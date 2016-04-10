package com.claire.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

@Component  
public class JSONUtil {  
  
    private static final String DEFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";  
    private static final ObjectMapper mapper;  
  
    static {  
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);  
        mapper = new ObjectMapper();
        mapper.setDateFormat(dateFormat);  
    }  
      
    public static String toJson(Object obj) {  
        try {  
            return mapper.writeValueAsString(obj);  
        } catch (Exception e) {  
        	e.printStackTrace();
            throw new RuntimeException("String TO json failed!");  
	    } 
	}  
	  
	public static <T> T toObject(String json,Class<T> clazz) {  
	    try {  
	        return mapper.readValue(json, clazz);  
	    } catch (IOException e) {  
	    	e.printStackTrace();
	        throw new RuntimeException("Json to String failed!");  
	        }  
	    }  
}