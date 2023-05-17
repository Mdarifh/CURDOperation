package crudOperation;

import org.testng.annotations.Test;
import org.json.JSONObject;
import org.testng.Assert;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;// https://reqres.in/api/users/2
import io.restassured.specification.RequestSpecification;


public class CRUDOperations {

	@Test(priority=1)
	public void HTTPRequest() {
		Response res = get("https://reqres.in/api/users?page=2");
		System.out.println(res.asString());
		System.out.println("Status Code: "+ res.getStatusCode());
		System.out.println("Response response Body: " + res.getBody().asString());
		System.out.println("Response response Time: " + res.getTime());
		System.out.println("Response response Header: " + res.getHeader("Content-Type"));

		// Validate StatusCode
		// Expected Status Code = 200
		int expectedStatusCode = 200;
		int  actualStatusCode  = res.getStatusCode();
		Assert.assertEquals(expectedStatusCode ,actualStatusCode);
	}
	
	@Test(priority=2)
	public void GetRequest() {
		baseURI = "https://reqres.in/api/users";
		
		 given().queryParam("page",2)
		
		.when().get()
		
		.then().statusCode(200);
	}
	
	@Test(priority=3)
	 public void Create() {
		 JSONObject jsonData = new JSONObject();
		 jsonData.put("Name", "Arif");
		 jsonData.put("Location","Chakiya");
		 jsonData.put("email","mdimranzama3110@gmail.com");
		 jsonData.put("Phone","7061651398");
		 jsonData.put("Job","Automation Engineer");
		 
		// RestAssured.baseURI = "https://reqres.in/api/users";
		 
		 RestAssured.given()
		 .header("Content-type","application/json")
		 .contentType(ContentType.JSON)
		 .body(jsonData.toString())
		 
		 .when().post("https://reqres.in/api/users")
		 
		 .then().statusCode(201)
		 .log().all();
		 
		 
	 }
	@Test(priority=4)
	public void Update() {
		JSONObject jsonData = new JSONObject();
		 jsonData.put("Name", "Zoya");
		 jsonData.put("Location","Milki");
		 jsonData.put("email","zoya30@gmail.com");
		 jsonData.put("Job","Teacher");
		 
		 RestAssured.given()
		 .header("Content-type","application/json")
		 .contentType(ContentType.JSON)
		 .body(jsonData.toString())
		 
		 .when().put("https://reqres.in/api/users/789")
		 
		 .then().statusCode(200)
		 .log().all();
	}
	
	@Test(priority=5)
	public void PATCH() {
		JSONObject jsonData = new JSONObject();
		 jsonData.put("Name", "Zoya");
		 jsonData.put("Location","Bangalore");
		 jsonData.put("email","zoya30@gmail.com");
		 jsonData.put("Job","Teacher");
		 
		 RestAssured.given()
		 .header("Content-type","application/json")
		 .contentType(ContentType.JSON)
		 .body(jsonData.toString())
		 
		 .when().patch("https://reqres.in/api/users/789")
		 
		 .then().statusCode(200)
		 .log().all();
	}
	@Test(priority=6)
		public void Delete() {
		RestAssured.given()
		 
		.when().delete("https://reqres.in/api/users/65")
		 
		 .then().statusCode(204)
		 .log().all();
	}


}