package com.claire.daoImpl;

import com.claire.dao.ReviewDao;
import com.claire.dao.UserDao;
import com.claire.dao.ItemDao;
import com.claire.util.Config;
import com.claire.util.FileProcess;
import com.esotericsoftware.minlog.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by admin on 2016/4/6.
 */
public class ReviewDaoImpl implements ReviewDao {
    static Logger logger = Logger.getLogger("ReviewDaoImpl");
    String reviewFile = Config.reviewFile;
    String ratingFile = Config.ratingFile;
    String replacedRating = Config.replacedRating;

    @Override
    public void makeRatingFile() {
        logger.info("Start making rating file.");
        // read from dataPath/reviewFile
        /*
	     * line 1: {"votes": {"funny": 0, "useful": 0, "cool": 0}, "user_id": "PUFPaY9KxDAcGqfsorJp3Q", "review_id": "Ya85v4eqdd6k9Od8HbQjyA", "stars": 4, "date": "2012-08-01", "text": "Mr Hoagie is an institution. Walking in, it does seem like a throwback to 30 years ago, old fashioned menu board, booths out of the 70s, and a large selection of food. Their speciality is the Italian Hoagie, and it is voted the best in the area year after year. I usually order the burger, while the patties are obviously cooked from frozen, all of the other ingredients are very fresh. Overall, its a good alternative to Subway, which is down the road.", "type": "review", "business_id": "5UmKMjUEUNdYWqANhGckJw"}
	     */
        logger.info(reviewFile);
        String userReg = "\"user_id\": \"(.*)\", \"review_id\"";
        String itemReg = "\"business_id\": \"(.*)\"}";
        String starReg = "\"stars\": (.*), \"date\"";
        ArrayList<String> starList = FileProcess.readFileByLines(reviewFile, userReg, itemReg, starReg);
        logger.info(starList.size() + "");

        FileProcess.writeToFile(starList, ratingFile);

        replaceByReflectionTable(starList);
        logger.info("Done.");
    }

    @Override
    public void replaceByReflectionTable(ArrayList<String> starList) {

        UserDao user = new UserDaoImpl();
        ItemDao item = new ItemDaoImpl();
        ArrayList<String> replacedStarList = new ArrayList<String>();

        logger.info("Start making replaced rating file.");

        for(String str:starList){
            String[] review = str.split(",");
            if(review.length == 3) {

                int uid = user.getReflectedId(review[0].trim());
                int itemId = item.getReflectedId(review[1].trim());

                replacedStarList.add(uid+"," + itemId + "," + review[2]);

            } else {
                // this line of data is not user,item,rating, just drop it.
            }
        }
        FileProcess.writeToFile(replacedStarList, replacedRating);
        logger.info("Done.");
    }

