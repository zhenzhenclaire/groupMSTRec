package com.claire.serviceImpl;

import com.claire.entity.*;
import com.claire.service.GroupRecService;
import org.jgrapht.Graph;

import java.util.List;

/**
 * Created by claire on 5/17/16.
 */
public class GroupRecServiceImpl implements GroupRecService{
    @Override
    public List<MSTPair> generateMST(Graph projectedGraph) {
        return null;
    }

    @Override
    public ItemBridge getItemBridge(UserNode userNodeFrom, UserNode userNodeTo) {
        return null;
    }

    @Override
    public List<ItemBridge> getItemBridgeList(List<MSTPair> mstPairs) {
        return null;
    }

    @Override
    public List<ItemNode> HITS(List<ItemNode> itemNodeList, Group group) {
        return null;
    }

    @Override
    public List<Item> getRecommendations(List<ItemNode> itemNodeList) {
        return null;
    }
}
