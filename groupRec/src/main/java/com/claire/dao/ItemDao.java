package claire.dao;


import claire.entity.Item;

/**
 * Created by admin on 2016/4/5.
 */
public interface ItemDao {

    void makeItemReflectionTable();

    int getReflectedId(String rid);

    String getOriginalId(int reflectedRid);

    Item findItemByRid(String rid);

}
