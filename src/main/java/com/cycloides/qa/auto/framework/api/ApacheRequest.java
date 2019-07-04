package com.cycloides.qa.auto.framework.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cycloides.qa.auto.framework.constants.FilePaths;
import com.cycloides.qa.auto.framework.constants.RequestKeys;
import com.cycloides.qa.auto.framework.constants.RequestType;
import com.cycloides.qa.auto.framework.dto.Request;
import com.cycloides.qa.auto.framework.setup.JSONUtility;
import com.google.common.net.HttpHeaders;

public class ApacheRequest {
	
	JSONUtility utils = new JSONUtility();

	
public JSONArray sendPatch(String url, String auth, JSONObject jsonObject) throws Exception {
		StringEntity input=new StringEntity(jsonObject.toString());
		CloseableHttpClient  client=HttpClients.createDefault();
		HttpPatch post=new HttpPatch(url);
		input.setContentType("application/json");
		if(null!=auth && !auth.isEmpty() && !auth.equalsIgnoreCase(""))
		{
		post.setHeader(HttpHeaders.AUTHORIZATION,auth);
		}
		post.setEntity(input);
		post.setHeader("Accept", "application/json");
		post.setHeader("User-Agent", "Apache-HttpClient/4.1 (java 1.8)");
		System.out.println(post.toString());
		HttpResponse response = client.execute(post);		
		return formatResponse(client, response);
		
	}
	
public JSONArray sendHttpPost(String url, String auth, JSONObject jsonObject) throws Exception {		
	
	StringEntity input=new StringEntity(jsonObject.toString());
	CloseableHttpClient  client=HttpClients.createDefault();
		HttpPost post=new HttpPost(url);
		input.setContentType("application/json");
		if(null!=auth && !auth.isEmpty() && !auth.equalsIgnoreCase(""))
		{
			post.setHeader(HttpHeaders.AUTHORIZATION,auth);
		}
		post.setEntity(input);
		post.setHeader("Accept", "application/json");
		post.setHeader("User-Agent", "Apache-HttpClient/4.1 (java 1.8)");
		System.out.println(post.toString());
		HttpResponse response = client.execute(post);		
		return formatResponse(client, response);

		
	}
public JSONArray sendHttpRequest(Request request) throws Exception {		
	
	HttpEntity input = null;
	JSONObject requestBody=request.getBody();
	
	URI url = null;
	URIBuilder uriBuilder=new URIBuilder(request.getUrl());
	if(request.isContainsFile())
		{
		MultipartEntityBuilder builder=MultipartEntityBuilder.create();
		String imgPath=FilePaths.userDir+requestBody.get(RequestKeys.BODY_PARAM_IMG).toString();
		System.out.println("Uploaded file Path "+imgPath);
		builder.addBinaryBody("file", new File(imgPath));
		requestBody=utils.removeItem(requestBody, RequestKeys.BODY_PARAM_IMG);
		for(Object key:requestBody.keySet())
			builder.addTextBody(key.toString(), requestBody.get(key).toString());
		input=builder.build();
		}else
		{
			requestBody=utils.removeItem(requestBody, RequestKeys.BODY_PARAM_IMG);
			input=new StringEntity(requestBody.toJSONString());
		}
	
	if(request.isContainsURLParams())
	{
		JSONObject urlParams=request.getUrlParams();
		for(Object key:urlParams.keySet())
			uriBuilder.addParameter(key.toString(), urlParams.get(key).toString());
	}
	url=uriBuilder.build();
	CloseableHttpClient  client=HttpClients.createDefault();
		HttpUriRequest post=getRequestObject(request.getRequestType(),url);
//		post.setHeader("Accept", "application/json");
//		post.setHeader("Content-type", "application/json");
		
		if(request.isRequiredAuthorization() && !request.getAuthorizationToken().isEmpty())
		{
			post.setHeader(HttpHeaders.AUTHORIZATION,request.getAuthorizationToken());
		}
		if(! request.getRequestType().equalsIgnoreCase(RequestType.GET))
		{
			((HttpEntityEnclosingRequestBase)post).setEntity(input);		
		}
		
		post.setHeader("User-Agent", "Apache-HttpClient/4.1 (java 1.8)");
		System.out.println(request.toString());
		HttpResponse response = client.execute(post);		
		return formatResponse(client, response);

		
	}

private HttpUriRequest getRequestObject(String requestType,URI url)
{
	HttpUriRequest http = null;
	
	switch (requestType) {
	case RequestType.GET:
		http=new HttpGet(url);
		break;
	case RequestType.POST:
		http=new HttpPost(url);
		break;
	case RequestType.PATCH:
		http=new HttpPatch(url);
		break;

	default:
		System.out.println("Request Type "+requestType+" does not match with any case");
		break;
	}
	return http;
}


public JSONArray sendHttpPost1(String url, String auth, JSONObject jsonObject) throws Exception {		
	
	HttpEntity input = null;
	
	CloseableHttpClient  client=HttpClients.createDefault();
		HttpPost post=new HttpPost(url);
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-type", "application/json");
		
		if(null!=auth && !auth.isEmpty() && !auth.equalsIgnoreCase(""))
		{
			post.setHeader(HttpHeaders.AUTHORIZATION,auth);
		}
		post.setEntity(input);		
		post.setHeader("User-Agent", "Apache-HttpClient/4.1 (java 1.8)");
		System.out.println(post.toString());
		HttpResponse response = client.execute(post);		
		return formatResponse(client, response);

		
	}
public JSONArray sendHttpGet(String url, String auth, JSONObject jsonObject) throws Exception {

	StringEntity input=new StringEntity(jsonObject.toString());
	DefaultHttpClient  client=new DefaultHttpClient();
	HttpGet get=new HttpGet(url);
	input.setContentType("application/json");
	if(null!=auth && !auth.isEmpty() && !auth.equalsIgnoreCase(""))
	{
		get.setHeader(HttpHeaders.AUTHORIZATION,auth);
	}
//	get.setEntity(input);
	get.setHeader("Accept", "application/json");
	get.setHeader("User-Agent", "Apache-HttpClient/4.1 (java 1.8)");
	System.out.println(get.toString());
	HttpResponse response = client.execute(get);
	
	return formatResponse(client, response);

	
}

private JSONArray formatResponse(CloseableHttpClient client, HttpResponse response) throws IOException {
	BufferedReader br = new BufferedReader(
	        new InputStreamReader((response.getEntity().getContent())));

String output;
StringBuilder builder=new StringBuilder();
System.out.println("Response Code "+response.getStatusLine().getStatusCode());
System.out.println("Output from Server .... \n");
while ((output = br.readLine()) != null) {
System.out.println(output);
builder.append(output);
}

client.getConnectionManager().shutdown();
JSONArray resp=utils.convertToJSONObject(builder.toString(),response.getStatusLine().getStatusCode());
//resp.put("response_code", response.getStatusLine().getStatusCode());
return resp;
}
}
