package com.ytu.be.javaClientRest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//    	processRead();
//    	processCreate();
    	processUpdate();
//    	processDelete();
//    	processRead();
    }
    private static void processRead(){
    	System.out.println("Trello List");
    	System.out.println("----------------------------");
    	String listStr = ListDao.read("/list");
    	System.out.println(listStr);
    	try{
    	List<Map<String,Object>> listObj = new ObjectMapper().readValue(listStr, List.class);
    	for(Map<String,Object> list:listObj){
    		System.out.println(list.get("id")+". "+list.get("title")+"");
    		System.out.println("---------------------------");
    		List<Map<String,Object>> cards = (List)list.get("cards");
    		for(Map<String,Object> card:cards){
    			System.out.println("Card "+card.get("id")+" : "+card.get("title"));
    		}
    		System.out.println("=====================================");
    	}
    	}catch(JsonMappingException e){
    		e.printStackTrace();
    	}
    	catch(JsonProcessingException e){
    		e.printStackTrace();
    	}
    	
    }
    private static void processCreate(){
    	Map<String,Object> body = new HashMap<String,Object>();
    	body.put("title", "To-eat-list");
    	body.put("position", 3);
    	String listStr = ListDao.create("/list", body);
    	System.out.println(listStr);
    }
    private static void processUpdate(){
    	Map<String,Object> body = new HashMap<String,Object>();
    	body.put("title", "To-play-list");
    	body.put("position", 3);
    	String listStr = ListDao.update("/list", body, 280);
    	System.out.println(listStr);
    }
    private static void processDelete(){
    	ListDao.delete("/list", 280);
    }
}
