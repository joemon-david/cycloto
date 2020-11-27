package com.cycloides.qa.auto.framework.setup;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class TestBase {
	
	protected Properties config,locator,data,appLocator,appData;
	FileInputStream configOS=null,locatorOS=null,dataOS=null,appLocatorOS=null,appDataOS=null;
	protected static String userDir=System.getProperty("user.dir");
	
	public TestBase() {
		
		try {
			
			config=new Properties();
			locator=new Properties();
			data=new Properties();
			appLocator=new Properties();
			appData=new Properties();
			String configPath=userDir+"\\src\\test\\resources\\config\\suiteConfig.properties";
			String locatorPath=userDir+"\\src\\test\\resources\\config\\locators.properties";
			String dataPath=userDir+"\\src\\test\\resources\\config\\data.properties";
			String appLocatorPath=userDir+"\\src\\test\\resources\\config\\appLocators.properties";
			String appDataPath=userDir+"\\src\\test\\resources\\config\\appData.properties";
			configOS=new FileInputStream(configPath);
			config.load(configOS);
			locatorOS=new FileInputStream(locatorPath);
			locator.load(locatorOS);
			dataOS=new FileInputStream(dataPath);
			data.load(dataOS);
			appLocatorOS=new FileInputStream(appLocatorPath);
			appLocator.load(appLocatorOS);
			appDataOS=new FileInputStream(appDataPath);
			appData.load(appDataOS);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	
	public WebDriver getDriver(String browser)
	{
		WebDriver driver = null;
		
		String chromePath=config.getProperty("chromeDriverPath");
		String firefoxPath=config.getProperty("firefoxDriverPath");
		String edgePath=config.getProperty("edgeDriverPath");
		String iePath=config.getProperty("ieDriverPath");
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",userDir+chromePath);
			driver=new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver",userDir+firefoxPath);
			driver=new FirefoxDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver",userDir+edgePath);
			driver=new EdgeDriver();
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver",userDir+iePath);

			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability("nativeEvents",false);
			ieCapabilities.setCapability("unexpectedAlertBehaviour","accept");
			ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
			ieCapabilities.setCapability("disable-popup-blocking", true);
			ieCapabilities.setCapability("enablePersistentHover", true);
			ieCapabilities.setCapability("ignoreZoomSetting", true);
					driver = new InternetExplorerDriver(ieCapabilities);


		default:
			break;
		}
		return driver;
	}
	
	public DesiredCapabilities getCapabilities() throws MalformedURLException
	{
		DesiredCapabilities caps= new DesiredCapabilities();
		caps.setCapability("deviceName", "");
		caps.setCapability("platformName", "Android");
		caps.setCapability("app", "");// path to the unlock apk
		AndroidDriver driver =new AndroidDriver(new URL("0.0.0.0:4723/wd/hub"),caps);
		return caps;
	}
	
	
	public String getDataValue(String dataID)
	{
		String dataValue=null;
		dataValue=data.getProperty(dataID);
		if(null==dataValue)
			dataValue=dataID;
		return dataValue;
	}
	public String getappDataValue(String dataID)
	{
		String dataValue=null;
		dataValue=appData.getProperty(dataID);
		if(null==dataValue)
			dataValue=dataID;
		return dataValue;
	}
	public String getLocator(String locatorID)
	{
		String locatorValue=null;
		locatorValue=locator.getProperty(locatorID);
		if(null==locatorValue)
			locatorValue=locatorID;
		return locatorValue;
	}
	public String getAppLocator(String locatorID)
	{
		String locatorValue=null;
		locatorValue=appLocator.getProperty(locatorID);
		if(null==locatorValue)
			locatorValue=locatorID;
		return locatorValue;
	}

	public boolean isMessageDisplayed(String messageText,WebDriver driver)
	{
		return driver.findElement(By.xpath("//*[contains(text(),'"+messageText+"')]")).isDisplayed();
	}
	
	public void Delay(int seconds)
	{
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public void moveElementByCordinates(WebElement elem,String xyCords,WebDriver driver)
	{
		Actions act=new Actions(driver);
		int x=Integer.parseInt(xyCords.split("\\|")[0]);
		int y=Integer.parseInt(xyCords.split("\\|")[1]);
		act.dragAndDropBy(elem, x, y).build().perform();
		
	}
	
}
