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
	Time arrviedTime;
	Item recommendation;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public List<User> getMembers() {
		return members;
	}
	public void setMembers(List<User> members) {
		this.members = members;
	}
	public Time getArrviedTime() {
		return arrviedTime;
	}
	public void setArrviedTime(Time arrviedTime) {
		this.arrviedTime = arrviedTime;
	}
	public Item getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(Item recommendation) {
		this.recommendation = recommendation;
	} 
	public int getNumberOfMembers() {
		return numberOfMembers;
	}
	public void setNumberOfMembers(int numberOfMembers) {
		this.numberOfMembers = numberOfMembers;
	}

}
