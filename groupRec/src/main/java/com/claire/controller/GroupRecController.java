package com.claire.controller;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.claire.entity.Group;
import com.claire.entity.Item;
import com.claire.entity.RecStore;
import com.claire.entity.User;
import com.claire.util.Config;
import com.claire.util.JSONUtil;
import com.claire.util.Mode;
import com.claire.util.Util;

@Controller
@RequestMapping(value = "/rec")
public class GroupRecController {
    
	private static Logger log = Logger.getLogger(GroupRecController.class);
	
	@Autowired
	JSONUtil jsonutil;
	

	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	@ResponseBody
	public String ListCookBook(HttpServletRequest req, HttpServletResponse resp) {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json;encode=utf8");
		JSONObject result=new JSONObject();

		result.put("ret", 1);
		result.put("cookbooks", "user1");
		return  result.toString();
	}
	
	@RequestMapping(value = "/getGroup", method = RequestMethod.GET)
	@ResponseBody
	public String GetGroup(HttpServletRequest req, HttpServletResponse resp) {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json;encode=utf8");
		JSONObject result=new JSONObject();

		String groupID = RecStore.getInstance().groupID;
		Group group = RecStore.getInstance().group;
		//result.put("groupID", groupID);
		//result.put("group",group);
		//return result.toString();
		
		return jsonutil.toJson(group);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String login(HttpServletRequest req, @RequestBody String data,HttpServletResponse resp) {
		
		/*
		 * expected json format: 
			{
				"user":"zhengxil",
				"password":"pass4you",
				"isGroupOwner":"true"
			}
		 * 
		 */
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json;encode=utf8");
		JSONObject result=new JSONObject();
		try {
			if (null != data && data.trim().length() > 0) {
				JSONObject request = new JSONObject(data);
				String user = null;
				String isGroupOwner = null;
				String password =null;
				
				if(request.has("user"))
					user=request.getString("user");
				if(request.has("password"))
					password=request.getString("password");
				if (request.has("isGroupOwner")) 
					isGroupOwner = request.getString("isGroupOwner");
				
				result.put("user", user);
				log.info("user:"+user);
				result.put("password", password);
				log.info("password:"+password);
				result.put("isGroupOwner", isGroupOwner);
				log.info("isGroupOwner: "+isGroupOwner);
				
				if("true".equals(isGroupOwner)) {
					String groupID = RecStore.getInstance().groupID;
					result.put("groupID", groupID);
				}
				return result.toString();

				
			} else {
				result.put("ret", "400");
				result.put("msg", "The request data is null");
				log.info("createGroup result:"+result);
				return result.toString();
			}
			
		} catch (Exception e) {
			result.put("ret", "500");
			result.put("msg", e.getMessage());
			log.info("createGroup result:"+result);
			return result.toString();
		}
	}
	
	@RequestMapping(value = "/createGroup", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String createGroup(HttpServletRequest req, @RequestBody String data,HttpServletResponse resp) {
		
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json;encode=utf8");
		JSONObject result=new JSONObject();
		try {
			if (null != data && data.trim().length() > 0) {
				JSONObject request = new JSONObject(data);
				String gatheringTime = null;
				String numberOfUsers = null;
				String departureTime = null;
				String user = null;
				String mode = null;
				
				if(request.has("gatheringTime"))
					gatheringTime=request.getString("gatheringTime");
				if(request.has("departureTime"))
					departureTime=request.getString("departureTime");
				if (request.has("numberOfUsers")) 
					numberOfUsers = request.getString("numberOfUsers");
				
				if (request.has("user")) 
					user = request.getString("user");
				
				if (request.has("mode")) 
					mode = request.getString("mode");
				
				result.put("gatheringTime", gatheringTime);
				log.info("gatheringTime:"+gatheringTime);
				result.put("numberOfUsers", numberOfUsers);
				log.info("numberOfUsers:"+numberOfUsers);
				result.put("departureTime", departureTime);
				log.info("departureTime: "+departureTime);
				
				result.put("user", user);
				log.info("user: "+user);
				result.put("mode", mode);
				log.info("mode: "+mode);
				
				 

		        User u = new User();
				u.setStartTime(departureTime);
				u.setName(user);
				
				u.setLocation(Config.getInstance().userLocationList.get(0));
				
				if (mode != null && Mode.WALKING.value().equals(mode)) {
					u.setTransportMode(Mode.WALKING);
				}
				if (mode != null && Mode.BICYCLING.value().equals(mode)) {
					u.setTransportMode(Mode.BICYCLING);
				}
				if (mode != null && Mode.DRIVING.value().equals(mode)) {
					u.setTransportMode(Mode.DRIVING);
				}
				
				
				RecStore.getInstance().createGroup( u,  gatheringTime, new Integer(numberOfUsers).intValue());
				
				
				return result.toString();

				
			} else {
				result.put("ret", "400");
				result.put("msg", "The request data is null");
				log.info("createGroup result:"+result);
				return result.toString();
			}
			
		} catch (Exception e) {
			result.put("ret", "500");
			result.put("msg", e.getMessage());
			log.info("createGroup result:"+result);
			return result.toString();
		}
	}
	
	@RequestMapping(value = "/joinGroup", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String joinGroup(HttpServletRequest req, @RequestBody String data,HttpServletResponse resp) {
		
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json;encode=utf8");
		JSONObject result=new JSONObject();
		try {
			if (null != data && data.trim().length() > 0) {
				JSONObject request = new JSONObject(data);
				String departureTime = null;
				String user = null;
				String mode = null;
				
				if(request.has("departureTime"))
					departureTime=request.getString("departureTime");
				
				if (request.has("user")) 
					user = request.getString("user");
				
				if (request.has("mode")) 
					mode = request.getString("mode");
				
				result.put("departureTime", departureTime);
				log.info("departureTime: "+departureTime);
				
				result.put("user", user);
				log.info("user: "+user);
				result.put("mode", mode);
				log.info("mode: "+mode);

		        
		        User u = new User();
				u.setStartTime(departureTime);
				u.setName(user);
				
				u.setLocation(Config.getInstance().userLocationList.get(RecStore.getInstance().group.getMembers().size()));
				
				if (mode != null && Mode.WALKING.value().equals(mode)) {
					u.setTransportMode(Mode.WALKING);
				}
				if (mode != null && Mode.BICYCLING.value().equals(mode)) {
					u.setTransportMode(Mode.BICYCLING);
				}
				if (mode != null && Mode.DRIVING.value().equals(mode)) {
					u.setTransportMode(Mode.DRIVING);
				}
				
				
				RecStore.getInstance().joinGroup(u);
				
				
				return result.toString();

				
			} else {
				result.put("ret", "400");
				result.put("msg", "The request data is null");
				log.info("createGroup result:"+result);
				return result.toString();
			}
			
		} catch (Exception e) {
			result.put("ret", "500");
			result.put("msg", e.getMessage());
			log.info("createGroup result:"+result);
			return result.toString();
		}
	}
	
	@RequestMapping(value = "/updateRight", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String updateRight(HttpServletRequest req, @RequestBody String data,HttpServletResponse resp) {
		
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json;encode=utf8");
		JSONObject result=new JSONObject();
		try {
			if (null != data && data.trim().length() > 0) {
				JSONObject request = new JSONObject(data);
				
				// Get user list from group
				List <User> userList = RecStore.getInstance().group.getMembers();
				
				// Update right for each user
				
				for (User user : userList) {
					if(request.has(user.getName()))
						user.setRight(Integer.parseInt(request.getString(user.getName())));
				}
				return result.toString();
			} else {
				result.put("ret", "400");
				result.put("msg", "The request data is null");
				log.info("createGroup result:"+result);
				return result.toString();
			}
			
		} catch (Exception e) {
			result.put("ret", "500");
			result.put("msg", e.getMessage());
			log.info("createGroup result:"+result);
			return result.toString();
		}
	}
	
	@RequestMapping(value = "/recommend", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String recommend(HttpServletRequest req, @RequestBody String data,HttpServletResponse resp) {
		
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json;encode=utf8");
		JSONObject result=new JSONObject();
		try {
			if (null != data && data.trim().length() > 0) {
				JSONObject request = new JSONObject(data);
				String flag = null;
				if(request.has("flag"))
					flag=request.getString("flag");
				if("updateRightAndRecommend".equals(flag)){
					// Get user list from group
					List <User> userList = RecStore.getInstance().group.getMembers();
					
					// Update right for each user
					
					for (User user : userList) {
						if(request.has(user.getName()))
							user.setRight(Integer.parseInt(request.getString(user.getName())));
					}
				}
				
					Group group = RecStore.getInstance().group;
					Item item = new Item();
					item.setName("");
					item.setLocation(Config.getInstance().itemLocationList.get(0));
					group.setRecommendation(item);
					List <User> userList = RecStore.getInstance().group.getMembers();
					
					for (User user : userList) {
						user.setDistance(getDistance(user.getLocation(), item.getLocation()));
						user.setTravelTime(getTravelTime(user.getLocation(), item.getLocation(), user.getTransportMode()));
					}
					
				
				return result.toString();
			} else {
				result.put("ret", "400");
				result.put("msg", "The request data is null");
				log.info("createGroup result:"+result);
				return result.toString();
			}
			
		} catch (Exception e) {
			result.put("ret", "500");
			result.put("msg", e.getMessage());
			log.info("createGroup result:"+result);
			return result.toString();
		}
	}
	
	public static String getDistance(String startLocation, String endLocation){
		//TODO Invoke google API to get the distance
		return Config.getInstance().distanceList.get(Integer.parseInt(Util.getRandomString(1)));
		
	}
	
	public static long getTravelTime(String startLocation, String endLocation, Mode mode){
		//TODO Invoke google API to get the distance
		return Config.getInstance().travelTimeList.get(Integer.parseInt(Util.getRandomString(1))).longValue();
	}
}
