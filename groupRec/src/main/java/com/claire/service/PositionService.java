package com.claire.service;

import java.sql.Time;
import java.util.ArrayList;

import com.claire.entity.Group;
import com.claire.util.Mode;


/**
 * Created by admin on 2016/3/31.
 */
public interface PositionService {

     int getDistance(String startingLoc, String destination, Mode mode);

     int getDuration(String startingLoc, String destination, Mode mode);

     ArrayList<String> getDurationOfGroup(Group group, String destination);

     ArrayList<String> getDistanceOfGroup(Group group, String destination);
}
