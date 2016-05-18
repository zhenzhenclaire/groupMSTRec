package com.claire.entity;

/**
 * Created by admin on 2016/3/31.
 */
public class Item {
	private String name;
	private String id;
	private String location;

	public Item(){}

	public Item(String id, String location) {
		this.id = id;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
