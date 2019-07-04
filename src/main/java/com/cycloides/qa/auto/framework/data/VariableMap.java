package com.cycloides.qa.auto.framework.data;

import java.util.HashMap;

public class VariableMap {
	
	public static HashMap<String, String>map=new HashMap<String,String>();
	
	public static void put(String key,String value) {
		map.put(key, value);
	}
	
	public static String get(String key)
	{
		if(key.startsWith("$"))
			key=key.replace("$", "");
		return map.get(key);
	}

}
