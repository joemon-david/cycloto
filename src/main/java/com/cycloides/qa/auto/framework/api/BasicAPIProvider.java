package com.cycloides.qa.auto.framework.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.cycloides.qa.auto.framework.constants.RequestKeys;
import com.cycloides.qa.auto.framework.data.VariableMap;
import com.cycloides.qa.auto.framework.dto.Request;
import com.cycloides.qa.auto.framework.setup.JSONUtility;

import cucumber.api.Scenario;
import sun.misc.BASE64Encoder;

public class BasicAPIProvider {
	private final String USER_AGENT = "Mozilla/5.0";
	JSONUtility utils = new JSONUtility();
	ApacheRequest apache=new ApacheRequest();
	int response_code=0;

	public JSONArray sendPost(String url, String auth, JSONObject jsonObject) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con = getNormalConnection(con, "POST");
		con.setRequestProperty("Authorization", auth);// "Bearer "+
		return sendRequestAndGetResponse(url, jsonObject, con);
	}

	public static void main(String[] args) throws Exception {
		BasicAPIProvider api=new BasicAPIProvider();
		JSONObject empty=new JSONObject();
		JSONObject create_field=new JSONObject();
		create_field.put("field_name_en", "auto_api");
		create_field.put("field_name_fr", "auto_api");
		create_field.put("field_category", 1);
		create_field.put("field_type", 1);
		create_field.put("status", "null");
		String token=api.getBasicAuthToken("superadmin", "swordfish");
		String ip="http://103.79.223.61:6070";
		String url="/api/v1/fields/148";
//		api.sendGet(ip+url, token, empty);
//		api.sendPatch(ip+url, token, empty);
		
	}
	

	public JSONArray sendPost(String url, JSONObject json) throws Exception {

		// String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con = getNormalConnection(con, "POST");
		return sendRequestAndGetResponse(url, json, con);
	}

	public JSONArray sendGet(String url, JSONObject json) throws Exception {

		// String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con = getNormalConnection(con, "GET");
		String response = sendGetRequest(url, con);
		return utils.convertToJSONObject(response,response_code);
	}

	public JSONArray sendGet(String url, String auth, JSONObject json) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con = getNormalConnection(con, "GET");
		con.setRequestProperty("Authorization", auth);// "Bearer "+
		String response = sendGetRequest(url, con);
		return utils.convertToJSONObject(response,response_code);
	}

	public HttpURLConnection getNormalConnection(HttpURLConnection con, String method) throws ProtocolException {
		con.setRequestMethod(method);
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		return con;
	}

	public JSONArray sendRequestAndGetResponse(String url, JSONObject json, HttpURLConnection con)
			throws IOException, UnsupportedEncodingException {
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.write(json.toString().getBytes("UTF-8"));
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		response_code=responseCode;
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + json.toString());
		System.out.println("Response Code : " + responseCode);
		

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		JSONArray resp=utils.convertToJSONObject(response.toString(),response_code);
//		resp.put("response_code", responseCode);
		return resp;
	}

	

	public String getBasicAuthToken(String username, String password) {
		BASE64Encoder enc = new sun.misc.BASE64Encoder();
		String userpassword = username + ":" + password;
		String encodedAuthorization = "Basic " + enc.encode(userpassword.getBytes());
		System.out.println("Authorization " + encodedAuthorization);
		return encodedAuthorization;
	}

	private String sendGetRequest(String url, HttpURLConnection con) throws IOException {
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		response_code=responseCode;
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());
		return response.toString();
	}

	public Request createRequest(String req_name, JSONObject req_full) throws Exception {
		Request request = new Request();

		String auth_token = null;
		request.setIp(req_full.get(RequestKeys.IP).toString());
		JSONObject req = (JSONObject) req_full.get(req_name);
		if(req.containsKey(RequestKeys.REQUEST_CONTAINS_FILE) && req.get(RequestKeys.REQUEST_CONTAINS_FILE).toString().equalsIgnoreCase("true"))
		{
			request.setContainsFile(true);
		}
		
		request.setRequestType(req.get(RequestKeys.REQUEST_TYPE).toString());
		request.setApi(req.get(RequestKeys.REQUEST_API).toString());
		
		
		if (req.containsKey(RequestKeys.REQUEST_BODY)) {
			JSONObject req_body = (JSONObject) req.get(RequestKeys.REQUEST_BODY);
			
			req_body = utils.formatRequest(req_body);
			request.setBody(req_body);
		}
		if (req.containsKey(RequestKeys.REQUEST_URL_PARAMS)) {
			JSONObject req_urlParams = (JSONObject) req.get(RequestKeys.REQUEST_URL_PARAMS);
			req_urlParams = utils.formatRequest(req_urlParams);
			request.setUrlParams(req_urlParams);			
			request.setContainsURLParams(true);
		}
		if (req.get(RequestKeys.REQUEST_AUTH_REQUIRED).toString().equalsIgnoreCase("true")) {
			request.setRequiredAuthorization(true);
			if (req.get(RequestKeys.REQUEST_AUTH_TOKEN).toString().equalsIgnoreCase("Global")) {
				JSONObject global_auth = (JSONObject) req_full.get(RequestKeys.GLOBAL_TOKEN);
				if (global_auth.get(RequestKeys.GLOBAL_AUTHORIZATION_TYPE).toString().equalsIgnoreCase("Basic")) {
					auth_token = getBasicAuthToken(global_auth.get(RequestKeys.GLOBAL_TOKEN_USERNAME).toString(),
							global_auth.get(RequestKeys.GLOBAL_TOKEN_PASSWORD).toString());
					request.setAuthorizationToken(auth_token);
					request.setAuthorizationType(global_auth.get(RequestKeys.GLOBAL_AUTHORIZATION_TYPE).toString());
				}

			}
		} else
			request.setRequiredAuthorization(false);

		return request;
	}

	public JSONArray sendRequestAndGetResponse(Request request) throws Exception {
		JSONArray Response = null;
		
		if (request.getRequestType().equalsIgnoreCase("POST")) {
			if (request.isRequiredAuthorization()) {
//				Response = sendPost(request.getUrl(), request.getAuthorizationToken(),request.getBody());
				Response= apache.sendHttpPost(request.getUrl(), request.getAuthorizationToken(), request.getBody());
			}else
			{
//				Response = sendPost(request.getUrl(), request.getBody());
				Response= apache.sendHttpPost(request.getUrl(), "", request.getBody());
			}
		} else if (request.getRequestType().equalsIgnoreCase("GET")) {
			if (request.isRequiredAuthorization()) {				
				Response= apache.sendHttpGet(request.getUrl(), request.getAuthorizationToken(), request.getBody());
			}else
			{				
				Response= apache.sendHttpGet(request.getUrl(), "", request.getBody());
			}

		} else if (request.getRequestType().equalsIgnoreCase("PATCH")) {
			if (request.isRequiredAuthorization()) {
				Response = apache.sendPatch(request.getUrl(), request.getAuthorizationToken(),
						request.getBody());
			}

		}
		return Response;

	}
	
	public JSONArray sendRequestAndGetResponse1(Request request) throws Exception {
		JSONArray Response = null;
		Response=apache.sendHttpRequest(request);	
		return Response;

	}
	
	public void performNullPointCheckOnManadatoryParams(Request request,Scenario scenario,String expected_code) throws Exception
	{
		JSONArray response;
		
		JSONObject initial_body=request.getBody();
		boolean isFailed=false;
		for(Object param:request.getBody().keySet())
		{
			JSONObject requestBody=utils.removeItem(initial_body, param.toString());
			request.setBody(requestBody);
			response=sendRequestAndGetResponse(request);
			scenario.write(request.toString());
			int expected=Integer.parseInt(expected_code);
			int actual=(int) ((JSONObject) response.get(0)).get("response_code");
			if(actual==expected)
			{
				scenario.write("### PASSED "+param.toString()+"  ###");
			}else
				{
				scenario.write("### FAILED "+param.toString()+"  ###");
				isFailed=true;
				}
			
			
			scenario.write(response.toJSONString());
		}
		
		if(isFailed)
			throw new Exception("One or more of the null check condition failed - Please check output ");
		
	}
	

	public Object getRequestParameter(Request request, String param) {
		Object result = new Object();
		System.out.println("Checking the Request Parameter "+param);
		if(param.startsWith("Request.body"))
		{
			JSONObject body = request.getBody();
			param=param.replace("Request.body.", "");
			result = body.get(param);
		}else if(param.startsWith("$"))
		{
			result=VariableMap.get(param);
		}
		else
			result=param;
		
		
		return result;
	}

}
