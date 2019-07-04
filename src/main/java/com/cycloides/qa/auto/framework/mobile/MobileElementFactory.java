package com.cycloides.qa.auto.framework.mobile;

import com.cycloides.qa.auto.framework.setup.DriverProvider;
import com.cycloides.qa.auto.framework.setup.TestBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MobileElementFactory {
	private DriverProvider provider;
	
	
	public MobileElement mElements(String locatorId,AppiumDriver<MobileElement> driver)
	{
		String locatorValue=new TestBase().getAppLocator(locatorId);
		
		if(locatorValue==null) {
			locatorValue=locatorId;
		}
		String [] locatorTokens=locatorValue.split("\\|");
		String id=locatorTokens[0].trim();
		String type=locatorTokens[1].trim();
		MobileElement element=null;
		
		switch(type) {
		case "id":
		case "ID":
			element=provider.appDriver.findElementById(id);
			break;
		case "xpath":
		case "XPATH":
			element=provider.appDriver.findElementByXPath(id);
			break;
		default:
			System.out.println("No Matching Element Type found as "+type);
			break;
			}
//		element=waitForMobileElement(element,provider.appDriver);
			return element;
	}


//	s


	
}
