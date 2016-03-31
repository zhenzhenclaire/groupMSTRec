package claire.service;

import claire.entity.*;

import java.util.List;

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
