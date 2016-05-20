package com.claire.test;

import com.claire.entity.Group;
import com.claire.entity.User;
import com.claire.service.GraphMakeService;
import com.claire.serviceImpl.GraphMakeServiceImpl;
import com.claire.util.Config;
import com.claire.util.Mode;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by claire on 5/17/16.
 */
public class OnlineTest {

    public static void main(String[] args){
        System.getProperties().put("proxySet","true");
        System.getProperties().put("proxyHost","9.181.193.205");
        System.getProperties().put("proxyPort","8080");
        Config config = new Config();
        Group group = joinGroup();
        //getItems(group);
//        createNodes(group);
//        makeOriginalGraph(group);
        makeDistanceGraph(group);
    }

    public static void getItems(Group group){
        GraphMakeService gService = new GraphMakeServiceImpl();
        gService.getItems(group);
    }

    public static void makeOriginalGraph(Group group){
        GraphMakeService gService = new GraphMakeServiceImpl();
        gService.makeOriginalGraph(gService.createUserNodes(group),gService.createItemNodes(group));
    }

    public static void makeDistanceGraph(Group group){
        GraphMakeService gService = new GraphMakeServiceImpl();
        gService.makeDistanceGraph(gService.createUserNodes(group),gService.createItemNodes(group));
    }

    public static void createNodes(Group group){
        GraphMakeService gService = new GraphMakeServiceImpl();
        gService.createUserNodes(group);
        gService.createItemNodes(group);
    }

    public static Group joinGroup(){
        List<User> userList = new ArrayList<User>();
        User owner = new User("d560VRV3ccG3MW7ivjaMnA", Mode.DRIVING, 1, "36.1472955,-115.1686364","");
        userList.add(owner);
        User user1 = new User("vpwhEbSTNJtQAF50dEMO2A", Mode.DRIVING,1,"36.1270548,-115.184086","");
        userList.add(user1);
        User user2 = new User("4TZARz8AjNWfukK2UfZitA", Mode.DRIVING,2,"36.1852674,-115.1995355","");
        userList.add(user2);
        User user3 = new User("4u0mTX9DP2oq4xJraCjBrA", Mode.WALKING,1,"36.2118645,-115.2012521","");
        userList.add(user3);
//        User user4 = new User("4uCqsNsOs4xFQs1Kk5h_Og", Mode.WALKING,3,"40.7224127715,-73.9888000488","");
//        userList.add(user4);
//        User user5 = new User("RQoPqBHWFyFIISR_p1lFDg", Mode.DRIVING,1,"40.7735218764,-73.9579010010","");
//        userList.add(user5);
//        User user6 = new User("rquJDew4DCRNlfNKu4ELEw", Mode.WALKING,1,"40.8091319538,-73.9433097839","");
//        userList.add(user6);

        String arriveTime = "";
        Group group = new Group(userList.size(),userList,arriveTime);
        return group;
    }

}
