package com.claire.test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.claire.dao.UserDao;
import com.claire.daoImpl.UserDaoImpl;
import com.claire.util.Config;
import com.claire.util.FileProcess;

public class Test {

	public static void main(String[] args) {
		
		
		Config config = new Config();
		testGetOriginalId(4);
		testGetReflectedId("fHtTaujcyKvXglE33Z5yIw");
		//makeReflection();
		//testReg();
		
//		String testStr = "12315<Text>show me</Text>";  
//		Pattern p = Pattern.compile("<Text>(.*)</Text>");  
//		Matcher m = p.matcher(testStr);  
//		while(m.find()){  
//		System.out.println(m.group(1));  
//		}

	}

	public static void makeReflection(){
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


	public static void testReg() {
		String userFile = "/Users/jiangnan/Documents/groupMSTRec/groupRec/src/test/java/com/claire/test/user.txt";
		String reg = "\"user_id\": \"([^\"]*)\"";
		String reg1 = "\"user_id\": \"(.*)\"";
		
		
		
		ArrayList<String> userList = FileProcess.readFileByLines(userFile, reg);
		for (int i = 0; i < userList.size(); i++) {
			System.out.println(userList.get(i) + ",");
			
		}

	}
}
