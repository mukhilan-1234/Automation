package restassuredoperations;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SimpleTest1 {
	
	@Test
	public void GetWeatherDetails()
	{   
	// Specify the base URL to the RESTful web service
	RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

	// Get the RequestSpecification of the request that you want to sent
	// to the server. The server is specified by the BaseURI that we have
	// specified in the above step.
	RequestSpecification httpRequest = RestAssured.given();

	// Make a request to the server by specifying the method Type and the method URL.
	// This will return the Response from the server. Store the response in a variable.
	Response response = httpRequest.request(Method.GET, "/Hyderabad");

	// Now let us print the body of the message to see what response
	// we have recieved from the server
	String responseBody = response.getBody().asString();

	// getting status code
	int statusCode = response.getStatusCode();

	// getting status line
	String statusLine = response.getStatusLine();

	// getting header
	String contentType = response.getHeader("Content-Type");
	String serverType = response.getHeader("Server");
	String acceptLanguage = response.getHeader("Content-Encoding");

	// getting headers
	Headers allHeaders = response.headers();

	// getting json path and then evaluating
	JsonPath jsonPathEvaluator = response.jsonPath();
	String city = jsonPathEvaluator.get("City");

	System.out.println("Response Body is =>  " + responseBody);

	System.out.println("Response Status Code is =>  " + statusCode);

	System.out.println("Response Status Line is =>  " + statusLine);

	System.out.println("Response Content Type is =>  " + contentType);

	System.out.println("Response Server Type is =>  " + serverType);

	System.out.println("Response content encoding is =>  " + acceptLanguage);

	Assert.assertEquals(statusCode,200,"success");

	for(Header header : allHeaders)
	{
		System.out.println("key= " + header.getName()+"Value= " +header.getValue());
	}

	System.out.println("city is "+city);

	System.out.println("temperature is "+jsonPathEvaluator.get("Temperature"));

	}

	@Test
	public void RegistrationSuccessful()
	{ 
	 RestAssured.baseURI ="http://restapi.demoqa.com/customer";
	 RequestSpecification request = RestAssured.given();
	 
	 JSONObject requestParams = new JSONObject();
	 requestParams.put("FirstName", "Virender"); // Cast
	 requestParams.put("LastName", "Singh");
	 requestParams.put("UserName", "sdimpleuser2dd2011");
	 requestParams.put("Password", "password1");
	 
	 requestParams.put("Email",  "sample2ee26d9@gmail.com");
	 request.body(requestParams.toJSONString());
	 
	 // using post method
	 Response response = request.post("/register");
	 
	 int statusCode = response.getStatusCode();
	 Assert.assertEquals(statusCode, "201");
	 String successCode = response.jsonPath().get("SuccessCode");
	 Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
	}


}
