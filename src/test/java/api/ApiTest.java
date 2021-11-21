package api;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTest {

    @Test
    void getTest(){
        given().when().get("https://google.com").then().statusCode(200).contentType("text/html; charset=ISO-8859-1").log()
                .all();
    }

}
