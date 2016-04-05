package com.claire.service;

import com.claire.entity.*;
import com.claire.util.Matrix;

import java.util.List;

/**
 * Created by admin on 2016/3/31.
 */
public interface GraphMakeService {
    List<Item> getHighScoreItemList(Group group, Matrix preferenceMatrix);

    int getItemFavor(Group group, Item Item);

    List<ItemNode> createItemNodes (List<Item> itemList);

    List<User> getUsers (Group group);

    List<UserNode> createUserNodes (List<User> userList);

    Graph createGraph (List<UserNode> userNodeList, List<ItemNode> itemNodeList);

    Graph filterByTimeCost();

    InducedGraph projectGraphByUser (Graph g);
}
