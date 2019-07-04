package com.cycloides.qa.auto.framework.setup;

import java.net.MalformedURLException;
import java.net.URL;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

public class TestLinkIntegration {
	
	private static String propertyPath=System.getProperty("user.dir")+"\\src\\test\\resources\\config\\testlink.properties";
	private static String TESTLINK_SERVER_URL =FileReader.getPropertyValue(propertyPath, "TESTLINK_SERVER_URL");
	  private static String API_KEY = FileReader.getPropertyValue(propertyPath, "API_KEY");
	 
	  private static String PROJECT_NAME = FileReader.getPropertyValue(propertyPath, "PROJECT_NAME");
	  private static String TEST_PLAN_NAME = FileReader.getPropertyValue(propertyPath, "TEST_PLAN_NAME");
	  private static String BUILD_NAME = FileReader.getPropertyValue(propertyPath, "BUILD_NAME");
	  
	  
		public void printTestProjectsAvailableInTestLink() throws TestLinkAPIException, MalformedURLException
		{
			TestLinkAPI api = new TestLinkAPI(new URL(TESTLINK_SERVER_URL), API_KEY);
		    TestProject[] projects=api.getProjects();
		    for(TestProject proj:projects)
		    {
		    	System.out.println(proj.getName());
		    }
		}
		
	 
	  public void addTestLinkResult(String testcaseId,String testStatus,String comment) throws MalformedURLException {
	 
	    TestLinkAPI api = new TestLinkAPI(new URL(TESTLINK_SERVER_URL), API_KEY);
	    
	    TestCase testCase = api.getTestCaseByExternalId(testcaseId, null);
	 
	    System.out.println("Test Case: "
	        + testCase.getName()
	        + " Internal ID: "
	        + testCase.getInternalId()
	        + " External ID: "
	        + testCase.getId());
	 
	    int testPlanId=0;
	    
	    TestPlan testPlan= api.getTestPlanByName(TEST_PLAN_NAME, PROJECT_NAME);
		testPlanId=testPlan.getId();
		System.out.println("Test Plan: " + testPlan.getName() + " ID: " + testPlan.getId());	   
	 
	    Build[] builds = api.getBuildsForTestPlan(testPlanId);
	 
	    Build build = null;
	 
	    for (Build buildInArray : builds) {
	      if (buildInArray.getName().equals(BUILD_NAME)) {
	        System.out.println("Build found! " + buildInArray.getName() + " " + buildInArray.getId());
	        build = buildInArray;
	        break;
	      }
	    }
	 
	    System.out.println("Builds: " + builds.length + " " +build.getId() + " " + build.getName());
	 
	    ExecutionStatus execStatus=ExecutionStatus.PASSED;
	    if(testStatus.equalsIgnoreCase("F"))
	    	execStatus=ExecutionStatus.FAILED;
	    else if(testStatus.equalsIgnoreCase("B"))
	    	execStatus=ExecutionStatus.BLOCKED;
	    
	    api.reportTCResult(testCase.getId(), null, testPlanId, execStatus, build.getId(), null,
	    		comment, null, null, null, null, null, null);
	  }
	
	
	
	
	
	

}
