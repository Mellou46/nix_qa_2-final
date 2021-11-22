package api;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    private static final Logger LOG = LogManager.getLogger(ApiTest.class);

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
//            RestAssured.port = 8081; //если нужна настройка порта
        RestAssured.config = config()
                .logConfig(new LogConfig()
                        .defaultStream(IoBuilder.forLogger(LOG).buildPrintStream()));
    }

    @Test
    public void testGetAllPosts() {
        given()
                .log().all()
                .when()
                .get("/posts")
                .then()
                .assertThat()
                .body("size()", greaterThanOrEqualTo(100))
                .and()
                .body("[0].id", equalTo(1))
                .and()
                .body("[0].userId", equalTo(1))
                .and()
                .body("[0].title", notNullValue())
                .and()
                .body("[0].body", notNullValue())
                .and()
                .statusCode(200);
    }

    @Test
    public void testAddPost() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("userId", "1");
        jsonObj.put("title", "poluchilos");
        jsonObj.put("body", "Ta tochno poluchilos, otvechayu!!!");


        given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(jsonObj.toJSONString())
                .post("/posts")
                .then()
                .assertThat()
                .header("Location", containsString("http://jsonplaceholder.typicode.com/posts/101"))
                .statusCode(201)
                .body("id", equalTo(101))
                .body("userId", notNullValue())
                .body("title", equalTo("poluchilos"));
    }

    @Test
    public void testUpdatePost() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id", 1);
        jsonObj.put("userId", "5");
        jsonObj.put("title", "Post was updated");
        jsonObj.put("body", "68");

        given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(jsonObj.toJSONString())
                .put("/posts/1")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testDeletePost() {
        given()
                .log().all()
                .delete("/posts/1")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
