package stepdefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
@Before("@DeletePlace")
public void beforeScenario() throws IOException
{		//execute this code only when place id is null
	//write a code that will give you place id
	
	stepdefinitions m =new stepdefinitions();
	if(stepdefinitions.place_id==null)
	{
	
	m.add_place_payload_with("Shetty", "French", "Asia");
	m.user_calls_with_post_http_request("AddPlaceAPI", "POST");
	m.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");
	}
	
	

}
}