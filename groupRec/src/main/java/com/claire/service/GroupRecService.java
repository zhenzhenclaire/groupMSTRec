package com.claire.service;

import java.util.List;

import com.claire.entity.Group;
import com.claire.entity.InducedGraph;
import com.claire.entity.Item;
import com.claire.entity.ItemBridge;
import com.claire.entity.ItemNode;
import com.claire.entity.MSTPair;
import com.claire.entity.UserNode;


/**
 * Created by admin on 2016/3/31.
 */
public interface GroupRecService {
    List<MSTPair> generateMST (InducedGraph inducedG);

    ItemBridge getItemBridge(UserNode userNodeFrom, UserNode userNodeTo);

    List<ItemBridge> getItemBridgeList (List<MSTPair> mstPairs);

    List<ItemNode> HITS(List<ItemNode> itemNodeList, Group group);

    List<Item> getRecommendations(List <ItemNode> itemNodeList);

}
