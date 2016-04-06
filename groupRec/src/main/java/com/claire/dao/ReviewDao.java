package com.claire.dao;

/**
 * Created by admin on 2016/4/6.
 */
public interface ReviewDao {

    void makeRatingFile();

    void replaceByReflectionTable();

    int getReview(int userId, int itemId);

}
