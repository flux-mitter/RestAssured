package resAssuredPackage;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import resources.payload;

public class complexJsonParse {

	
	//1.Print No of courses returned by API
	
	@Test(priority=1)
	public void courseNumber()
	{
		JsonPath js =  new JsonPath(payload.CoursePrice());
		int noOfCourses =  js.getInt("courses.size()");
		System.out.println(noOfCourses);
	}
	
	// 2.Print Purchase Amount
	@Test(priority=2)
	public void purchaseAmount() {
		JsonPath js =  new JsonPath(payload.CoursePrice());
		int purchaseAcount= js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAcount);
	}
	
	// 3.Print Title of the first course
	@Test(priority=3)
	public void firstCouseTitle() {
		JsonPath js =  new JsonPath(payload.CoursePrice());
		String firstCourse= js.getString("courses[2].title");
		System.out.println(firstCourse);
	}
	
	// 4.Print All course titles and their respective Prices
	@Test(priority=4)
	public void coursePrices() {
		JsonPath js =  new JsonPath(payload.CoursePrice());
		int noOfCourses =  js.getInt("courses.size()");
		for (int i = 0; i < noOfCourses; i++) {
			System.out.println(js.getString("courses["+i+"].title"));
			
		}
		//String firstCourse= js.getString("courses[0].title");
		//System.out.println(firstCourse);
	}
	//5. Print no of copies sold by RPA Course
	@Test(priority=5)
	public void noOfCourseSoldByRPA() {
		JsonPath js =  new JsonPath(payload.CoursePrice());
		int noOfCourses =  js.getInt("courses.size()");
		for (int i = 0; i < noOfCourses; i++) {
			System.out.println(js.getString("courses["+i+"].price"));
			
		}
		
	}
	
	//6. 6. Verify if Sum of all Course prices matches with Purchase Amount
	@Test(priority=6)
	public void verifyPurchaseAmountandTotalPurchase() 
	{
		JsonPath js =  new JsonPath(payload.CoursePrice());
		int noOfCourses =  js.getInt("courses.size()");
		int sum=0,price=0,copies=0;
		for (int i = 0; i < noOfCourses; i++) {
			price=js.getInt("courses["+i+"].price");
			copies=js.getInt("courses["+i+"].copies");
			int total=price*copies;
			sum=sum+total;
			System.out.println(total);
		}
		System.out.println(sum);
		int purchaseAcount= js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAcount);
	}
	
}
