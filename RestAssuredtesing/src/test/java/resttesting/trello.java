package resttesting;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class trello {
	//need to give base url
	public static String baseurl = "https://api.trello.com/";
	public static String BoardId;
	@Test(priority=0)
	public void createBoard()
	{
	 RestAssured.baseURI = baseurl;
	
	 Response response = given().queryParam("name","nikhil goud")
	 .queryParam("key","2ab12e59ba8a457e31c87aa001a8be3d")
	 .queryParam("token","037ce080e41c7fa48ac77b00b293ed8f6517ae35d1d28aad295ed24295817658")
	 .header("Content-Type","application/json")
	
	 .when()
	 .post("1/boards/")
	
	 .then()
	 .assertThat().statusCode(200).contentType(ContentType.JSON)
	 .extract().response();
	 String jsonresponse = response.asString();
		//i want to convert the response in to json format
		//why do i use jsonpath to convert the string in to a json format
		JsonPath js = new JsonPath(jsonresponse);
		//nw i have to fetch the id
		BoardId = js.get("id");
		System.out.println(jsonresponse);
	}
	
	@Test(priority = 1)
	public void getBoard()
	{
		RestAssured.baseURI = baseurl;
		
		
	Response response =	given()
		.queryParam("key", "2ab12e59ba8a457e31c87aa001a8be3d")
		.queryParam("token", "037ce080e41c7fa48ac77b00b293ed8f6517ae35d1d28aad295ed24295817658")
		.header("Content-Type","application/json")
		
		.when()
		.get("1/boards/"+BoardId)
		
		.then()
		.assertThat().contentType(ContentType.JSON)
		.extract().response();	
		String jsonresponse = response.asString();
		
		System.out.println(jsonresponse);
	}
	
	@Test(priority = 2)
	public void DeleteBoard()
	{
		RestAssured.baseURI = baseurl;
		
		
	Response response =	given()
		.queryParam("key", "2ab12e59ba8a457e31c87aa001a8be3d")
		.queryParam("token", "037ce080e41c7fa48ac77b00b293ed8f6517ae35d1d28aad295ed24295817658")
		.header("Content-Type","application/json")
		
		.when()
		.delete("1/boards/"+BoardId)
		
		.then()
		.assertThat().contentType(ContentType.JSON)
		.extract().response();	
		String jsonresponse = response.asString();
		
		System.out.println(jsonresponse);
	}
}