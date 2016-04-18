package com.claire.test;

import com.claire.dao.ItemDao;
import com.claire.dao.ReviewDao;
import com.claire.dao.UserDao;
import com.claire.daoImpl.ItemDaoImpl;
import com.claire.daoImpl.ReviewDaoImpl;
import com.claire.daoImpl.UserDaoImpl;
import com.claire.util.Config;
import com.claire.util.FileProcess;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {

		Config config = new Config();

        //testGetOriginalId(4);
        //testGetReflectedId("fHtTaujcyKvXglE33Z5yIw");
        makeUserReflection();
		makeItemReflection();
		//testReg();
		makeRatingFile();

//		String line = "\"user_id\": \"Iu6AxdBYGR4A0wspR9BYHA\", \"review_id\": \"KPvLNJ21_4wbYNctrOwWdQ\", \"stars\": 5, \"date\": \"2014-02-13\", \"text\": \"Excellent food. Superb customer service. I miss the mario machines they used to have, but it's still a great place steeped in tradition.\", \"type\": \"review\", \"business_id\": \"5UmKMjUEUNdYWqANhGckJw\"}";
//        String reg1 = "\"user_id\": \"(.*)\", \"review_id\"";
//        String reg2 = "\"business_id\": \"(.*)\"}";
//        String reg3 = "\"stars\": (.*), \"date\"";

//        Pattern p = Pattern.compile("\"business_id\": \"(.*)\"}");
//		Matcher m = p.matcher(testStr);
//		while(m.find()){
//		System.out.println(m.group(1));
//		}
//
//        Pattern pat1 = Pattern.compile(reg1);
//        Pattern pat2 = Pattern.compile(reg2);
//        Pattern pat3 = Pattern.compile(reg3);
//        Matcher mat1 = pat1.matcher(line);
//        Matcher mat2 = pat2.matcher(line);
//        Matcher mat3 = pat3.matcher(line);
//        boolean rs1 = mat1.find();
//        boolean rs2 = mat2.find();
//        boolean rs3 = mat3.find();
//        if (rs1 && rs2 && rs3) {
//            String str = mat1.group(1) + "," + mat2.group(1) + "," + mat3.group(1) + "\n";
//            System.out.print(str);
//        }

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
