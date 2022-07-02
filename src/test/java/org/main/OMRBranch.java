package org.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.endpoint.EndPoints;
import com.pojo.AddAddress_Input_Pojo;
import com.pojo.AddAddress_Output_Pojo;
import com.pojo.DeleteAddress_Input_Pojo;
import com.pojo.DeleteAddress_Output_Pojo;
import com.pojo.GetAddresses_Output_Pojo;
import com.pojo.Login_Output_Pojo;
import com.pojo.UpdateAddress_Input_Pojo;
import com.pojo.UpdateAddress_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class OMRBranch extends BaseClass {
	String logtoken;
	int address_id;
	
	@Test(priority=1)
	public void getUsers() throws IOException, ParseException {
		// 1.Header
		addHeader("Content-Type", "application/json");
		// 2.Authentication
		basicAuth(getPropertyvalue("username"), getPropertyvalue("password"));
		// 3.request method type
		Response response = requestType("POST", EndPoints.LOGIN);
		int StatusCode = getStatusCode(response);
		System.out.println(StatusCode);
		String bodyAsPrettyString = getBodyAsPrettyString(response);
		System.out.println(bodyAsPrettyString);
		Login_Output_Pojo as = response.as(Login_Output_Pojo.class);
		String first_name = as.getData().getFirst_name();
		System.out.println(first_name);
		logtoken = as.getData().getLogtoken();
		Assert.assertEquals(first_name,"chandan","verify first name");
	}

	@Test(priority=2)
	private void createAddress() {
		
		//1.Headers
		List<Header> h = new ArrayList<Header>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);
		//2.Add address input pojo
		AddAddress_Input_Pojo addAddress_Input_Pojo = new AddAddress_Input_Pojo("Berlin", "Khundra", "1234567898", "apartment", 33, 3378, 101,"202020", "64/63 partap nagar", "home");
		addBody(addAddress_Input_Pojo);
		//3.Request type
		Response response = requestType("POST",EndPoints.ADDADDRESS);
		System.out.println(getStatusCode(response));
		AddAddress_Output_Pojo addAddress_Output_Pojo = response.as(AddAddress_Output_Pojo.class);
		address_id = addAddress_Output_Pojo.getAddress_id();
		System.out.println(address_id);
		String message = addAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(message,"Address added successfully","verify added address");
	}
	
	@Test(priority=3)
	private void updateAddress() {
		//1.Headers
		List<Header> h = new ArrayList<>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);
		//2.Body
		UpdateAddress_Input_Pojo updateAddress_Input_Pojo = new UpdateAddress_Input_Pojo(String.valueOf(address_id), "Nishu", "kumari", "9042562366", "apartment", 33, 3378, 101, "202020", "visakhapatnam", "home");
		addBody(updateAddress_Input_Pojo);
		//3.Req type
		Response response = requestType("PUT",EndPoints.UPDATEADDRESS);
		System.out.println(getStatusCode(response));
		UpdateAddress_Output_Pojo updateAddress_Output_Pojo = response.as(UpdateAddress_Output_Pojo.class);
		String message = updateAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(message,"Address updated successfully","Verify updated successfully");
	}
	
	@Test(priority=4)
	private void getAddresses() {
		//1.Headers
		List<Header> h = new ArrayList<>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);
		
		//2.Req type
		Response response = requestType("GET",EndPoints.GETADDRESSES);
		System.out.println(getStatusCode(response));
		GetAddresses_Output_Pojo getAddresses_Output_Pojo = response.as(GetAddresses_Output_Pojo.class);
		String message = getAddresses_Output_Pojo.getMessage();
		Assert.assertEquals(message,"OK","Verify get address message filed contains ok");
	}
	@Test(priority=5)
	private void deleteAddresses() {
		//1.Headers
		List<Header> h = new ArrayList<>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);
		// 2 payload
		DeleteAddress_Input_Pojo deleteAddress_Input_Pojo = new DeleteAddress_Input_Pojo(String.valueOf(address_id));
		addBody(deleteAddress_Input_Pojo);
		//3.Req type
		Response response = requestType("DELETE",EndPoints.DELETEADDRESS);
		System.out.println(getStatusCode(response));
		DeleteAddress_Output_Pojo deleteAddresses_Output_Pojo = response.as(DeleteAddress_Output_Pojo.class);
		String message = deleteAddresses_Output_Pojo.getMessage();
		Assert.assertEquals(message,"Address deleted successfully","Verify address details deleted successfully");
}
	@Test(priority=6)
	private void changeprofilepic() {
		//1.Headers
		List<Header> h = new ArrayList<>();
		Header h1 = new Header("Content-Type", "multipart/form-data");
		Header h2 = new Header("Authorization", "Bearer "+logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);
		// 2 body formdata
		formdata();
		
		//3.Req type
		Response response = requestType("POST",EndPoints.PROFILEPIC);
		System.out.println(getStatusCode(response));		
}	
}
