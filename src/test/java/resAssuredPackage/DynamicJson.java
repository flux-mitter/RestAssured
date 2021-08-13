package resAssuredPackage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.payload;
import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.*;

public class DynamicJson {
//leetcode

	@Test(dataProvider="BooksData")
	public void Book(String isbn, String aisle)
	{
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String resp= given().when().header("Content-Type", "text/plain").log().all()
				.body(payload.addBook(isbn, aisle)).when().put("Library/Addbook.php").then().log().all().statusCode(200).extract().response().asString();
		//System.out.println(resp);

		JsonPath js1 = ReUsableMethods.rawToJson(resp);
		String id= js1.get("ID");
		System.out.println(id);
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		return new Object[][] {{"arr1","1"},{"arr12","12"},{"arr13","13"}};
	}
}
