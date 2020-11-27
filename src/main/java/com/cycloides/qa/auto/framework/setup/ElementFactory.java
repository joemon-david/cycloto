package com.cycloides.qa.auto.framework.setup;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class ElementFactory {
	
	public WebElement element(String locatorId,WebDriver driver)
	{
		String locatorValue=new TestBase().getLocator(locatorId);
		if(null==locatorValue)
			locatorValue=locatorId;
		String [] locatorTokens=locatorValue.split("\\|");
		String id=locatorTokens[0];
		String type=locatorTokens[1];
		WebElement element=null;
		By locator=null;
		locator = getWebElementLocator(id, type);
		element=waitForElement(locator,driver);
		return element;
		
		
	}

	public By getWebElementLocator(String id, String type) {
		By locator = null;
		switch (type) {
		case "css":
		case "CSS":
			locator=By.cssSelector(id);
			break;
		case "id":
		case "ID":
			locator=By.id(id);
			break;
		case "xpath":
		case "XPATH":
			locator=By.xpath(id);
			break;
		case "name":
		case "NAME":
			locator=By.name(id);
			break;
		case "linktext":
		case "LINKTEXT":
			locator=By.linkText(id);
			break;
		case "model":
		case "MODEL":
			locator=By.cssSelector("[ng-model='"+id+"']");
			break;
		case "partialmodel":
		case "PARTIALMODEL":
			locator=By.cssSelector("[ng-model*='"+id+"']");
			break;
		case "buttontext":
		case "BUTTONTEXT":
			locator=By.xpath("//button[text()='"+id+"']");
			break;

		default:
			System.out.println("No Matching Element Type found as "+type);
			break;
		}
		return locator;
	}

	public WebElement waitForElement(By locator,WebDriver driver)
	{
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver).
				withTimeout(20, TimeUnit.SECONDS).
				pollingEvery(1, TimeUnit.SECONDS).
				ignoring(NoSuchElementException.class);
		WebElement elem=wait.until(new Function<WebDriver, WebElement>() {
		public WebElement apply(WebDriver driver)
		{return driver.findElement(locator);}
		});
		
		return elem;
	}

	public Keys convertIntoKeys(String key)
	{
		Keys keyTpReturn = null;
		switch (key.trim())
		{
			case "Tab":
				keyTpReturn=Keys.TAB;
				break;
			case "Enter":
				keyTpReturn= Keys.ENTER;
				break;
			default:
				keyTpReturn=Keys.ENTER;
				break;
		}

		return keyTpReturn;
	}

}
