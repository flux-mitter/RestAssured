package resAssuredPackage;

import io.restassured.RestAssured;


import io.restassured.path.json.JsonPath;
import resources.payload;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;

public class restAssuredFirstClass {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
//		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
//		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
//		.then().log().all().statusCode(200).body("scope", equalTo("APP")).header("Server",equalTo("Apache/2.4.18 (Ubuntu)")).extract().response().toString();
		
//		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
//		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
//		.then().statusCode(200).body("scope", equalTo("APP")).header("Server",equalTo("Apache/2.4.18 (Ubuntu)")).extract().response().asString();
//		
		
	//	System.setProperty("webdriver.chrome.driver", s+"//chromedriver");
		String directory=System.getProperty("user.dir")+"//Json Payload//addPlace.json";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(new String(Files.readAllBytes(Paths.get(directory)))).when().post("maps/api/place/add/json")
		.then().statusCode(200).body("scope", equalTo("APP")).header("Server",equalTo("Apache/2.4.18 (Ubuntu)")).extract().response().asString();
		
		System.out.println("Preetam response = "+response);
		
		JsonPath js =  new JsonPath(response);  //parsing Json
		String placeID=js.getString("place_id");
		System.out.println(placeID);
		String newPlace = "Testing";
		
		//update 
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(payload.updatePlace(placeID,newPlace)).when()
		.put("maps/api/place/update/json").then().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//get
		
		String getPlaceResponse=given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeID)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1 = new JsonPath(getPlaceResponse);
		String newPlaceResponse= js1.getString("address");
		System.out.println(newPlaceResponse);
		
		
		
	}

}
