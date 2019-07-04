package com.cycloides.qa.auto.framework.data;

import java.util.HashMap;

public class SuiteData {

public HashMap<String, ScenarioData> scenarioDataMap;

/**
 * @return the scenarioDataMap
 */
public HashMap<String, ScenarioData> getScenarioDataMap() {
	return scenarioDataMap;
}

/**
 * @param scenarioDataMap the scenarioDataMap to set
 */
public void setScenarioDataMap(HashMap<String, ScenarioData> scenarioDataMap) {
	this.scenarioDataMap = scenarioDataMap;
}


}
