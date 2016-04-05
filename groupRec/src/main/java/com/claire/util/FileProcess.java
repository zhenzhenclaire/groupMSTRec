package com.claire.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2016/4/5.
 */
public class FileProcess {

	   public static void readFileByLines(String fileName) {
	        File file = new File(fileName);
	        BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            int line = 1;
	            while ((tempString = reader.readLine()) != null && line < 10) {
	                //int num = tempString.split(",").length;

	                System.out.println("line " + line + ": " + tempString);
	                line++;
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
	    }

	   public static void writeToFile(ArrayList<String> strs, String fileName){
		   File file = new File(fileName);
		   Writer writer = null;
		   try {
			   writer = new BufferedWriter(new FileWriter(file));
			for(String str:strs){
				writer.write(str);
			}
			writer.flush();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	   }
	   
	   public static ArrayList <String> readFileByLines(String fileName, String reg) {
	        File file = new File(fileName);
	        BufferedReader reader = null;
	        ArrayList<String> list = new ArrayList<String>();
	        try {
	            reader = new BufferedReader(new FileReader(file));
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	            	Pattern pat = Pattern.compile(reg);
	            	Matcher mat = pat.matcher(line);
	            	boolean rs = mat.find();
	            	if (rs && mat.groupCount() == 1 && null != mat.group(1)) {
	            		list.add(mat.group(1));
	            	} else {
	            		// This line of data is invalid, just drop it.
	            	}
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e) {
	                	e.printStackTrace();
	                }
	            }
	        }
			return list;
	    }
   public static void main(String[] args){
        FileProcess.readFileByLines("data/yelp_academic_dataset_review.json");
    }
}
