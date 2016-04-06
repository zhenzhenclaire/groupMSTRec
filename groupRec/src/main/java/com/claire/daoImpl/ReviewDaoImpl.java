package com.claire.daoImpl;

import com.claire.dao.ReviewDao;
import com.claire.dao.UserDao;
import com.claire.dao.ItemDao;
import com.claire.util.Config;
import com.claire.util.FileProcess;

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
    public int getReview(int userId, int itemId) {
        return 0;
    }
}
