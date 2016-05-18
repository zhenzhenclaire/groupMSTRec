package com.claire.entity;

/**
 * Created by admin on 2016/3/31.
 */
public class ItemNode {
    private Item item;
    private String ID;

    public ItemNode(Item item) {
        this.item = item;
        this.ID = item.getId();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
