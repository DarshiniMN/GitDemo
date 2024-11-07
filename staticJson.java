package demoproject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class staticJson {
public static void main(String[] args) throws IOException {
	RestAssured.baseURI="https://rahulshettyacademy.com";
	String response = given().log().all().queryParam("key","qaclick123")
	.header("Content-Type","application/json")
	.body(Files.readAllBytes(Paths.get("C:\\Users\\User\\Desktop\\API testing\\AddPlace.json")))
	.when().post("maps/api/place/add/json")
	.then().log().all().assertThat().statusCode(200).extract().asString();
	
	JsonPath js=new JsonPath(response);
	 String placeID=js.getString("place_id");
	 System.out.println("place_id:"+ placeID);
}
}
