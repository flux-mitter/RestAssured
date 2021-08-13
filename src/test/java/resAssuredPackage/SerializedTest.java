package resAssuredPackage;
import io.restassured.RestAssured;



import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;
import resources.payload;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class SerializedTest 
{
	public static void main(String[] args) throws IOException {
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
		RestAssured.baseURI="https://rahulshettyacademy.com";

		AddPlace p =new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Frontline house");
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		p.setLocation(l);
		
		
		Response res=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(p)
				.when().post("/maps/api/place/add/json").
				then().assertThat().statusCode(200).extract().response();

				String responseString=res.asString();
				System.out.println(responseString);
		
		
//		Response response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
//		.body(p).when().post("maps/api/place/add/json")
//		.then().log().all().statusCode(200).body("scope", equalTo("APP")).header("Server",equalTo("Apache/2.4.18 (Ubuntu)")).extract().response();
//		
//		
//		System.out.println(response);
	}
}
