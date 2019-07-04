package com.cycloides.qa.auto.framework.data;

import java.util.HashMap;

import org.json.simple.JSONObject;

import com.cycloides.qa.auto.framework.dto.Request;

public class ScenarioData {

	public HashMap<Integer, Request>requestDataMap;
	public HashMap<Integer, JSONObject>responseDataMap;
	
	
	
	/**
	 * @return the requestDataMap
	 */
	public HashMap<Integer, Request> getRequestDataMap() {
		return requestDataMap;
	}
	/**
	 * @param requestDataMap the requestDataMap to set
	 */
	public void setRequestDataMap(HashMap<Integer, Request> requestDataMap) {
		this.requestDataMap = requestDataMap;
	}
	/**
	 * @return the responseDataMap
	 */
	public HashMap<Integer, JSONObject> getResponseDataMap() {
		return responseDataMap;
	}
	/**
	 * @param responseDataMap the responseDataMap to set
	 */
	public void setResponseDataMap(HashMap<Integer, JSONObject> responseDataMap) {
		this.responseDataMap = responseDataMap;
	}
	
}
