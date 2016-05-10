package com.claire.dao;

import java.util.ArrayList;

/**
 * Created by admin on 2016/4/6.
 */
public interface ReviewDao {

    void makeRatingFile();

    void replaceByReflectionTable(ArrayList<String> starList);

    Double getReview(String userId, String itemId);

}
