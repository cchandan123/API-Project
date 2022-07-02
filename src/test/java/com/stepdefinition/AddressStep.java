package com.stepdefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoint.EndPoints;
import com.payloads.PayLoad;
import com.pojo.AddAddress_Input_Pojo;
import com.pojo.AddAddress_Output_Pojo;
import com.pojo.DeleteAddress_Input_Pojo;
import com.pojo.DeleteAddress_Output_Pojo;
import com.pojo.GetAddresses_Output_Pojo;
import com.pojo.Login_Output_Pojo;
import com.pojo.UpdateAddress_Input_Pojo;
import com.pojo.UpdateAddress_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class AddressStep extends BaseClass {
	String logtoken;
	int address_id;

	@Given("User Add Header")
	public void userAddHeader() {
    addHeader("Content-Type", "application/json");   
	}
	@Given("User Add Basic Authentication For Login")
	public void userAddBasicAuthenticationForLogin() throws IOException {
		basicAuth(getPropertyvalue("username"), getPropertyvalue("password"));
	}
	@Given("User send {string} Request for Login Endpoint")
	public void userSendRequestForLoginEndpoint(String POST)  {
		setResponse(requestType(POST, EndPoints.LOGIN));  
	}
	@Then("User Verify the Status Code is {int}")
	public void userVerifyTheStatusCodeIs(Integer int1) {
		Response response = requestType("POST", EndPoints.LOGIN);
		int StatusCode = getStatusCode(response);    
		
	}
	@Then("User Verify the Response Body message of login {string}")
	public void userVerifyTheResponseBodyMessageOfLogin(String expMesage)  {
		String bodyAsPrettyString = getBodyAsPrettyString(getResponse());
		System.out.println(bodyAsPrettyString);
		Login_Output_Pojo as = getResponse().as(Login_Output_Pojo.class);
		String first_name = as.getData().getFirst_name();
		System.out.println(first_name);
		logtoken = as.getData().getLogtoken();
		Assert.assertEquals(first_name,"chandan","verify first name");
	}

////////////////////////////////////////////
	
	@Given("User should Add Headers of create address")
	public void userShouldAddHeadersOfCreateAddress() {
	
	List<Header> h = new ArrayList<Header>();
Header h1 = new Header("Content-Type", "application/json");
Header h2 = new Header("Authorization", "Bearer "+logtoken);
h.add(h1);
h.add(h2);
Headers headers = new Headers(h);
addHeaders(headers);

	}
	@Given("User Should Give AddressDetails {string},{string},{string},{string},{string},{string},{string},{string},{string}and{string}")
	public void user_Should_Give_AddressDetails_and(String firstName, String lastName, String mobileNumber,
			String apartment, String state, String city, String country, String zipcode, String Address,
			String AdressType) {
		
	AddAddress_Input_Pojo addAddress_Input_Pojo = new AddAddress_Input_Pojo("Berlin", "Khundra", "1234567898", "apartment", 33, 3378, 101,"202020", "64/63 partap nagar", "home");
	addBody(addAddress_Input_Pojo);
	}
	@Given("User Send {string} for ADDADDRESS Endpoint")
	public void userSendForADDADDRESSEndpoint(String POST) {
		Response response = requestType("POST",EndPoints.ADDADDRESS);
		System.out.println(getStatusCode(response));
}
	//@Then("User Verify the Status Code is {int}")
//	public void user_Verify_The_Status_Code_Is(Integer int1) {
	//	Response response = requestType("POST",EndPoints.ADDADDRESS);
	//	System.out.println(getStatusCode(response));
	//}
	@Then("User Verify the Response Body as message of create address {string} and get address_id saved")
	public void userVerifyTheResponseBodyAsMessageOfCreateAddressAndGetAddressIdSaved(String expMessage)  {
		String bodyAsPrettyString = getBodyAsPrettyString(response);
		System.out.println(bodyAsPrettyString);

		AddAddress_Output_Pojo addAddress_Output_Pojo = response.as(AddAddress_Output_Pojo.class);
		address_id = addAddress_Output_Pojo.getAddress_id();
		System.out.println(address_id);
		
	  String message = addAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(message,"Address added successfully","verify added address");
	}

	
//	//////////////////////////////////////////////////////
	
	@Given("User should Add Headers of update address")
	public void userShouldAddHeadersOfUpdateAddress() {
		List<Header> h = new ArrayList<>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);
		}
	@Given("User Should Give {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} and User Should Add Body")
	public void user_Should_Give_and_User_Should_Add_Body(String addressID, String firstName, String lastName, String mobileNumber,
			String apartment, String state, String city, String country, String zipcode, String Address,
			String AdressType) {
	    PayLoad p = new PayLoad();
	    UpdateAddress_Input_Pojo updateAddress_Input_Pojo = new UpdateAddress_Input_Pojo(String.valueOf(address_id), "Nishu", "kumari", "9042562366", "apartment", 33, 3378, 101, "202020", "visakhapatnam", "home");
		addBody(updateAddress_Input_Pojo);
	    
	}
	@Given("User Send {string} for UPDATEADDRESS Endpoint")
	public void user_send_for_updateaddress_endpoint(String PUT) {
		Response response = requestType("PUT",EndPoints.UPDATEADDRESS);
		System.out.println(getStatusCode(response));

	}
	//@Then("User Verify the Status Code is {int}")
	//public void userVerifyTheStatusCodeIs(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
	//}
	@Then("User Verify the Response Body message of update address as {string}")
	public void user_verify_the_response_body_message_of_update_address_as(String expMessage) {
		String bodyAsPrettyString = getBodyAsPrettyString(response);
		System.out.println(bodyAsPrettyString);

		UpdateAddress_Output_Pojo updateAddress_Output_Pojo = response.as(UpdateAddress_Output_Pojo.class);
		String message = updateAddress_Output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(message,"Address updated successfully","Verify updated successfully");
	}
