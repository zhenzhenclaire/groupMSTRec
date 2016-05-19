package com.claire.serviceImpl;

import com.claire.dao.ReviewDao;
import com.claire.daoImpl.ReviewDaoImpl;
import com.claire.entity.*;
import com.claire.service.GraphMakeService;
import com.claire.service.PositionService;
import com.claire.util.Config;
import com.claire.util.FileProcess;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.json.JSONObject;

import java.util.*;
import java.util.logging.Logger;

import org.jgrapht.Graph;

import javax.swing.plaf.synth.SynthTextAreaUI;

/**
 * Created by claire on 5/17/16.
 */

public class GraphMakeServiceImpl implements GraphMakeService{
    static Logger logger = Logger.getLogger("ItemDaoImpl");
    String ratingFile = Config.ratingFile;
    String itemFile = Config.itemFile;

    @Override
    public List<String> getItems(Group group) {
        List<String> itemList = new ArrayList<String>();
        if(group != null){
            List<User> groupMemList = group.getMembers();
            for(User user: groupMemList){
                String uid = user.getUid();
                String line = FileProcess.findStringInFile(ratingFile,uid);
                String iid = line.split(",")[1];
                // logger.info(iid + "\n");
                itemList.add(iid);
            }
        }
        return itemList;
    }

    @Override
    public List<UserNode> createUserNodes(Group group) {

        List<User> userList = group.getMembers();
        List<UserNode> userNodeList = new ArrayList<UserNode>();
        if(userList.size() >= 1) {
            for (User user : userList) {
                UserNode uNode = new UserNode(user);
                userNodeList.add(uNode);
//                logger.info(uNode.getID());
            }
        }
//        logger.info("UserNodes:" + userNodeList.size());
        return userNodeList;
    }

    @Override
    public List<ItemNode> createItemNodes(Group group) {

        List<ItemNode> itemNodeList = new ArrayList<ItemNode>();
        List<String> itemIdList = getItems(group);
        logger.info("# of found item in rating file" + itemIdList.size());
        if(itemIdList.size() != 0){
            // "longitude": -79.9007057, "state": "PA", "stars": 4.5, "latitude": 40.3543266, "attributes":
            // 1. Find location
            for(String id:itemIdList){
                String line = FileProcess.findStringInFile(itemFile,id);

                JSONObject jo = new JSONObject(line);
                String longitude = jo.getDouble("longitude") + "";
                String latitude = jo.getDouble("latitude") + "";
                String location = longitude + "," + latitude;

//                logger.info(id + ":" + location);

                Item item = new Item(id,location);
                ItemNode iNode = new ItemNode(item);
                itemNodeList.add(iNode);
            }
        }
//        logger.info("ItemNodes:" + itemNodeList.size());
        return itemNodeList;
    }

    @Override
    public Graph makeOriginalGraph(List<UserNode> userNodes, List<ItemNode> itemNodes) {
        // Create a directed graph
        SimpleWeightedGraph<String, DefaultWeightedEdge> g =
                new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        // Add user nodes to graph
        for(UserNode uNode : userNodes){
            g.addVertex(uNode.getID());
        }

        // Add item nodes to graph
        for(ItemNode iNode : itemNodes){
            g.addVertex(iNode.getID());
        }

        int userNodeSize = userNodes.size();
        int itemNodeSize = itemNodes.size();

        ReviewDao rd = new ReviewDaoImpl();

        int num = 1;
        for(int i = 0; i < userNodeSize;i++){
            String userId = userNodes.get(i).getID();

            for(int j = 0;j < itemNodeSize;j++){

                String itemId = itemNodes.get(j).getID();
                DefaultWeightedEdge e = g.addEdge(userId,itemId);
                Double weight = rd.getReview(userId,itemId);
                //System.out.println(num + ":" + userId + "-" + itemId + ":" + weight);
                num++;
//                logger.info(userId + "," + itemId + ":" + weight);
                g.setEdgeWeight(e,weight);
                System.out.println(g.getEdge(userId,itemId).toString() + ":" +g.getEdgeWeight(g.getEdge(userId,itemId)));
            }
        }
        return g;
    }

    @Override
    public Graph makeDistanceGraph(List<UserNode> userNodes, List<ItemNode> itemNodes) {
        // Create a directed graph
        SimpleWeightedGraph<String, DefaultWeightedEdge> g =
                new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        // Add user nodes to graph
        for(UserNode uNode : userNodes){
            g.addVertex(uNode.getID());
        }

        // Add item nodes to graph
        for(ItemNode iNode : itemNodes){
            g.addVertex(iNode.getID());
        }

        int userNodeSize = userNodes.size();
        int itemNodeSize = itemNodes.size();

        PositionService positionService = new PositionServiceImpl();

        for(int i = 0; i < userNodeSize;i++){

            User u = userNodes.get(i).getUser();
            String userId = u.getUid();

            for(int j = 0;j < itemNodeSize;j++){
                Item item= itemNodes.get(j).getItem();
                String itemId = item.getId();
                DefaultWeightedEdge e = g.addEdge(userId,itemId);
                int duration = positionService.getDuration(u.getLocation(),item.getLocation(),u.getTransportMode());
                System.out.println(userId + "-" + itemId + ":" + duration);
                g.setEdgeWeight(e,duration);
                System.out.println(g.getEdge(userId,itemId).toString() + ":" +g.getEdgeWeight(g.getEdge(userId,itemId)));
            }
        }
        return g;
    }

    @Override
    public int userDistanceCal(UserNode usernode1, UserNode userNode2) {

        return 0;
    }

    @Override
    public Graph makeProjectedGraph(Graph originalGraph) {
        return null;
    }
}
