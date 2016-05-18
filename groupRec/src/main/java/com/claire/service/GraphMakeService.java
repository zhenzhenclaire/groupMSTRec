package com.claire.service;

import com.claire.entity.*;
import com.claire.util.Matrix;
import org.jgrapht.Graph;

import java.util.List;

/**
 * Created by admin on 2016/3/31.
 */
public interface GraphMakeService {

    List<String> getItems(Group group);

    List<UserNode> createUserNodes (Group group);

    List<ItemNode> createItemNodes (Group group);

    Graph makeOriginalGraph(List<UserNode> userNodes,List<ItemNode> itemNodes);

    int userDistanceCal(UserNode usernode1, UserNode userNode2);

    Graph makeProjectedGraph (Graph originalGraph);

}