///////////////////////////////////////
	@Given("User Add Headers of get address")
	public void userAddHeadersOfGetAddress() {
		List<Header> h = new ArrayList<>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);
	}
	@Given("User Send {string} for GETADDRESS Endpoint")
	public void user_send_for_getaddress_endpoint(String GET) {
		Response response = requestType("GET",EndPoints.GETADDRESSES);
		System.out.println(getStatusCode(response));
	}
	//@Then("User Verify the Status Code is {int}")
	//public void userVerifyTheStatusCodeIs(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	//}
	@Then("User Verify the Response Body message of get address as {string}")
	public void user_verify_the_response_body_message_of_get_address_as(String expMessage) {
		GetAddresses_Output_Pojo getAddresses_Output_Pojo = response.as(GetAddresses_Output_Pojo.class);
		String message = getAddresses_Output_Pojo.getMessage();
		Assert.assertEquals(message,"OK","Verify get address message filed contains ok");
	}
//
//
//	
//	//////////////////////////////
//	
	@Given("User Add Headers of delete address")
	public void userAddHeadersOfDeleteAddress() {
		List<Header> h = new ArrayList<>();
		Header h1 = new Header("Content-Type", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logtoken);
		h.add(h1);
		h.add(h2);
		Headers headers = new Headers(h);
		addHeaders(headers);
	}
	@Given("User Should Add Body of delete address")
	public void userShouldAddBodyOfDeleteAddress() {

		PayLoad p = new PayLoad();
		DeleteAddress_Input_Pojo deleteAddress_Input_Pojo = new DeleteAddress_Input_Pojo(String.valueOf(address_id));
		addBody(deleteAddress_Input_Pojo);
	}
	@Given("User Send {string} for DELETEADDRESS Endpoint")
	public void userSendForDELETEADDRESSEndpoint(String DELETE) {
		Response response = requestType("DELETE",EndPoints.DELETEADDRESS);
		System.out.println(getStatusCode(response));
	}
	//@Then("User Verify the Status Code is {int}")
	//public void userVerifyTheStatusCodeIs(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	//}
	@Then("User Verify the Response Body message of Delete Address as {string}")
	public void user_verify_the_response_body_message_of_delete_address_as(String expMessage) {
		DeleteAddress_Output_Pojo deleteAddresses_Output_Pojo = response.as(DeleteAddress_Output_Pojo.class);
		String message = deleteAddresses_Output_Pojo.getMessage();
		
		Assert.assertEquals(message,"Address deleted successfully","Verify address details deleted successfully");
	}
}
