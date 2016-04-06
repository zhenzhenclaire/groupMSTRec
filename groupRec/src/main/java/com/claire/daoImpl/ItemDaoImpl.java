package com.claire.daoImpl;

import com.claire.dao.ItemDao;
import com.claire.entity.Item;
import com.claire.util.Config;
import com.claire.util.FileProcess;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by admin on 2016/4/5.
 */
public class ItemDaoImpl implements ItemDao {
    static Logger logger = Logger.getLogger("ItemDaoImpl");
    String itemFile = Config.itemFile;
    String itemReflectionTable = Config.itemReflectionTable;

    @Override
    public void makeItemReflectionTable() {
        logger.info("Start making item reflectionTable.");
        // read from dataPath/itemFile
        /*
	     * line 1: {"business_id": "5UmKMjUEUNdYWqANhGckJw", "full_address": "4734 Lebanon Church Rd\nDravosburg, PA 15034", "hours": {"Friday": {"close": "21:00", "open": "11:00"}, "Tuesday": {"close": "21:00", "open": "11:00"}, "Thursday": {"close": "21:00", "open": "11:00"}, "Wednesday": {"close": "21:00", "open": "11:00"}, "Monday": {"close": "21:00", "open": "11:00"}}, "open": true, "categories": ["Fast Food", "Restaurants"], "city": "Dravosburg", "review_count": 4, "name": "Mr Hoagie", "neighborhoods": [], "longitude": -79.9007057, "state": "PA", "stars": 4.5, "latitude": 40.3543266, "attributes": {"Take-out": true, "Drive-Thru": false, "Good For": {"dessert": false, "latenight": false, "lunch": false, "dinner": false, "brunch": false, "breakfast": false}, "Caters": false, "Noise Level": "average", "Takes Reservations": false, "Delivery": false, "Ambience": {"romantic": false, "intimate": false, "classy": false, "hipster": false, "divey": false, "touristy": false, "trendy": false, "upscale": false, "casual": false}, "Parking": {"garage": false, "street": false, "validated": false, "lot": false, "valet": false}, "Has TV": false, "Outdoor Seating": false, "Attire": "casual", "Alcohol": "none", "Waiter Service": false, "Accepts Credit Cards": true, "Good for Kids": true, "Good For Groups": true, "Price Range": 1}, "type": "business"}
	     */
        // Get list of itemid List <String> from json file
        // We need to find the first "business_id": " and the text before next "
        String reg = "\"business_id\": \"(.*)\", \"full_address\"";
        ArrayList<String> itemList = FileProcess.readFileByLines(itemFile, reg);
        ArrayList<String> itemReflectionList = new ArrayList<String>();

        // Generate itemid <int>
        for (int i = 1; i <= itemList.size(); i++) {
            itemReflectionList.add(i + "\t" + itemList.get(i - 1) + "\n");
        }

        // Generate itemid <String> TAB itemid <int> itemReflectionTable
        FileProcess.writeToFile(itemReflectionList, itemReflectionTable);
        logger.info("Done.");
    }

    @Override
    public int getReflectedId(String rid) {
        logger.info("Get reflected id of user:" + rid);
        String itemReflectionTable = Config.itemReflectionTable;
        String reg = "(.*" + rid + ".*)";
        ArrayList<String> itemList = FileProcess.readFileByLines(itemReflectionTable, reg);
        if (itemList.size() == 1) {
            return new Integer(itemList.get(0).substring(0, itemList.get(0).indexOf("\t"))).intValue();
        } else {
            // We found more than one item with the given uid, this should not happen.
            return 0;
        }
    }

    @Override
    public String getOriginalId(int reflectedRid) {
        String reg = "(" + reflectedRid + "\t.*)";
        ArrayList<String> itemList = FileProcess.readFileByLines(itemReflectionTable, reg);
        if (itemList.size() == 1) {
            return itemList.get(0).substring(itemList.get(0).indexOf("\t") + 1, itemList.get(0).length()).trim();
        } else {
            // We found more than one item with the given reflectedUid, this should not happen.
            return null;
        }
    }

    @Override
    public Item findItemByRid(String rid) {
        return null;
    }
}
