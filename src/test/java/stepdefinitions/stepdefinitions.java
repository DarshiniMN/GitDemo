package stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepdefinitions extends Utils {
	Response response;
	RequestSpecification res;
 	ResponseSpecification resspec;
 	TestDataBuild data=new TestDataBuild();
 	static String place_id	;

@Given("Add Place Payload with {string} {string} {string}")
public void add_place_payload_with(String name, String language, String address) throws IOException {
        // Add code here to create the payload for adding a place
    	 res=given().spec(requestspecification()).body(data.addPlacePayload(name, language, address));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource,String method) {
        // constructor will be called with value of resource which you pass
    	APIResources resourceAPI=APIResources.valueOf(resource);
    	System.out.println(resourceAPI.getResource());
    if(method.equalsIgnoreCase("POST")) {
    	 response =res.when().post(resourceAPI.getResource());
    }
    else if(method.equalsIgnoreCase("GET")) {
   	 response =res.when().get(resourceAPI.getResource());
    }
    }
    @Then("the API call is successful with status code {int}")
    public void the_api_call_is_successful_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
     	assertEquals(response.getStatusCode(),200);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectedValue) {
        // Write code here that turns the phrase above into concrete actions
    	assertEquals(getJsonPath(response,key), expectedValue);
    }
    
    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        // Write code here that turns the phrase above into concrete actions
     place_id	=getJsonPath(response,"place_id");
    res=given().spec(requestspecification().queryParam("place_id", place_id));
    user_calls_with_post_http_request(resource,"GET");
     String actualName=getJsonPath(response,"name");
     assertEquals(expectedName, actualName);
    }
    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
    	res=given().spec(requestspecification()).body(data.deletePlacePayload(place_id));
    }
}
