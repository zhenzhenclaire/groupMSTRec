package com.claire;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by claire on 5/7/16.
 */
public class replaceRate {

    Map<String,String> itemMap = new HashMap<String,String>();
    Map<String,String> userMap = new HashMap<String,String>();

    public void replace(String rateinput,String iteminput,String userinput,String output) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader(iteminput));
        String line = null;
        System.out.println("read item cluster file");
        while((line = reader.readLine())!=null){
            line = line.trim().replace("\"","");
            String[] eles = line.split(",");
//            System.out.println(line);
            itemMap.put(eles[0],eles[1]);
        }
        reader.close();

        System.out.println("read item cluster file");
        reader = new BufferedReader(new FileReader(userinput));
        while ((line = reader.readLine())!=null){
            line = line.trim().replace("\"","");
            String[] eles = line.split(",");
            userMap.put(eles[0],eles[1]);
//            System.out.println(line);
        }
        reader.close();

        System.out.println("begin to replace");
        reader = new BufferedReader(new FileReader(rateinput));
        BufferedWriter writer = new BufferedWriter(new FileWriter(output));
        int i = 0;
        while((line = reader.readLine())!=null){
            String[] eles = line.split(",");
            writer.write(getUserCluster(eles[0])+ "," + getItemCluster(eles[1]) + "," + eles[2]);
            writer.newLine();
            i++;
            if (i%1000 == 0){
                System.out.println(i);line = line.trim().replace(",","");
            }
        }
        reader.close();
        writer.flush();
        writer.close();

    }

    public String getUserCluster(String uid){
        String res = userMap.get(uid);
        if (res == null){
            return "0";
        }else{
            return res;
        }
    }

    public String getItemCluster(String itemid){
        String res = itemMap.get(itemid);
        if (res == null){
            return "0";
        }else{
            return res;
        }
    }


    public static void main(String[] args) throws Exception {
        replaceRate replace = new replaceRate();
        String rateinput = "/home/claire/IdeaProjects/groupMSTRec/data/ratingFile";
        String iteminput = "/home/claire/RProjects/GroupRec/data/itemSummary/itemClusteringResult";
        String userinput = "/home/claire/RProjects/GroupRec/data/userSummary/userClusteringResult";
        String output = "/home/claire/IdeaProjects/groupMSTRec/data/reRatingFile";
        replace.replace(rateinput,iteminput,userinput,output);
    }
}
