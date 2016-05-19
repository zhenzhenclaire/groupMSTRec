package com.claire.serviceImpl;

import com.claire.entity.Group;
import com.claire.service.PositionService;
import com.claire.util.Mode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by claire on 5/19/16.
 */
public class PositionServiceImpl implements PositionService {
    static Logger logger = Logger.getLogger("Position Service");

    @Override
    public int getDistance(String startingLoc, String destination, Mode mode) {
        int distance = 0;
        try {
            distance = getDurationOrDistance(GoogleMapCall(startingLoc, destination, mode), "distance");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return distance;
    }

    @Override
    public int getDuration(String startingLoc, String destination, Mode mode) {
        int duration = 0;
        try {
            duration = getDurationOrDistance(GoogleMapCall(startingLoc, destination, mode), "duration");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duration;
    }

    @Override
    public ArrayList<String> getDurationOfGroup(Group group, String destination) {
        ArrayList<String> durationList = new ArrayList<>();
        try {
            durationList = getDurationOrDistanceOfGroup(GoogleMapCall(group,destination), "duration");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return durationList;
    }

    @Override
    public ArrayList<String> getDistanceOfGroup(Group group, String destination) {
        ArrayList<String> distanceList = new ArrayList<>();
        try {
            distanceList = getDurationOrDistanceOfGroup(GoogleMapCall(group,destination), "distance");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return distanceList;
    }

    /**
     * Get distance or duration from JSON from GoogleMap API (a group result)
     * @param resultJSON
     * @param type  distance or duration
     * @return  distance(duration) list of the group
     */
    public ArrayList<String> getDurationOrDistanceOfGroup(JSONObject resultJSON, String type){
        ArrayList<String> durationList = new ArrayList<String>();
        ArrayList<String> distanceList = new ArrayList<String>();
        JSONArray ja = resultJSON.getJSONArray("rows");

        for(int i = 0;i < ja.length();i++){
            JSONArray elements = ja.getJSONObject(i).getJSONArray("elements");

            String distance = elements.getJSONObject(0).getJSONObject("distance").getString("text");

            distanceList.add(distance);

            String duration = elements.getJSONObject(0).getJSONObject("duration").getString("text");

            durationList.add(duration);

        }

        if(type.equals("distance")){
            return distanceList;
        }
        else{
            return durationList;
        }
    }

    /**
     * GoogleMap API call method
     * @param group
     * @return  JSONObject of GoogleMap API
     * @throws Exception
     */
    public  JSONObject GoogleMapCall(Group group, String destinationAd) throws Exception {
        //URL to get the distance
        //https://maps.googleapis.com/maps/api/distancematrix/output?parameters

        JSONObject resultJSON = new JSONObject();

        if (group == null) {
            return resultJSON;
        }

        String originsString = "";

        if(group.getNumberOfMembers() != 0){
            if(group.getNumberOfMembers() == 1) {
                originsString = group.getMembers().get(0).getLocation();
            }
            else{
                for(int i = 1;i < group.getNumberOfMembers();i++){
                    originsString += "|" + group.getMembers().get(i).getLocation();
                }
            }
        }
        String origin = java.net.URLEncoder.encode(originsString, "utf-8");

        String destination = destinationAd;
        destination = java.net.URLEncoder.encode(destination, "utf-8");

        String baseURL = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=oriGPS&destinations=desGPS&language=en";
        String URL = baseURL.replaceAll("oriGPS", origin);
        URL = URL.replaceAll("desGPS", destination);

        // Setup the HttpClient
        HttpClient httpclient = new DefaultHttpClient();

        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,600000);
        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 600000);

        // Setup the HTTP GET method
        HttpGet rootServiceDoc = new HttpGet(URL);
        HttpResponse response;

        try {
            // Execute the request
            Thread.sleep(250);
            response = httpclient.execute(rootServiceDoc);
            if (response.getStatusLine().getStatusCode() == 200) {
                String json = returnResponseBody(response);
                resultJSON = new JSONObject(json);
            } else {
                // Release allocated resources
                response.getEntity().consumeContent();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Shutdown the HTTP connection
            httpclient.getConnectionManager().shutdown();
        }
        return resultJSON;
    }

    /**
     * Get back distance or duration result with two single addresses
     * @param resultJSON
     * @param type
     * @return duration or distance result
     */
    public int getDurationOrDistance(JSONObject resultJSON, String type){
        int duration = 0;
        int distance = 0;

        JSONArray ja = resultJSON.getJSONArray("rows");

        for(int i = 0;i < ja.length();i++){
            JSONArray elements = ja.getJSONObject(i).getJSONArray("elements");
            distance = elements.getJSONObject(0).getJSONObject("distance").getInt("value");
            duration = elements.getJSONObject(0).getJSONObject("duration").getInt("value");
        }

        if(type.equals("distance")){
            return distance;
        }
        else{
            return duration;
        }
    }

    public  JSONObject GoogleMapCall(String originAd, String destinationAd, Mode travelMode) throws Exception {
        //URL to get the distance
        //https://maps.googleapis.com/maps/api/distancematrix/output?parameters

        JSONObject resultJSON = new JSONObject();

        String origin = java.net.URLEncoder.encode(originAd, "utf-8");

        String destination = destinationAd;
        destination = java.net.URLEncoder.encode(destination, "utf-8");

        String tmode = travelMode.value();
        tmode = java.net.URLEncoder.encode(tmode, "utf-8");

        String baseURL = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=oriGPS&destinations=desGPS&mode=tmode&language=en";
        String URL = baseURL.replaceAll("oriGPS", origin);
        URL = URL.replaceAll("desGPS", destination);
        URL = URL.replaceAll("tmode", tmode);

        logger.info("URL:" + URL);

        // Setup the HttpClient
        HttpClient httpclient = new DefaultHttpClient();

        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,600000);
        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 600000);

        //URL = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=40.7575,-73.9700&destinations=32.7974,-96.8256";

        // Setup the HTTP GET method
        HttpGet rootServiceDoc = new HttpGet(URL);

        HttpResponse response;

        try {
            // Execute the request
            Thread.sleep(250);
            response = httpclient.execute(rootServiceDoc);
            System.out.println(">> HTTP Status code:" + response.getStatusLine());

            if (response.getStatusLine().getStatusCode() == 200) {

                String json = returnResponseBody(response);

                logger.info("json from response: " + json);

                resultJSON = new JSONObject(json);

            } else {
                // Release allocated resources
                response.getEntity().consumeContent();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Shutdown the HTTP connection
            httpclient.getConnectionManager().shutdown();
        }
        return resultJSON;
    }

    /**
     * Utility method to pass HttpResponse to get the response body
     *
     * @param response
     * @return the response body
     */
    public String returnResponseBody(HttpResponse response) {
        String result = "";
        HttpEntity entity = response.getEntity();
        if (entity == null) result = "";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(entity.getContent()));
            String line = reader.readLine();
            String res = "";
            while (line != null) {
                res = res + line;
                line = reader.readLine();

            }
            result = res;

            reader.close();


        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
