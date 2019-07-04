package com.cycloides.qa.auto.framework.mobile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.cycloides.qa.auto.framework.utils.CommonUtility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;

public class MobileUtility {
	Properties mobileConfig= new Properties();
	String configPath=System.getProperty("user.dir")+"\\src\\test\\resources\\config\\appconfig.properties";
	String path;
	
	public DesiredCapabilities getCapability() throws IOException
	{
		
		
		mobileConfig.load(new FileInputStream(configPath));
		path=System.getProperty("user.dir");
		DesiredCapabilities cap=new DesiredCapabilities();
		
		cap.setCapability("platformName", mobileConfig.get("platformName"));
		cap.setCapability("deviceName", mobileConfig.get("deviceName"));
		cap.setCapability("app", mobileConfig.get("app"));
		cap.setCapability("autoGrantPermissions", mobileConfig.get("autoGrantPermissions"));
//		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, mobileConfig.get("MobileCapabilityType.AUTOMATION_NAME"));
		cap.setCapability(MobileCapabilityType.FULL_RESET, mobileConfig.get("MobileCapabilityType.FULL_RESET"));
		return cap;	
		
	}
		
	public void swipe(String swipe,AppiumDriver<MobileElement>appDriver ) throws InterruptedException {
		appDriver.context("NATIVE_APP");
		Dimension dimensions = appDriver.manage().window().getSize();
		 int initialheight= 0;
		 int initialwidth=0;
		 int maxheight = (int) dimensions.getHeight();
		 int maxwidth = (int) dimensions.getWidth();
		 int halfmaxheight = (int) dimensions.getHeight() / 2;
		 int halfmaxwidth = (int) dimensions.getWidth() / 2;
		if(swipe!=null) {
			switch(swipe) {
			case "LEFT_TO_RIGHT": (new TouchAction(appDriver)).press(PointOption.point(initialwidth,halfmaxheight)).waitAction().moveTo(PointOption.point(maxwidth-25,halfmaxheight)).release().perform();
			break;
			case "TOP_TO_BOTTOM":  (new TouchAction(appDriver)).press(PointOption.point(halfmaxwidth,initialheight)).waitAction().moveTo(PointOption.point(halfmaxwidth,maxheight)).release().perform();
			break;
			case "RIGHT_TO_LEFT":  (new TouchAction(appDriver)).press(PointOption.point(maxwidth,halfmaxheight)).waitAction().moveTo(PointOption.point(initialwidth,halfmaxheight)).release().perform();
			break;
			case "BOTTOM_TO_TOP":  (new TouchAction(appDriver)).press(PointOption.point(maxwidth,halfmaxheight)).waitAction().moveTo(PointOption.point(initialwidth,halfmaxheight)).release().perform();
			break;
			default:
				System.out.println("No Swipe action defined for the input "+swipe);
			}
		}
	}
	
	
	public String readToastMessage(String expectedValue,AppiumDriver<MobileElement>appDriver ) throws Throwable {
		//Thread.sleep(3000);
		  String imgName = new CommonUtility().takeScreenShot(appDriver);
		  String result = null;
		  File imageFile = new File( imgName);
		  System.out.println("Image name is :" + imageFile.toString());
		  ITesseract instance = new Tesseract();
		  File tessDataFolder = LoadLibs.extractTessResources("tessdata"); 
		  instance.setDatapath(tessDataFolder.getAbsolutePath());
		  result = instance.doOCR(imageFile).trim();
		  System.out.println(result);		 
		  Assert.assertTrue("Validate the "+result+" contains "+expectedValue,result.contains(expectedValue.trim()));
		  
		  
		  return result;
		  
	}		 
}
