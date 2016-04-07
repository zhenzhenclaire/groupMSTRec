package com.claire.util;

import java.io.*;
import java.util.Properties;

/**
 * Created by claire on 12/9/15.
 */
public class Config {
    public static String dataPath;
    public static String clusteringPath;
    public static String userFile;
    public static String itemFile;
    public static String reviewFile;

    public static String ratingFile;
    public static String replacedRating;
    public static String squareMatrixPath;
    public static String clusteringResult;

    public static String userReflectionTable;
    public static String itemReflectionTable;
    public static String ratingModel;
    public static String mergedUser;
    public static String mergedHotel;
    public static double alpha;
    public static int maxRating;
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
        userFile = dataPath + properties.getProperty("userFile");
        itemFile = dataPath + properties.getProperty("itemFile");
        reviewFile = dataPath + properties.getProperty("reviewFile");


        userReflectionTable = dataPath + properties.getProperty("userReflectionTable");
        itemReflectionTable = dataPath + properties.getProperty("itemReflectionTable");
        ratingFile = dataPath + properties.getProperty("ratingFile");
        replacedRating = dataPath + properties.getProperty("replacedRating");

        clusteringPath = dataPath + properties.getProperty("clusteringPath");
        squareMatrixPath = clusteringPath + properties.getProperty("squareMatrixPath");
        clusteringResult = clusteringPath + properties.getProperty("clusteringResult");

        ratingModel = dataPath + properties.getProperty("ratingModel");
        mergedHotel = clusteringPath + properties.getProperty("mergedHotel");
        mergedUser = clusteringPath + properties.getProperty("mergedUser");
        alpha = Double.parseDouble(properties.getProperty("alpha", "0.5"));
        maxRating = Integer.parseInt(properties.getProperty("maxRating", "5"));
    }


//    public Config(){
//        CreateFileUtil.createDir(clusteringPath);
//    }

}
