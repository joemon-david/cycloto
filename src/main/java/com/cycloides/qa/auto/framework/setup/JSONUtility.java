package com.cycloides.qa.auto.framework.setup;

import java.io.FileReader;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cycloides.qa.auto.framework.constants.FilePaths;
import com.cycloides.qa.auto.framework.data.VariableMap;

public class JSONUtility {
	String userDir=System.getProperty("user.dir");
	public JSONObject readAndParse(String fileName)
	{
		
		String path=FilePaths.jsonFolderPath+fileName+".json";
		JSONObject jsonObject = null;
		JSONParser parser=new JSONParser();
		try {
			Object obj=parser.parse(new FileReader(path));
			jsonObject = (JSONObject) obj;           
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	public JSONObject formatRequest(JSONObject request)
	{
		JSONObject formatedObj=new JSONObject();
		Set<String> keys=request.keySet();
		for(String key:keys)
		{
			Object obj=request.get(key);
			JSONObject innerObject = null;
			String formatedValue=null;
			try {
				innerObject = (JSONObject) obj;
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
			if(null!=innerObject)
				{
				innerObject=formatRequest(innerObject);
				formatedObj.put(key, innerObject);
				}
			else 
			{
				if(obj instanceof String )
				{
					formatedValue=formatString(obj.toString());
					formatedObj.put(key, formatedValue);
				}else
					formatedObj.put(key, obj);
				
			}
		}
		return formatedObj;
	}
	
	public String formatString(String input)
	{
		String value=input;
		if(input.contains("+"))
		{
			value="";
			String []tokens=input.split("\\+");
			for(String item:tokens)
				value+=formatString(item);
		}
		
		if(input.startsWith("RandomString("))
		{
			value=getRandomString(input);
		}else if(input.startsWith("$"))
		{
			value=VariableMap.get(input.replace("$", ""));
		}
		return value;
	}
	
	public String getRandomString(String input)
	{
		int count=Integer.parseInt(input.substring(input.indexOf("(")+1,input.indexOf(")")));
		String randString=RandomStringUtils.randomAlphabetic(count);
		return randString.toUpperCase();
	}
	public void printJsonObject(JSONObject obj)
	{
		Set<String> keys=obj.keySet();
		for(String key:keys)
		{

			Object item=obj.get(key);
			JSONObject innerObject = null;
			
			try {
				innerObject = (JSONObject) item;
			} catch (Exception e) {
				
				
			}
			if(null!=innerObject)
				printJsonObject(innerObject);
				else
					System.out.println(key+" : "+item);
		}
	}
	
	public JSONArray convertToJSONObject(String response,int response_code ) {
		System.out.println(response.toString());
		JSONParser parser = new JSONParser();
		JSONObject rep = null;
		JSONArray jsonArray=new JSONArray();
		Object responseObject;
		try {
			responseObject=parser.parse(response);
			if(responseObject instanceof org.json.simple.JSONArray)
				{
				JSONArray interArray=(JSONArray) responseObject;
				for(Object json:interArray)
				{
					JSONObject jsObj=(JSONObject) parser.parse(json.toString());
					jsObj.put("response_code", response_code);
					jsonArray.add(jsObj);
					System.out.println(jsObj.toString());
				}
				}
			else
				{
				rep = (JSONObject) responseObject;
				rep.put("response_code", response_code);
				jsonArray.add(rep);
				System.out.println(rep.toString());
				}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		return jsonArray;
	}
public static void main(String[] args) {
	JSONUtility util= new JSONUtility();
	JSONObject obj=util.readAndParse("api");
	JSONObject formated=util.formatRequest(obj);
	util.printJsonObject(formated);
	
	
//	System.out.println(util.formatString("Auto_+RandomString(5)"));
}

public JSONObject removeItem(JSONObject json,String key)
{
	JSONObject removedJson=new JSONObject();
	for(Object item:json.keySet())
	{
	if(!item.toString().equalsIgnoreCase(key))
		removedJson.put(item, json.get(item));
	}
	return removedJson;
}

}
