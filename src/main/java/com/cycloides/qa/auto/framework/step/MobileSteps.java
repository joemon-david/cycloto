package com.cycloides.qa.auto.framework.step;


	import java.net.URL;
	import java.util.Properties;
	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.remote.DesiredCapabilities;
import com.cycloides.qa.auto.framework.mobile.MobileElementFactory;
import com.cycloides.qa.auto.framework.mobile.MobileUtility;
import com.cycloides.qa.auto.framework.setup.DriverProvider;
    import com.cycloides.qa.auto.framework.setup.TestBase;
    import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
	import cucumber.api.java.en.Then;
	import cucumber.api.java.en.When;
    import io.appium.java_client.MobileElement;
    import io.appium.java_client.android.AndroidDriver;
    import junit.framework.Assert;

	public class MobileSteps extends TestBase{
		
		private DriverProvider provider;
		Properties mobileConfig= new Properties();
		private MobileElementFactory elem=new MobileElementFactory();
		
		MobileUtility utils=new MobileUtility();
		
		
		public MobileSteps(DriverProvider provider) {
				this.provider=provider;				
				System.out.println("Mobile Steps Initialized,.... ");
			}
		
		
		@Before
		public void driver_initialize() {
			String appRunStatus=config.getProperty("isToRunAndroid");;
			if(null!=appRunStatus && appRunStatus.equalsIgnoreCase("false")) {
				System.out.println("Configuration related to Mobile will be skipped");
			}else
			{
				if(null==provider.appDriver) {				
					
					try {
						DesiredCapabilities cap=new MobileUtility().getCapability();
						URL url=new URL("http://0.0.0.0:4723/wd/hub");
						provider.appDriver=new AndroidDriver<MobileElement>(url,cap);
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					provider.appDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				}
			}
			
		}
		
		
		
	
		@Given("^AppUser launches the application$")
		public void appuser_launches_the_application() throws Throwable {
			provider.appDriver.launchApp();
			
		}
		@Then("^AppUser clicks \"([^\"]*)\"$")
		public void appuser_clicks(String arg1) throws Throwable {
			elem.mElements(arg1,provider.appDriver).click();
		}
		@When("^AppUser Enter the \"([^\"]*)\" in \"([^\"]*)\"$")
		public void appuser_Enter_the_in(String arg1, String arg2) throws Throwable {
			 elem.mElements(arg2, provider.appDriver).sendKeys(getappDataValue(arg1));
		}
		
		@When("^AppUser swipes from the co-ordinates\"([^\"]*)\"$")
		public void appuser_swipes_from_the_co_ordinates(String arg1) throws Throwable {
			
			 new MobileUtility().swipe(arg1,provider.appDriver);
		 
		}



		@Then("^User Want to keep the app session$")
		public void user_want_to_keep_the_app_session()  {
			provider.isToKillMobileDriverAfterScenario=false;
			System.out.println("User want to keep the session the value for isToKillDriverAfterScenario:"+provider.isToKillMobileDriverAfterScenario);
		}

		@Then("^User want wait \"([^\"]*)\" seconds$")
		public void user_want_wait_seconds(String arg1)  {
		   int sec=Integer.parseInt(arg1.trim());
		   Delay(sec);
		}

		@Then("^AppUser Verify Toast Message \"([^\"]*)\"$")
		public void AppUser_Verify_Toast_Message(String expectedValue ) throws Throwable {
		String toast_message=utils.readToastMessage(expectedValue, provider.appDriver);
		Assert.assertTrue("Validate the "+toast_message+" contains "+expectedValue,toast_message.contains(expectedValue.trim()));
		}
}
