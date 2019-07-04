package com.cycloides.qa.auto.framework.setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileReader {

	public static String getPropertyValue(String path,String key) 
	{
		FileInputStream fip=null;
		Properties prop=null;
		String value = null;
		try {
			prop=new Properties();
			fip=new FileInputStream(path);
			prop.load(fip);
			value=prop.getProperty(key);
			fip.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				if(null!=fip)
				fip.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				
			}
		}
		return value;
	}
	
}
