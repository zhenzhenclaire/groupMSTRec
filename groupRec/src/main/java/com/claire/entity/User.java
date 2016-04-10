package com.claire.entity;

import java.sql.Time;

import com.claire.util.Mode;

/**
 * Created by admin on 2016/3/31.
 */
public class User {
	private String uid;
	
	private String name;
	
	private Time startTime;

	private Group belonging;
	
	private Mode transportMode;
	
	private int right;

	private String location;
	
	private int averageStars;
	
	private long travelTime;
	
	private String distance;
	

	public long getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(long travelTime) {
		this.travelTime = travelTime;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Group getBelonging() {
		return belonging;
	}

	public void setBelonging(Group belonging) {
		this.belonging = belonging;
	}

	public Mode getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(Mode transportMode) {
		this.transportMode = transportMode;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getAverageStars() {
		return averageStars;
	}

	public void setAverageStars(int averageStars) {
		this.averageStars = averageStars;
	}


}
