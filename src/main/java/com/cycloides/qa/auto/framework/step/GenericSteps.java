package com.cycloides.qa.auto.framework.step;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import com.cycloides.qa.auto.framework.dto.ScenarioData;
import com.cycloides.qa.auto.framework.setup.*;
import cucumber.api.Result;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GenericSteps extends TestBase{
	String configPath=System.getProperty("user.dir")+"//src//test//resources//config//suiteConfig.properties";
	private DriverProvider provider;
	public GenericSteps(DriverProvider provider) {
		// TODO Auto-generated constructor stub
		this.provider=provider;
		
		System.out.println("Generic Steps Initialized,.... ");
	}
	
	
	private ElementFactory elem=new ElementFactory();
	
	
	
	
	
	
	
	@Given("^User is on Page \"([^\"]*)\"$")
	public void user_is_on_Page(String arg0) {
		
		String url=config.getProperty(arg0);
		provider.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if(!(provider.driver instanceof EdgeDriver))
		{
			provider.driver.manage().window().maximize();
		}
		
		provider.driver.get(url);
	}
	
	@Given("^User directly move to the Page \"([^\"]*)\"$")
	public void user_move_to_the_page(String arg0) {		
		String url=config.getProperty(arg0);		
		provider.driver.get(url);
	}

	@When("^User Enter \"([^\"]*)\" in field \"([^\"]*)\"$")
	public void user_Enter_in_field(String arg1, String arg2)  {
		elem.element(arg2,provider.driver).clear();
		elem.element(arg2,provider.driver).sendKeys(getDataValue(arg1));
	  
	}
	@When("^User Upload the image  \"([^\"]*)\" in the field \"([^\"]*)\"$")
	public void user_Upload_Image(String arg1, String arg2)  {
		
		elem.element(arg2,provider.driver).sendKeys(userDir+getDataValue(arg1));
	  
	}	
	
	@Then("^User Click on \"([^\"]*)\"$")
	public void user_Click_on(String arg1)  {
		elem.element(arg1,provider.driver).click();
	  Delay(1);
	}

	@Then("^User Choose \"([^\"]*)\" from dropdown \"([^\"]*)\"$")
	public void user_Choose_from_dropdown(String arg1, String arg2) {
	   Select dropdown=new Select(elem.element(arg2,provider.driver));
	   dropdown.selectByVisibleText(arg1);
	}

	@Then("^User Check the item \"([^\"]*)\" is displayed$")
	public void user_Check_the_item_is_displayed(String arg1)  {
		assertTrue(elem.element(arg1,provider.driver).isDisplayed());	   
	}
	@Then("^User Close the browser$")
	public void user_Close_the_browser()  {
		provider.driver.quit();
	}
	
	@Then("^User Verify the text of item \"([^\"]*)\" is \"([^\"]*)\"$")
	public void user_Verify_the_text_of_item_is(String arg1, String arg2) {
	   String text= elem.element(arg1,provider.driver).getText();
		System.out.println("Value of the text is "+text);
	  assertTrue(text.equalsIgnoreCase(getDataValue(arg2)));
	}
	
	@Then("^User Verify the text of item  \"([^\"]*)\" contains \"([^\"]*)\"$")
	public void user_Verify_the_text_of_item_contains(String arg1, String arg2) {
	   String text= elem.element(arg1,provider.driver).getText();
	  assertTrue(text.contains(getDataValue(arg2)));
	}
	
	@Then("^User Verify that the message \"([^\"]*)\" is displayed on the page$")
	public void user_Verify_the_message_is_displayed(String arg1) {
	   
	  assertTrue(isMessageDisplayed(arg1,provider.driver));
	}
	@Then("^User Takes a Screenshot$")
	public void user_takes_screenshot()  {
//		String src=provider.driver.getPageSource();
//		System.out.println(src);
		createScreenshot();
	}

	@Then("^User Want to keep the session$")
	public void user_want_to_keep_the_session()  {
		provider.isToKillDriverAfterScenario=false;
		System.out.println("User want to keep the session the value for isToKillDriverAfterScenario:"+provider.isToKillDriverAfterScenario);
	}
	private void createScreenshot() {
		try {
			provider.runningScenario.write("Current Page URL is " + provider.driver.getCurrentUrl());
            byte[] screenshot = ((TakesScreenshot)provider.driver).getScreenshotAs(OutputType.BYTES);
            provider.runningScenario.embed(screenshot, "image/png");  // Stick it in the report
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.out.println(somePlatformsDontSupportScreenshots.getMessage());
        } catch (ClassCastException cce) {
            cce.printStackTrace();
        }
	}
	
	@Then("^User wants to report the result in testlink in testcase \"([^\"]*)\"$")
	public void user_set_the_testlink_testcase(String arg1)  {
		String propertyPath=System.getProperty("user.dir")+"\\src\\test\\resources\\config\\testlink.properties";
		String status=FileReader.getPropertyValue(propertyPath, "ENABLE");
		if(status.equalsIgnoreCase("True"))
		{
			provider.scenarioData.setReportResultInTestLink(true);
			provider.scenarioData.setTestLinkId(arg1);
		}else
			System.out.println("Test Result will not be added to TestLink as the flag is disabled.");
		
		
	}
	@Given("^User wants to report the result in testlink in testcase  \"([^\"]*)\"$")
	public void user_set_the_testlink_test_case_of_this_scenario_as(String arg1)  {
		provider.scenarioData.setReportResultInTestLink(true);		
		provider.scenarioData.setTestLinkId(arg1);
	}
	
	

	@When("^User Verify \"([^\"]*)\" has \"([^\"]*)\" value by default$")
	public void user_Verify_has_value_by_default(String arg1, String arg2) {
	    verifyAttributeValue(arg1, arg2);
	}

	

	@When("^User Verify that the value of the \"([^\"]*)\" changed to \"([^\"]*)\"$")
	public void user_Verify_that_the_value_of_the_changed_to(String arg1, String arg2) {
	    
		verifyAttributeValue(arg1, arg2);
	}

	private void verifyAttributeValue(String arg1, String arg2) {
		String loc=getLocator(arg2);
	    String attr=loc.split("\\|")[1];
	    String expectedValue=loc.split("\\|")[0];
	   String act=elem.element(arg1,provider.driver).getAttribute(attr);
	   assertTrue(act.contains(expectedValue));
	}

	@Then("^User should land on the page \"([^\"]*)\"$")
	public void user_should_land_on_the_page(String arg1)  {
	    boolean isExpectedPage=provider.driver.getCurrentUrl().contains(getDataValue(arg1));
	    assertTrue(isExpectedPage);
	}
	
	@When("^User Verify the \"([^\"]*)\" is not displayed$")
	public void user_Verify_the_is_not_displayed(String arg1) {
	   boolean isNotDisplayed=true;
	   try {
		if(elem.element(arg1,provider.driver).isDisplayed())
			isNotDisplayed=false;
	} catch (Exception e) {
		// TODO: handle exception
	}	   
	   
		assertTrue(isNotDisplayed);
	}
	@Then("^User Move the \"([^\"]*)\" to \"([^\"]*)\"$")
	public void user_Move_the_to(String arg1, String arg2)  {
	   moveElementByCordinates(elem.element(arg1,provider.driver), arg2,provider.driver);
	}



	@Before()
	public void init_Scenario(Scenario scenario)
	{
		provider.runningScenario=scenario;
		provider.scenarioData=new ScenarioData();
		provider.scenarioData.setScenarioName(scenario.getName());
		String browser=new FileReader().getPropertyValue(configPath,"browser");
		String uiRunStatus=new FileReader().getPropertyValue(configPath,"isToRunUICases");
		if(null!=uiRunStatus && uiRunStatus.equalsIgnoreCase("false")) {
			System.out.println("Configuration related to UI will be skipped");
		}else
		{
			if(null!=System.getenv("browser"))
			{
				browser=System.getenv("browser");
				System.out.println("Found the browser Env property as-"+browser);


			}
			if(null==browser)
				browser="chrome";

			if(null==provider.driver)
			{
				if(browser.equalsIgnoreCase("html"))
					provider.driver=new HtmlUnitDriver(false);
				else if(browser.equalsIgnoreCase("phantom"))
				{
					String path=(System.getProperty("user.dir")+"//lib//phantomjs.exe");
					System.out.println("Phanto JS Path Set "+path);
					System.setProperty("phantomjs.binary.path", path);
					provider.driver=new PhantomJSDriver();
					provider.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				}
				else
					provider.driver=(getDriver(browser));
				System.out.println("Driver is initialized,.....");
			}
		}





	}

	@After
	public void tearDown_Scenario()
	{
		String status="P";
		Result.Type scenarioStatus=provider.runningScenario.getStatus();
		if(scenarioStatus == Result.Type.FAILED)
		{
			status="F";
			createScreenshot();

		}
		else if(provider.runningScenario.getStatus() == Result.Type.SKIPPED)
			status="B";
		createScreenshot();
		try {
			if(provider.scenarioData.isReportResultInTestLink())
				new TestLinkIntegration().addTestLinkResult(provider.scenarioData.getTestLinkId(), status, "Results executed by the automation scenario "+provider.scenarioData.getScenarioName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(provider.isToKillDriverAfterScenario)
		{
			if(null!=provider.driver)
			{
				System.out.println("Driver will be killed ,....");
				provider.driver.quit();
				provider.driver=null;
			}
		}

	}

	@Given("^User prints the page source$")
	public void user_prints_the_page_source() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println(provider.driver.getPageSource());
	}

    @And("User Submit the page {string}")
    public void userSubmitThePage(String arg0) {
		elem.element(arg0,provider.driver).submit();
    }

	@Then("User want to simulate the keyboard events {string}")
	public void userWantToSimulateTheKeyboardEvents(String arg0) {
		String [] tokens = arg0.trim().split("\\,");
		Actions act = new Actions(provider.driver);

		for(String key:tokens)
		{
			act=act.sendKeys(elem.convertIntoKeys(key));

		}
		act.build().perform();

	}
}
