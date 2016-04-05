package com.claire.dao;

import java.util.List;

import com.claire.entity.User;

/**
 * Created by admin on 2016/4/5.
 */
public interface UserDao {
	
	/* How to process user data? The current user data is stored in json file, 
	 * we want to put this into a Java List object? Or we want to store user in a database table?
	 * 
	 * If we want to have the user in web interface to operate the data, how does the user log on?
	 * 


	 */
	
	/*
	 * tab del data file
	 * userid <String>, userid <int>
	 */
    void makeUserReflectionTable();

    int getReflectedId(String uid);

    String getOriginalId(int reflectedUid);

    User findUserByUid(String uid);
	
}
