package com.claire.daoImpl;

import com.claire.dao.ItemDao;
import com.claire.entity.Item;
import com.claire.util.Config;
import com.claire.util.FileProcess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by admin on 2016/4/5.
 */
public class ItemDaoImpl implements ItemDao {
    static Logger logger = Logger.getLogger("ItemDaoImpl");
    String itemFile = Config.itemFile;
    String itemReflectionTable = Config.itemReflectionTable;
    String itemClusteringData = Config.itemClusteringData;

    public static Map<String, String> itemListById;
    public static Map<String, String> itemListByName;


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
    public void makeItemClusteringData() {
        logger.info("Start preparing item clustering data.");
        // read from dataPath/userFile
		/*
	     * line 1: {"business_id": "5UmKMjUEUNdYWqANhGckJw", "full_address": "4734 Lebanon Church Rd\nDravosburg, PA 15034", "hours": {"Friday": {"close": "21:00", "open": "11:00"}, "Tuesday": {"close": "21:00", "open": "11:00"}, "Thursday": {"close": "21:00", "open": "11:00"}, "Wednesday": {"close": "21:00", "open": "11:00"}, "Monday": {"close": "21:00", "open": "11:00"}}, "open": true, "categories": ["Fast Food", "Restaurants"], "city": "Dravosburg", "review_count": 4, "name": "Mr Hoagie", "neighborhoods": [], "longitude": -79.9007057, "state": "PA", "stars": 4.5, "latitude": 40.3543266, "attributes": {"Take-out": true, "Drive-Thru": false, "Good For": {"dessert": false, "latenight": false, "lunch": false, "dinner": false, "brunch": false, "breakfast": false}, "Caters": false, "Noise Level": "average", "Takes Reservations": false, "Delivery": false, "Ambience": {"romantic": false, "intimate": false, "classy": false, "hipster": false, "divey": false, "touristy": false, "trendy": false, "upscale": false, "casual": false}, "Parking": {"garage": false, "street": false, "validated": false, "lot": false, "valet": false}, "Has TV": false, "Outdoor Seating": false, "Attire": "casual", "Alcohol": "none", "Waiter Service": false, "Accepts Credit Cards": true, "Good for Kids": true, "Good For Groups": true, "Price Range": 1}, "type": "business"}
	     * Item_clustering,
	     * "business_id","review_count","state","stars","Take-out","Drive-Thru","Caters","Noise Level","Has TV","Outdoor Seating","Alcohol","Waiter Service","Good for Kids","Good For Groups","Price Range",
	     * "5UmKMjUEUNdYWqANhGckJw",4,"PA",4.5,true,false,false,"average",false,false,"none",false,true,true,1
	     * Changed item_clustering:
	     * "business_id","review_count","state","stars","Caters","Noise Level","Outdoor Seating","Alcohol","Waiter Service","Good for Kids","Good For Groups","Price Range",
	     * "5UmKMjUEUNdYWqANhGckJw",4,"PA",4.5,false,"average",false,"none",false,true,true,1
	     */
        String[] regs = new String[11];
        regs[0] = "\"business_id\": \"(.*)\", \"full_address\"";
        regs[1] = "\"review_count\": (.*), \"name\"";
        regs[2] = "\"state\": \"(.*)\", \"stars\"";
        regs[3] = "\"stars\": (.*), \"latitude\"";

        //regs[4] = "\"Take-out\": (.*), \"Drive-Thru\"";
//        regs[4] = "\"Drive-Thru\": (.*), \"Good For\"";
        regs[4] = "\"Caters\": (.*), \"Noise Level\"";
        regs[5] = "\"Noise Level\": \"(.*)\", \"Takes Reservations\"";
//        regs[6] = "\"Has TV\": (.*), \"Outdoor Seating\"";
        regs[6] = "\"Outdoor Seating\": (.*), \"Attire\"";
        regs[7] = "\"Alcohol\": \"(.*)\", \"Waiter Service\"";
        regs[8] = "\"Good for Kids\": (.*), \"Good For Groups\"";
        regs[9] = "\"Good For Groups\": (.*), \"Price Range\"";
        regs[10] = "\"Price Range\": (.*)}, \"type\"";

        ArrayList<String> itemList = FileProcess.readFileByLines(itemFile, regs);
        ArrayList<String> itemClusteringList = new ArrayList<String>();

        // Generate userid <int>
        for(int i = 1;i <= itemList.size();i++){
            itemClusteringList.add(itemList.get(i - 1) + "\n");
        }

        // Generate userid <String> TAB userid <int> userReflectionTable
        FileProcess.writeToFile(itemClusteringList,itemClusteringData);
        logger.info("Done.");
    }


    @Override
    public int getReflectedId(String rid) {


        generateItemList();
        return (new Integer (itemListByName.get(rid))).intValue();

        /* No need to read the file every time
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
        */
    }

    @Override
    public String getOriginalId(int reflectedRid) {

        generateItemList();
        return itemListById.get(reflectedRid + "");

        /* No need to read the file every time
        String reg = "(" + reflectedRid + "\t.*)";
        ArrayList<String> itemList = FileProcess.readFileByLines(itemReflectionTable, reg);
        if (itemList.size() == 1) {
            return itemList.get(0).substring(itemList.get(0).indexOf("\t") + 1, itemList.get(0).length()).trim();
        } else {
            // We found more than one item with the given reflectedUid, this should not happen.
            return null;
        }
        */
    }

    @Override
    public void generateItemList() {

        if (itemListById == null || itemListByName == null ) {

            logger.info("Need to generate userList map again.");
            Map<String, String> itemMapById = new HashMap<String, String>();
            Map<String, String> itemMapByName = new HashMap<String, String>();

            ArrayList <String> itemList = FileProcess.readFileByLines(itemReflectionTable, "(.*)");

            logger.info("itemList.size(): " + itemList.size() );

            for(String str:itemList){
                String uid = str.substring(0,str.indexOf("\t"));
                String uName = str.substring(str.indexOf("\t")+1,str.length()).trim();
                itemMapById.put(uid, uName);
                itemMapByName.put(uName, uid);
            }
            itemListById = itemMapById;
            itemListByName = itemMapByName;

        }
    }
    @Override
    public Item findItemByRid(String rid) {
        return null;
    }
}