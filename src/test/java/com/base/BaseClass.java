package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	RequestSpecification reqSpec;
	private Response response;
	
	
	public void addHeader(String key,String value) {
		 reqSpec = RestAssured.given().header(key,value) ;
		 }
	public void addHeaders(Headers headers) {
		 reqSpec = RestAssured.given().headers(headers);
		 }
	public void formdata() {
		reqSpec.multiPart("profile_picture",new File("C:\\Users\\Nishu\\Pictures\\photo\\imgk.jpeg"));	
	}
	public String getPropertyvalue(String key) throws IOException {
		
		Properties properties = new Properties();
		FileInputStream stream = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		properties.load(stream);
		Object object = properties.get(key);
		String value = (String)object;
		return value;
		}

	public void basicAuth(String name, String password) {
		reqSpec.auth().preemptive().basic(name, password);
	}
	 public void queryparam(String key,String value) {
		 reqSpec = reqSpec.queryParam(key, value);
		 }
	 public void pathparam(String key,String value) {
		 reqSpec = reqSpec.pathParam(key, value);
		 }
	 public void addBody(String body) {
		 reqSpec = reqSpec.body(body);
		 }
	 public void addBody(Object body) {
		 reqSpec = reqSpec.body(body);
		 }
	 	 
	 public Response requestType(String reqType,String endpoint) {
		 switch (reqType){
		 case"GET":
			 setResponse(reqSpec.log().all().get(endpoint));
			 break;
		 
		 case"POST":
			 setResponse(reqSpec.log().all().post(endpoint));
			 break; 
		
		 case"PUT":
			 setResponse(reqSpec.log().all().put(endpoint));
			 break;
			 
		 case"DELETE":
			 setResponse(reqSpec.log().all().delete(endpoint));
			 break;
			 default:
				 break;
		 }
		 return getResponse();
	 }
	 public int getStatusCode(Response response) {
		 int  statusCode = response.getStatusCode();
		 return statusCode; 
	 }
	 public ResponseBody getBody(Response response) {
		 ResponseBody body = response.getBody();
		 return body; 
	 }
	 public String getBodyAsString(Response response) {
		 String  asString = getBody(response).asString();
		 return asString; 
	 }
	 public String getBodyAsPrettyString(Response response) {
		 String  asPrettyString = getBody(response).asPrettyString();
		 return asPrettyString; 
	 }
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}

}