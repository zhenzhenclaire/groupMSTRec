package com.claire.entity;

import java.sql.Time;
import java.util.List;

/**
 * Created by admin on 2016/3/31.
 */
public class Group {
	String ID;
	int numberOfMembers;
	List<User> members;
	String arrviedTime;
	Item recommendation;

	public Group(){}
	public Group(int numberOfMembers, List<User> members, String arrviedTime) {
		this.ID = Math.random() * 6 + "";
		this.numberOfMembers = numberOfMembers;
		this.members = members;
		this.arrviedTime = arrviedTime;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public int getNumberOfMembers() {
		return numberOfMembers;
	}

	public void setNumberOfMembers(int numberOfMembers) {
		this.numberOfMembers = numberOfMembers;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public String getArrviedTime() {
		return arrviedTime;
	}

	public void setArrviedTime(String arrviedTime) {
		this.arrviedTime = arrviedTime;
	}

	public Item getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(Item recommendation) {this.recommendation = recommendation;}
}
