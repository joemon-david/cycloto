package com.cycloides.qa.auto.framework.dto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Response {
	
	private int responseCode;
	private JSONArray responseBody;
	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}
	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	/**
	 * @return the responseBody
	 */
	public JSONArray getResponseBody() {
		return responseBody;
	}
	/**
	 * @param responseBody the responseBody to set
	 */
	public void setResponseBody(JSONArray responseBody) {
		this.responseBody = responseBody;
	}
	
	
	public String getResponseBodyAsString()
	{
		StringBuilder sb= new StringBuilder();
		for(int i=0;i<this.responseBody.size();i++)
		{
			JSONObject jsObj=(JSONObject) responseBody.get(i);
			sb.append(jsObj.toJSONString());
			sb.append("\n");
		}		
		return sb.toString();		
	}
	
	public String getResponseFieldValue(String param) {
		String value = null;
		
		for(int i=0;i<this.responseBody.size();i++)
		{
			JSONObject json=(JSONObject) responseBody.get(i);
			for(Object key:json.keySet())
			{
				if(key.toString().equalsIgnoreCase(param))
					value=json.get(key).toString();
			}
		}
		return value;
	}

}
