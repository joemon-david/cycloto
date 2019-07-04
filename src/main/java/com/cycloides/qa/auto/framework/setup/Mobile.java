package com.cycloides.qa.auto.framework.setup;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

public class Mobile {
	
	AppiumDriver<MobileElement> driver;
	String path;
	
	public void setUp()
	{
		path=System.getProperty("user.dir");
		DesiredCapabilities cap=new DesiredCapabilities();
		
		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName", "ZY3223LGS2");
		cap.setCapability("app", path+"//app//Policaro_STG_20.06.apk");// Need to give the apk path
		cap.setCapability("autoGrantPermissions", "True");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.FULL_RESET, "True");
		try {
			driver=new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		delay(5);
//		allowAppPermission();
		driver.launchApp();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		delay(5);
		
//		System.out.println(driver.getPageSource());
		
		List<MobileElement>icons=driver.findElements(MobileBy.id("com.policaro.myservice:id/dashboard_brand_iv_icon"));
		System.out.println("Total icons "+icons.size());
		for(MobileElement elem:icons)
			elem.click();
		
		
//		MobileElement el1 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.GridView/android.widget.LinearLayout[8]/android.widget.ImageView");
//		el1.click();
//		MobileElement el2 = (MobileElement) driver.findElementById("com.policaro.myservice:id/fragment_service_center_details_request_a_service_appointment_ll");
//		el2.click();
//		MobileElement el3 = (MobileElement) driver.findElementById("com.policaro.myservice:id/frag_login_et_email");
//		el3.sendKeys("joemon.david@cycloides.co.in");
//		MobileElement el4 = (MobileElement) driver.findElementById("com.policaro.myservice:id/login_passwoed_et");
//		el4.sendKeys("123456");
//		MobileElement el5 = (MobileElement) driver.findElementById("com.policaro.myservice:id/frag_login_bt_login");
//		el5.click();
		
		MobileElement el2 = (MobileElement) driver.findElementById("com.policaro.myservice:id/fragment_service_center_details_request_a_service_appointment_ll");
		el2.click();
		delay(2);
		MobileElement el3 = (MobileElement) driver.findElementById("com.policaro.myservice:id/frag_login_et_email");
		el3.sendKeys("joemon.david@cycloides.co.in");
		delay(2);
		MobileElement el5 = (MobileElement) driver.findElementById("com.policaro.myservice:id/login_passwoed_et");
		el5.sendKeys("123456");
		delay(2);
		MobileElement el6 = (MobileElement) driver.findElementById("com.policaro.myservice:id/frag_login_bt_login");
		el6.click();		
		delay(4);
		
		driver.findElementById("com.policaro.myservice:id/fragment_service_center_details_request_a_service_appointment_ll").click();
		
		delay(2);
		MobileElement el7 = (MobileElement) driver.findElementById("com.policaro.myservice:id/Carimage");
		el7.click();
		delay(2);
		MobileElement el8 = (MobileElement) driver.findElementById("com.policaro.myservice:id/car_select_continue");
		el8.click();
		delay(2);
		MobileElement el9 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.LinearLayout[4]/android.widget.RelativeLayout/android.widget.TextView");
		el9.click();
		delay(2);
		MobileElement el10 = (MobileElement) driver.findElementById("com.policaro.myservice:id/service_type_continue");
		el10.click();
		delay(2);
		MobileElement el11 = (MobileElement) driver.findElementByXPath("//com.prolificinteractive.materialcalendarview.CalendarPagerView[@content-desc=\"Calendar\"]/android.widget.CheckedTextView[25]");
		el11.click();
		delay(2);
		MobileElement el12 = (MobileElement) driver.findElementById("com.policaro.myservice:id/service_date_continue");
		el12.click();
		delay(2);
		MobileElement el13 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout[2]/android.widget.TextView");
		el13.click();
		delay(2);
		MobileElement el14 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.TextView");
		el14.click();
		delay(2);
		MobileElement el15 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout[6]/android.widget.TextView");
		el15.click();
		delay(2);
		(new TouchAction(driver)).press(PointOption.point(917,1238)).moveTo(PointOption.point(919,685)).release().perform();
		delay(2); 
		MobileElement el16 = (MobileElement) driver.findElementById("com.policaro.myservice:id/vehicle_radiobutton");
		el16.click();
		delay(2);
		MobileElement el17 = (MobileElement) driver.findElementById("com.policaro.myservice:id/own_radiobutton");
		el17.click();
		delay(2);
		(new TouchAction(driver)).press(PointOption.point(42,1599)).moveTo(PointOption.point(901,959)).release().perform();
		delay(2);		  
		MobileElement el18 = (MobileElement) driver.findElementById("com.policaro.myservice:id/appointment_continue");
		el18.click();
		delay(2);
		MobileElement el19 = (MobileElement) driver.findElementById("com.policaro.myservice:id/view_appointments_button");
		el19.click();
		delay(2);
//		MobileElement el20 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.Button[2]");
//		el20.click();
//		delay(2);
//		MobileElement el21 = (MobileElement) driver.findElementById("com.policaro.myservice:id/yes_button_dialog_cancel");
//		el21.click();

		delay(2);	
		
		
		driver.quit();

	}
	
	public void setUpWeb()
	{
		path=System.getProperty("user.dir");
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "ZY3223LGS2");
		try {
			driver=new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://digitizeddealers.com:8099/#/login");
		
	}
	
	public void delay(int seconds)
	{
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void allowAppPermission()
	{
		System.out.println("Total No of permission pop-ups "+driver.findElements(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).size());
		while(driver.findElements(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).size()>0)
		{
			driver.findElement(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).click();
		}
	}
	public static void main(String[] args) {
		new Mobile().setUp();
	}

}
