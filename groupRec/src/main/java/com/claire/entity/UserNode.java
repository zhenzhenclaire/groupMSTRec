package com.claire.entity;

/**
 * Created by admin on 2016/3/31.
 */
public class UserNode {
    private String ID;
    private User user;

    public UserNode(User user) {
        this.user = user;
        this.ID = user.getUid();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
