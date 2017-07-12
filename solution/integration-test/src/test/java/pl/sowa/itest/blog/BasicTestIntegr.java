/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sowa.itest.blog;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import static com.jayway.restassured.path.json.JsonPath.from;
import com.jayway.restassured.response.Response;
import java.util.List;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.is;
import org.jglue.fluentjson.JsonBuilderFactory;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author pawel
 */


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasicTestIntegr {
    private final static String SERVER_BASE = "http://localhost";
    private final static int    SERVER_PORT = 8080;
    private final static String BASE_PATH = "blog-web/posts/";
    
    public BasicTestIntegr() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        RestAssured.port = SERVER_PORT;
        RestAssured.baseURI = SERVER_BASE;
        RestAssured.basePath = BASE_PATH;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    
    final static Long TESTING_IDENTITY = 1L;
    final static String TESTING_TITLE   = "brilliant entry";
    final static String TESTING_CONTENT = "brilliant content";
    
    
     private void emptyBody(){
            given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                get("").
                then().body("", Matchers.hasSize(0));
    }
    
    /**
    * make the tests deterministic, keep the order of tests
    * @author pawel
    */
    @Test
    public void t0DeleteAllPosts(){
        Response response = given().
                            accept(ContentType.JSON).
                            contentType(ContentType.JSON).
                            get("").
                            then().contentType(ContentType.JSON).statusCode(200).
                            extract().response();

        for(Integer id : response.<List<Integer>>path("id")){
            given().
            delete(id.toString()).
            then().statusCode(200);
        }
    }
    
 
    
    @Test
    public void t1EmptyBody() {
        emptyBody();
    }
    
   
    
    @Test
    public void t2CreateEntry() {
           given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(    JsonBuilderFactory.buildObject().
                                    add("id", TESTING_IDENTITY).
                                    add("title", TESTING_TITLE).
                                    add("content", TESTING_CONTENT).toString()) .
                post("").
                then().statusCode(200).body("any { it.key == 'example' }", is(true));
    
    }
    
    @Test
    public void t3ReadEntry(){
           Response response = given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                get(TESTING_IDENTITY.toString()).
                then().statusCode(200).extract().response();
           
           
           assertEquals((Long)TESTING_IDENTITY, (Long)from(response.asString()).getLong("id"));
           assertEquals(TESTING_TITLE, from(response.asString()).getString("title"));
           assertEquals(TESTING_CONTENT, from(response.asString()).getString("content"));
    }
    
    @Test
    public void t4DeleteEntry(){
            given().
            delete(TESTING_IDENTITY.toString()).
            then().statusCode(200);
    }
    
    @Test
    public void t5EmptyBody(){
        emptyBody();
    }
    
    @Test 
    public void t6DeleteNonExisting(){
            given().
            delete(TESTING_IDENTITY.toString()).
            then().statusCode(404);
    }
    
    @Test 
    public void t6getNonExisting(){
            given().
            get(TESTING_IDENTITY.toString()).
            then().statusCode(204);
    }
    
}
