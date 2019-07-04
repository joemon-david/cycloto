package com.cycloides.qa.auto.framework.dto;

public class ScenarioData {
	private String scenarioName;
	private String testLinkId;
	private String failureDetails;
	private boolean reportResultInTestLink;
	
	public boolean isReportResultInTestLink() {
		return reportResultInTestLink;
	}
	public void setReportResultInTestLink(boolean reportResultInTestLink) {
		this.reportResultInTestLink = reportResultInTestLink;
	}
	public String getScenarioName() {
		return scenarioName;
	}
	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}
	public String getTestLinkId() {
		return testLinkId;
	}
	public void setTestLinkId(String testLinkId) {
		this.testLinkId = testLinkId;
	}
	public String getFailureDetails() {
		return failureDetails;
	}
	public void setFailureDetails(String failureDetails) {
		this.failureDetails = failureDetails;
	}
	
	

}
