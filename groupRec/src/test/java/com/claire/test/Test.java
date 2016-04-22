package com.claire.test;

import com.claire.dao.ItemDao;
import com.claire.dao.ReviewDao;
import com.claire.dao.UserDao;
import com.claire.daoImpl.ItemDaoImpl;
import com.claire.daoImpl.ReviewDaoImpl;
import com.claire.daoImpl.UserDaoImpl;
import com.claire.entity.Item;
import com.claire.util.Config;
import com.claire.util.FileProcess;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {

		Config config = new Config();

//		makeUserReflection();
//		makeItemReflection();
//		makeRatingFile();

		prepareUserClusteringData();
		//prepareItemClusteringData();
		//testGetOriginalId(4);
		//testGetReflectedId("fHtTaujcyKvXglE33Z5yIw");

	}

	public static void prepareUserClusteringData(){
		UserDao ud = new UserDaoImpl();
		ud.makeUserClusteringData();
	}

	public static  void prepareItemClusteringData(){
		ItemDao id = new ItemDaoImpl();
		id.makeItemClusteringData();
	}

	public static void makeRatingFile() {
		ReviewDao rd = new ReviewDaoImpl();
		rd.makeRatingFile();
	}

	public static void makeItemReflection(){
		ItemDao itemDao = new ItemDaoImpl();
		itemDao.makeItemReflectionTable();
	}

	public static void makeUserReflection(){
		UserDao ui = new UserDaoImpl();
		ui.makeUserReflectionTable();
	}

	public static void testGetReflectedId(String uid){
		UserDao ui = new UserDaoImpl();
		int reflectedUid = ui.getReflectedId(uid);
		System.out.println("reflectedUid for " + uid + " is:" + reflectedUid);
	}


	public static void testGetOriginalId(int reflectedUid) {
		UserDao ui = new UserDaoImpl();
		String uid = ui.getOriginalId(reflectedUid);
		System.out.println("OriginalId for " + reflectedUid + " is:" + uid);
	}
}