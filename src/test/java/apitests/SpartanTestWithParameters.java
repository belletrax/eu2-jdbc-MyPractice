package apitests;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanTestWithParameters {

    @BeforeClass
    public void beforeClass(){
        baseURI="http://54.157.154.248:8000";
    }


    /*
      Given accept type is Json
      And Id parameter value is 5
      When user sends GET request to /api/spartans/{id}
      Then response status code should be 200
      And response content-type: application/json;charset=UTF-8
      And "Blythe" should be in response payload
   */

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .and().pathParam("id",5)
                .when().get("/api/spartans/{id}");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");
        Assert.assertTrue(response.body().asString().contains("Blythe"));


    }

    //Given accept type is Json
    //And id parameter value 500
    //When user sends get request to/api/spartans/{id}
    //Then response status code should be 404
    //And response contenttype ;application/json;charset=UFT-8
    //And Spartan Not Foundmessage should be display
    @Test
    public void test2(){


        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .and().pathParam("id",500)
                .when().get("/api/spartans/{id}");
           Assert.assertEquals(response.statusCode(),404);
           Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");
           Assert.assertTrue(response.body().asString().contains("Spartan Not Found"));

    }

      //Given accept type is json
    //And query parameter values are:
    //gender/Female
    //nameContains/e
    //When user request get request to api/spartans/search
    //Then response status code should be 200
    //And response content-type: app/json;charset=UFT-8
    //And

    @Test
    public void positiveTestWithQueryParam(){

        Response response = given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .queryParam("gender","Female")
                .queryParam("nameContains","e")
                .when().get("/api/spartans/search");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");
        Assert.assertTrue(response.body().asString().contains("Janette"));
        Assert.assertTrue(response.body().asString().contains("Female"));

    }
    @Test
    public void positiveTestWithQueryParamWithMaps(){
        //create a map and add parameters
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        Response response = given().accept(ContentType.JSON).and()
                .auth().basic("admin", "admin")
                .queryParams(queryMap)
                .when().get("/api/spartans/search");

        //response verification
      /*  assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        //verify body contains
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));*/
    }

}
