package com.claire.entity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.claire.util.Util;

import org.apache.commons.collections.map.HashedMap;

public class RecStore {
	private static RecStore instance =null;  
	public static Map<String, Group> groupMap;
	public static String groupID;
	public static List<User> users;
	public static Group group;
	
    public static synchronized RecStore getInstance(){  

		if (instance == null) {
			instance = new RecStore();
		}
		return instance;
    }
    
    public RecStore() {
    	if(groupMap == null || groupMap.size() == 0) {
    		// We need to create a new group.
    		String id = Util.getRandomString(6);
    		Map<String, Group> map = new HashedMap();
    		Group g = new Group();
    		g.setID(id);
    		map.put(id, g);
    		groupID = id;
    		groupMap = map;
    		group = g;
    	
    	} else {
    		// Group already exists.
    	}
    }

    public void createGroup(User user, Time arrivedTime, int num) {
    	Group g = new Group();
    	g.setArrviedTime(arrivedTime);
    	g.setID(groupID);
    	List<User> members = new ArrayList<User>(); 
    	members.add(user);
    	g.setMembers(members);
    	g.setNumberOfMembers(num);
    	
    	group = g;
    	
    }
    
    public void joinGroup(User user) {
    	
    	List<User> mem = group.members;
    	mem.add(user);
    	
    	group.setMembers(mem);
    	
    	//group = g;
    	
    }
}