    @Override
    public Double getReview(String userId, String itemId) {
        Double rating = 0.0;
        // 1. Find if this user has original rating to this item in ratingFile
        boolean isFind = false;
        File file = new File(Config.ratingFile);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                //int num = tempString.split(",").length;
                String findStr = userId + "," + itemId;
                if(tempString.contains(findStr)){
                    rating = Double.parseDouble(tempString.split(",")[2]);
                    isFind = true;
                    logger.info("User " + userId + "has already rated item " + itemId + ", the rating is " + rating);
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

        // 2. If not, calculate predicted rating
        if(!isFind){
            logger.info("User " + userId + " did not rate item " + itemId + " before. Start to calculate estimated rating.");
            int ucl = 0;   //user class
            int icl = 0;    //item class
            int umix = 0;   //user mix class
            int imix = 0;   //item mix class
            Double M_cu = 0.0;  //avg rating of user class
            Double M_ci = 0.0;  //avg rating of item class
            Double M_G = 0.0;   //avg rating of user mix
            Double M_H = 0.0;   //avg rating of item mix
            Double M_Coc = Config.mixRating;    //Global rating
            Double M_CuCi = 0.0;    //estimated rating of class
            Double A_u = 0.0;   //avg rating of user
            Double A_i = 0.0;   //avg rating of item

            // 1) Find user class(ucl)
            String uclLine = FileProcess.findStringInFile(Config.userClusteringResult,userId);
            if(!uclLine.equals("NOT FOUND")){
                ucl = Integer.parseInt(uclLine.split(",")[1].replace("\"",""));
                logger.info("user class(ucl) is " + ucl);
            }
            else{
                System.out.println("Userid not found");
            }


            // 2) Find item class(icl)
            String iclLine = FileProcess.findStringInFile(Config.itemClusteringResult,itemId);
            if(!iclLine.equals("NOT FOUND")){
                icl = Integer.parseInt(iclLine.split(",")[1].replace("\"",""));
                logger.info("Item class(icl) is " + icl);
            }
            else{
                System.out.println("Itemid not found");
            }

            // 3) Count avg rating of user class(M_cu)
            String mcuLine = FileProcess.findStringInFile(Config.userClAvg,ucl + ",");
            if(!mcuLine.equals("NOT FOUND")){
                M_cu = Double.parseDouble(mcuLine.split(",")[1].replace("\"",""));
                logger.info("Average rating of user class(M_cu) is " + M_cu);
            }
            else{
                System.out.println("M_cu not found");
            }

            // 4) Count avg rating of item class(M_ci)
            String mciLine = FileProcess.findStringInFile(Config.itemClAvg,icl + ",");
            if(!mciLine.equals("NOT FOUND")){
                M_ci = Double.parseDouble(mciLine.split(",")[1].replace("\"",""));
                logger.info("Average rating of item class(M_ci) is " + M_ci);
            }
            else{
                System.out.println("M_cu not found");
            }

            // 5) Find user mix class(umix)
            String umixLine = FileProcess.findStringInFile(Config.usercl_mixcl,ucl + ",");
            if(!umixLine.equals("NOT FOUND")){
                umix = Integer.parseInt(umixLine.split(",")[2].replace("\"",""));
                logger.info("User's mix class(umix) is " + umix);
            }
            else{
                System.out.println("umix not found");
            }


            // 6) Find item mix class(imix)
            String imixLine = FileProcess.findStringInFile(Config.itemcl_mixcl,icl + ",");
            if(!imixLine.equals("NOT FOUND")){
                imix = Integer.parseInt(imixLine.split(",")[2].replace("\"",""));
                logger.info("Item's mix class(imix) is " + imix);
            }
            else{
                System.out.println("imix not found");
            }

            // 7) Count avg rating of user mix(M_G)
            String mgLine = FileProcess.findStringInFile(Config.mixcl_avgstars,umix + ",");
            if(!mgLine.equals("NOT FOUND")){
                M_G = Double.parseDouble(mgLine.split(",")[1].replace("\"",""));
                logger.info("Average rating of user mix class(M_G) is " + M_G);
            }
            else{
                System.out.println("M_G not found");
            }


            // 8) Count avg rating of item mix(M_H)
            String mhLine = FileProcess.findStringInFile(Config.mixcl_avgstars,imix + ",");
            if(!mhLine.equals("NOT FOUND")){
                M_H = Double.parseDouble(mhLine.split(",")[1].replace("\"",""));
                logger.info("Average rating of item mix class(M_H) is " + M_H);
            }
            else{
                System.out.println("M_H not found");
            }

            // 9) Calculate class rating
            M_CuCi = M_Coc + (M_cu - M_G) + (M_ci - M_H);
            logger.info("Class rating(M_CuCi) is " + M_CuCi);

            // 10) Find user avg rating(A_u)
            String auLine = FileProcess.findStringInFile(Config.user_cl_star,"\"" + userId + "\"," + ucl + ",");
            if(!auLine.equals("NOT FOUND")){
                A_u = Double.parseDouble(auLine.split(",")[2].replace("\"",""));
                logger.info("Average rating of user(A_u) is " + A_u + ". Start to consider user preference.");
            }
            else{
                System.out.println("A_u not found");
            }

            // 11) Find item avg rating(A_i)
            String aiLine = FileProcess.findStringInFile(Config.item_cl_star,"\"" + itemId + "\"," + icl + ",");
            if(!aiLine.equals("NOT FOUND")){
                A_i = Double.parseDouble(aiLine.split(",")[2].replace("\"",""));
                logger.info("Average rating of item(A_i) is " + A_i);
            }
            else{
                System.out.println("A_i not found");
            }

            // 12) Combine preference to predicted rating
            rating = M_CuCi + (A_u - M_cu) + (A_i - M_ci);
            logger.info("Combined predicted rating is " + rating);
        }

        return rating;
    }
}
