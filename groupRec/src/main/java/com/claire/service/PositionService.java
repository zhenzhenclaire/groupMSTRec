package claire.service;

import claire.util.Mode;

import java.sql.Time;

/**
 * Created by admin on 2016/3/31.
 */
public interface PositionService {

     Time getTransportationTime(String startingLoc, String destination, Mode mode);

}
