package com.cycloides.qa.auto.framework.dto;

import org.json.simple.JSONObject;

public class Request {
	
private String requestType;
private boolean isRequiredAuthorization;
private String authorizationType;
private String authorizationToken;
private JSONObject body=new JSONObject();
private JSONObject urlParams=new JSONObject();
private String ip;
private String api;
private String url;
private boolean isContainsFile;
private boolean isContainsURLParams;

public String getRequestType() {
	return requestType;
}
public void setRequestType(String requestType) {
	this.requestType = requestType;
}

public boolean isRequiredAuthorization() {
	return isRequiredAuthorization;
}
public void setRequiredAuthorization(boolean isRequiredAuthorization) {
	this.isRequiredAuthorization = isRequiredAuthorization;
}
public String getAuthorizationType() {
	return authorizationType;
}
public void setAuthorizationType(String authorizationType) {
	this.authorizationType = authorizationType;
}
public JSONObject getBody() {
	return body;
}
public void setBody(JSONObject body) {
	this.body = body;
}
public void setBodyParam(String key,Object value)
{
	this.body.put(key, value);
}

/**
 * @return the urlParams
 */
public JSONObject getUrlParams() {
	return urlParams;
}
/**
 * @param urlParams the urlParams to set
 */
public void setUrlParams(JSONObject urlParams) {
	this.urlParams = urlParams;
}
public String getIp() {
	return ip;
}
public void setIp(String ip) {
	this.ip = ip;
}
public String getApi() {
	return api;
}
public void setApi(String api) {
	this.api = api;
	this.url = this.ip+this.api;
}
public String getUrl() {
	return url;
}
public String getAuthorizationToken() {
	return authorizationToken;
}
public void setAuthorizationToken(String authorizationToken) {
	this.authorizationToken = authorizationToken;
}


/**
 * @return the isContainsFile
 */
public boolean isContainsFile() {
	return isContainsFile;
}
/**
 * @param isContainsFile the isContainsFile to set
 */
public void setContainsFile(boolean isContainsFile) {
	this.isContainsFile = isContainsFile;
}


/**
 * @return the isContainsURLParams
 */
public boolean isContainsURLParams() {
	return isContainsURLParams;
}
/**
 * @param isContainsURLParams the isContainsURLParams to set
 */
public void setContainsURLParams(boolean isContainsURLParams) {
	this.isContainsURLParams = isContainsURLParams;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "Request [requestType=" + requestType + ", isRequiredAuthorization=" + isRequiredAuthorization
			+ ", authorizationType=" + authorizationType + ", authorizationToken=" + authorizationToken + ", ip=" + ip
			+ ", api=" + api + ", url=" + url + ", isContainsFile=" + isContainsFile+ ", isContainsURLParams=" + isContainsURLParams +",body="+body.toJSONString()+",urlParams="+urlParams.toJSONString()+"]";
}



}
