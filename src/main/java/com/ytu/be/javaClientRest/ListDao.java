package com.ytu.be.javaClientRest;


import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ListDao {
	public static final String END_POINT = "https://trello-clone-ppm.herokuapp.com";
//	public static final String END_POINT = "https://jsonplaceholder.typicode.com";
	public static final OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
	public static String read(String url){
		Request request = new Request.Builder().url(END_POINT+url).build();
		try{
			Response resp = client.newCall(request).execute();
			return resp.body().string();
		}catch(IOException e){
			e.printStackTrace();
			return "";
		}
	}
	public static String create(String url,Map<String,Object> body){
		try{
			String bodyStr = new ObjectMapper().writeValueAsString(body);
			RequestBody reqBody = RequestBody.create(bodyStr, JSON);
			Request request = new Request.Builder().url(END_POINT+url).post(reqBody).build();
			Response resp = client.newCall(request).execute();
			return resp.body().string();
		}catch(IOException e){
			e.printStackTrace();
			return "";
		}
	}
	public static String update(String url,Map<String,Object> body,int id){
		try{
			String bodyStr =new ObjectMapper().writeValueAsString(body);
			RequestBody reqBody = RequestBody.create(bodyStr,JSON);
			Request request = new Request.Builder().url(END_POINT+url+"/"+id).put(reqBody).build();
			Response resp = client.newCall(request).execute();
			return resp.body().string();
			
		}
		catch(IOException e){
			e.printStackTrace();
			return "";
		}
	}
	public static void delete(String url,int id){
		try{
			Request request = new Request.Builder().url(END_POINT+url+"/"+id).delete().build();
			client.newCall(request).execute();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
