package claire.dao;

import claire.entity.User;

/**
 * Created by admin on 2016/4/5.
 */
public interface UserDao {

    void makeUserReflectionTable();

    int getReflectedId(String uid);

    String getOriginalId(int reflectedUid);

    User findUserByUid(String uid);


}
