package com.claire.util;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2016/4/5.
 */
public class FileProcess {

	public static String findStringInFile(String fileName,String str) {
		String result = "NOT FOUND";
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				//int num = tempString.split(",").length;
				if (tempString.contains(str)) {
					result = tempString;
					break;
				}
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
		return result;
	}


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
				/*
				Only for item Clustering to escape some invalid data line.
				 */
				if(str.split(",").length > 10)	continue;
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

	public static ArrayList<String> readFileByLines(String fileName, String reg1, String reg2, String reg3) {
		File file = new File(fileName);
		BufferedReader reader = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				Pattern pat1 = Pattern.compile(reg1);
				Pattern pat2 = Pattern.compile(reg2);
				Pattern pat3 = Pattern.compile(reg3);
				Matcher mat1 = pat1.matcher(line);
				Matcher mat2 = pat2.matcher(line);
				Matcher mat3 = pat3.matcher(line);
				boolean rs1 = mat1.find();
				boolean rs2 = mat2.find();
				boolean rs3 = mat3.find();
				if (rs1 && rs2 && rs3 && mat1.groupCount() == 1 && null != mat1.group(1) && mat2.groupCount() == 1 && null != mat2.group(1) && mat3.groupCount() == 1 && null != mat3.group(1)) {
					String str = mat1.group(1) + "," + mat2.group(1) + "," + mat3.group(1) + "\n";
					list.add(str);
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

	public static ArrayList<String> readFileByLines(String fileName, String[] regs) {
		File file = new File(fileName);
		BufferedReader reader = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {

				int num_of_regs = regs.length;

				Pattern[] pats = new Pattern[num_of_regs];
				Matcher[] mats = new Matcher[num_of_regs];
				boolean[] rss = new boolean[num_of_regs];

				for(int i = 0;i < num_of_regs;i++){
					pats[i] = Pattern.compile(regs[i]);
					mats[i] = pats[i].matcher(line);
					rss[i] = mats[i].find();
				}

				boolean rs = true;
				boolean result = true;

				for(int i = 0;i < num_of_regs;i++){
					if(rs){
						rs = rs && rss[i];
					}
					else{
						result = false;
						break;
					}
				}

				String str = "";
				if(result){
					for(int i = 0;i < num_of_regs;i++) {
							if(rss[i] == false){
								str += "deficiency";
							}
							else {
								str += mats[i].group(1);
							}
						if (i < num_of_regs - 1){
							str += ",";
						}
					}
					list.add(str);
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

	public static ArrayList<String> readFileByLines(String fileName, ArrayList<String> regList) {
		File file = new File(fileName);
		BufferedReader reader = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {

				int num_of_regs = regList.size();

				Pattern[] pats = new Pattern[num_of_regs];
				Matcher[] mats = new Matcher[num_of_regs];
				boolean[] rss = new boolean[num_of_regs];

				for(int i = 0;i < num_of_regs;i++){
					pats[i] = Pattern.compile(regList.get(i));
					mats[i] = pats[i].matcher(line);
					rss[i] = mats[i].find();
				}

				boolean rs = true;
				boolean result = true;

				for(int i = 0;i < num_of_regs;i++){
					if(rs){
						rs = rs && rss[i];
					}
					else{
						result = false;
						break;
					}
				}

				String str = "";
				if(result){
					for(int i = 0;i < num_of_regs;i++) {
						if(rss[i] == false){
							str += "deficiency";
						}
						else {
							str += mats[i].group(1);
						}
						if (i < num_of_regs - 1){
							str += ",";
						}
					}
					list.add(str);
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