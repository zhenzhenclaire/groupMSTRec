package com.claire.util;

import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by claire on 12/9/15.
 */
public class Config {
    public static String dataPath;
    public static String rDataPath;
    public static String clusteringPath;
    public static String userFile;
    public static String itemFile;
    public static String reviewFile;

    public static String userClusteringData;
    public static String itemClusteringData;

    public static String ratingFile;
    public static String replacedRating;

    public static String user_usercl_mixcl;
    public static String item_itemcl_mixcl;

    public static String usercl_mixcl;
    public static String itemcl_mixcl;

    public static String userClusteringResult;
    public static String itemClusteringResult;

    public static String userClAvg;
    public static String itemClAvg;
    public static String mixcl_avgstars;
    public static Double mixRating;
    public static Double userAvgRating;
    public static Double itemAvgRating;
    public static String user_cl_star;
    public static String item_cl_star;

    public static String userReflectionTable;
    public static String itemReflectionTable;
    public static String ratingModel;

    public static double alpha;
    public static int maxRating;


    public static List<String> userLocationList;
    public static List<String> itemLocationList;
    public static List<String> distanceList;
    public static List<Long> travelTimeList;

    public static String filePath = "groupRec/conf/config.properties";

    private static Config instance =null;

    public static void writeToProperty() {
        Properties properties = new Properties();
        try {
            OutputStream out = new BufferedOutputStream(new FileOutputStream(filePath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized  Config getInstance(){

        if (instance == null) {
            instance = new Config();
        }

        return instance;

    }

    public Config() {
        System.out.println("init");
        Properties properties = new Properties();

        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataPath = properties.getProperty("dataPath");
        rDataPath = properties.getProperty("rDataPath");
        clusteringPath = dataPath + "clustering/";

        userFile = dataPath + properties.getProperty("userFile");
        itemFile = dataPath + properties.getProperty("itemFile");
        reviewFile = dataPath + properties.getProperty("reviewFile");


        userReflectionTable = dataPath + properties.getProperty("userReflectionTable");
        itemReflectionTable = dataPath + properties.getProperty("itemReflectionTable");
        ratingFile = dataPath + properties.getProperty("ratingFile");
        replacedRating = dataPath + properties.getProperty("replacedRating");

        userClusteringData = clusteringPath + properties.getProperty("userClusteringData");
        itemClusteringData = clusteringPath + properties.getProperty("itemClusteringData");

        userClusteringResult = rDataPath + properties.getProperty("userClusteringResult");
        itemClusteringResult = rDataPath + properties.getProperty("itemClusteringResult");
        user_usercl_mixcl = rDataPath + properties.getProperty("userCluster");
        item_itemcl_mixcl = rDataPath + properties.getProperty("itemCluster");
        usercl_mixcl = rDataPath + properties.getProperty("usercl_mixcl");
        itemcl_mixcl = rDataPath + properties.getProperty("itemcl_mixcl");
        userClAvg = rDataPath + properties.getProperty("userClAvg");
        itemClAvg = rDataPath + properties.getProperty("itemClAvg");
        mixcl_avgstars = rDataPath + properties.getProperty("mixcl_avgstars");
        mixRating = Double.parseDouble(properties.getProperty("mixRating"));
        userAvgRating = Double.parseDouble(properties.getProperty("userAvgRating"));
        itemAvgRating = Double.parseDouble(properties.getProperty("itemAvgRating"));
        user_cl_star = rDataPath + properties.getProperty("user_cl_star");
        item_cl_star = rDataPath + properties.getProperty("item_cl_star");

        ratingModel = dataPath + properties.getProperty("ratingModel");

        alpha = Double.parseDouble(properties.getProperty("alpha", "0.5"));
        maxRating = Integer.parseInt(properties.getProperty("maxRating", "5"));

        List<String> list  = new ArrayList<String>();
        list.add("37.7531, -122.4039");
        list.add("37.7831, -122.4139");
        list.add("37.7331, -122.4639");
        list.add("37.7631, -122.4339");
        list.add("37.7931, -122.4539");
        list.add("37.7131, -122.4039");
        list.add("37.7931, -122.4239");
        list.add("37.7031, -122.4939");
        list.add("37.7231, -122.3539");
        list.add("37.7531, -122.3739");

        userLocationList = list;

        List<String> itemList  = new ArrayList<String>();
        itemList.add("37.7525, -122.4510");
        itemList.add("37.7501, -122.4439");
        itemList.add("37.7589, -122.4399");
        itemLocationList = itemList;


        List<String> distance  = new ArrayList<String>();
        distance.add("15 miles");
        distance.add("10.8 miles");
        distance.add("19.3 miles");
        distance.add("25.5 miles");
        distance.add("29 miles");
        distance.add("18 miles");
        distance.add("16.5 miles");
        distance.add("21 miles");
        distance.add("17.3 miles");
        distance.add("15.6 miles");
        distance.add("16.5 miles");
        distance.add("12.4 miles");
        distanceList = distance;

        List<Long> time  = new ArrayList<Long>();
        time.add(new Long(60000 * 45));
        time.add(new Long(60000 * 31));
        time.add(new Long(60000 * 50));
        time.add(new Long(60000 * 60));
        time.add(new Long(60000 * 65));
        time.add(new Long(60000 * 75));
        time.add(new Long(60000 * 55));
        time.add(new Long(60000 * 67));
        time.add(new Long(60000 * 76));
        time.add(new Long(60000 * 48));
        time.add(new Long(60000 * 39));
        travelTimeList = time;
    }


//    public Config(){
//        CreateFileUtil.createDir(clusteringPath);
//    }

}