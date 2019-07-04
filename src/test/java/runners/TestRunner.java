package runners;

import com.cycloides.qa.auto.framework.utils.ReportUtility;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/functionalTests/",
glue= {"com.cycloides.qa.auto.framework.step"},
plugin= {"pretty","html:target","json:target/cucumber.json"},
monochrome=true,
strict=true,
dryRun=false,
tags= {"@RunNow"}
)
public class TestRunner {

    @AfterClass
    public static void teardown()
    {
        ReportUtility.generateReport();
    }

	
}
