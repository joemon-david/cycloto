package com.cycloides.qa.auto.framework.step;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cycloides.qa.auto.framework.api.BasicAPIProvider;
import com.cycloides.qa.auto.framework.data.ScenarioData;
import com.cycloides.qa.auto.framework.data.VariableMap;
import com.cycloides.qa.auto.framework.dto.Request;
import com.cycloides.qa.auto.framework.dto.Response;
import com.cycloides.qa.auto.framework.setup.DriverProvider;
import com.cycloides.qa.auto.framework.setup.JSONUtility;
import com.cycloides.qa.auto.framework.setup.TestBase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class APISteps extends TestBase{
	
	public DriverProvider context;
	public APISteps(DriverProvider provider) {
		// TODO Auto-generated constructor stub
		this.context=provider;		
		System.out.println("API Steps Initialized,.... ");
	}
	
	static Request request=new Request();
	static Response response=new Response();
	static JSONArray jsonArray=new JSONArray();
	
	BasicAPIProvider provider=new BasicAPIProvider();
	JSONUtility utils=new JSONUtility();
	String response_code="response_code";
	ScenarioData scenario_data=new ScenarioData();
	HashMap<Integer, Request>requestDataMap=new HashMap<Integer, Request>();
	
	static int request_index=0,response_index=0;
	
	
	
	@Given("^User want to send Request \"([^\"]*)\" from the json file \"([^\"]*)\"$")
	public void user_want_to_send_Request_from_the_json_file(String arg1, String arg2) throws Exception  {
	    JSONObject req_full=utils.readAndParse(arg2);
	    request=provider.createRequest(arg1, req_full);
	    jsonArray=provider.sendRequestAndGetResponse1(request);
	    response.setResponseBody(jsonArray);
	    addRequestAndResponse();
	}



	private void addRequestAndResponse() {
		context.runningScenario.write(request.toString());
	    context.runningScenario.write(response.getResponseBodyAsString());
	    requestDataMap.put(++request_index, request);
	    
	}

	

	@Given("^User want to check the Response field \"([^\"]*)\" is \"([^\"]*)\"$")
	public void user_want_to_check_the_Response_field_is(String arg1, String arg2) {
	  
	   String expected=provider.getRequestParameter(request, arg2).toString();
		boolean condition=response.getResponseFieldValue(arg1).equalsIgnoreCase(expected)?true:false;
		assertTrue("Verify the Response field value "+arg1+" is "+arg2, condition);
	}
	
	@Then("^User Check the Response code is \"([^\"]*)\"$")
	public void user_Check_the_Response_code_is(String arg1) {
	    // Write code here that turns the phrase above into concrete actions
	  int resp_code=Integer.parseInt( response.getResponseFieldValue(response_code));
	  int expected=Integer.parseInt(arg1);
	  assertTrue("Verify the Response code is "+arg1, expected==resp_code);
	}
	
	@Then("^User want to send Request \"([^\"]*)\" from file \"([^\"]*)\" by appending the \"([^\"]*)\" value with \"([^\"]*)\"$")
	public void user_want_to_send_Request_from_file_by_appending_the_value_with(String arg1, String arg2, String arg3, String arg4) throws Exception  {
	   
		JSONObject req_full=utils.readAndParse(arg2);
		
		JSONObject req = (JSONObject) req_full.get(arg1);
		String append_value=req.get(arg3).toString();
		if(arg4.startsWith("Response."))
		{
			arg4=arg4.replace("Response.", "");
			arg4=response.getResponseFieldValue(arg4);
		}
		append_value+=arg4;
		req.put(arg3, append_value);
		req_full.put(arg1, req);
		request=provider.createRequest(arg1, req_full);
		jsonArray=provider.sendRequestAndGetResponse(request);
		response.setResponseBody(jsonArray);
		addRequestAndResponse();
	}
	
	@Then("^User performs a null check on all mandatory parameters for the api \"([^\"]*)\" from the json file \"([^\"]*)\"$")
	public void user_performs_a_null_check_on_all_mandatory_parameters_for_the_api_from_the_json_file(String arg1, String arg2) throws Exception  {
		JSONObject req_full=utils.readAndParse(arg2);
	    request=provider.createRequest(arg1, req_full);
	   
	}
	
	@Given("^User performs a null check for the api \"([^\"]*)\" from the json file \"([^\"]*)\" and expects status code \"([^\"]*)\"$")
	public void user_performs_a_null_check_for_the_api_from_the_json_file_and_expects_status_code(String arg1, String arg2, String arg3) throws Exception  {
	    
		JSONObject req_full=utils.readAndParse(arg2);
	    request=provider.createRequest(arg1, req_full);
	    provider.performNullPointCheckOnManadatoryParams(request,context.runningScenario,arg3);
	}
	@Given("^User want to save the response field \"([^\"]*)\" to the variable \"([^\"]*)\"$")
	public void user_want_to_save_the_response_field_to_the_variable(String arg1, String arg2)  {
		String value=response.getResponseFieldValue(arg1);
		VariableMap.put(arg2, value);
	}
}
