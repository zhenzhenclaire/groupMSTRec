package com.claire.service;

import java.sql.Time;

import com.claire.util.Mode;


/**
 * Created by admin on 2016/3/31.
 */
public interface PositionService {

     Time getTransportationTime(String startingLoc, String destination, Mode mode);

}
