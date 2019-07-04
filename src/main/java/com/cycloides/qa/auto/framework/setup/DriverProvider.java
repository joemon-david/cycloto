package com.cycloides.qa.auto.framework.setup;

import org.openqa.selenium.WebDriver;

import com.cycloides.qa.auto.framework.data.SuiteData;
import com.cycloides.qa.auto.framework.dto.ScenarioData;

import cucumber.api.Scenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class DriverProvider {
	
	public  WebDriver driver;
	public  AppiumDriver<MobileElement> appDriver;
	public  Scenario runningScenario;
	public   ScenarioData scenarioData;
	public static boolean isToKillDriverAfterScenario=true;
	public static boolean isToKillMobileDriverAfterScenario=true;
	public  SuiteData suiteData;

}
