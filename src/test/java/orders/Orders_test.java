package orders;

import core.StatusCode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import java.util.List;
import java.util.Map;
import utils.FileReader;

public class Orders_test {
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5NTgiLCJqdGkiOiI0Y2ExNmIxNmNjOTdhZWJjOTFmZTU3MTMwMWQ3YzQ5ZDI5OWRlMTAxODE4MmE5ZjkyMWVlNGIxYjg1YTM3Y2M1N2Y3MWNlMWJmZmZlNmJlYyIsImlhdCI6MTcyOTM0OTc3MCwibmJmIjoxNzI5MzQ5NzcwLCJleHAiOjE3NjA4ODU3NzAsInN1YiI6IiIsInNjb3BlcyI6W119.5nNA0vxsRZdx1PaymYYJr-ABbgU3T_RsGPTrH7Sm1O27ndNXm1faxRJUjUTtxPTDQgYpCFI06ziq0DVxJklmNXk-0MA35bhs3bdb7hRxcaRoYu5QSmJqaiLETbkhnfopq933Q1xksJAXyVkZ9AGaHmFCR-cAdyPpoCFwx2xqM5LL6RWWRNPjpKWN1bWWplvCZtTHO5s3Ix2lxeBxq3rvyHA3ukI09Ex_7GnrQdIf55Pgln8XD6ZapEOV2h8WZnJ8yaU2-tls2pIHxODLCy6BSL7wF5cEw_z855RBJJIMjvFgTYF8ghfYMpvnEyjytxHK6oVvnk0sF22HOQRAkufRA0gjMev_IytaZKwfDXWwZT4nAJ0qhT_d3JyQXbclNpTG1DeW3HbXoWsVU5Zt90zTgXt8gGYEomi7zfra0aMvXVV3IdHHaiKHlt6JM3h-MWQMlpInFeQcSYnkrGL6HLX3azjI9jEkyVh90XQRrd5Mnm8OZkHTV7lCioBzYfORx7c6bB18R_rB1zPKHOK56HtdEC1eHN4skC7d0n2H8ka5eMvwHPi1LGE4wk2RHmIxxHdJYgwk0Q2ZQDkgngZIlmKIMaWGG6B6_fUOk9YGc0R2Tmj2jBJXehyBa1UAxQ9P1tC6IZ4ClFPgoElAYWsHvPYmSuoPgyFZtOUkXC3bMUnCh60";

    @BeforeClass
    public void setUp(){
        baseURI = "https://sandbox-partners-api.airalo.com/v2/orders";
    }
    /***
     * TC_01 --> Verify Submit Order endpoint
     *
     */
    @Test(description = "Submit Order")
    public void post_an_order() {
        Response response;

        response = given()
                .auth().oauth2(token)
                .header("Content-Type","application/json")
                .body(FileReader.getTestData("ordersTestData.json"))
                .when()
                .post();


        System.out.println(response.body().asString());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.statusCode(), StatusCode.SUCCESS.code,StatusCode.SUCCESS.msg);
        Assert.assertEquals(response.jsonPath().getMap("data").get("quantity"),6);
        Assert.assertEquals(response.jsonPath().getMap("data").get("package_id"),"merhaba-7days-1gb");
        Assert.assertEquals(response.jsonPath().getMap("data").get("type"),"sim");
        Assert.assertEquals(response.jsonPath().getMap("data").get("brand_settings_name"),null);
        System.out.println("Post operation is successful!!");

    }

    /***
     * TC_02 --> Verify Get Order List endpoint
     *
     */
    @Test(description = "Get Order List")
    public void get_list_of_orders() {
        Response response;

        response = given()
                .auth().oauth2(token)
                .header("Content-Type","application/json")
                .when()
                .get();

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.statusCode(), StatusCode.SUCCESS.code,StatusCode.SUCCESS.msg);
        List<Object> results = response.jsonPath().getList("data");
        for(int i=0; i<results.size(); i++){
            Map<String, Object> res1 = (Map<String, Object>) results.get(i);
            Assert.assertEquals(res1.get("quantity"),6);
            Assert.assertEquals(res1.get("package_id"),"merhaba-7days-1gb");
        }
        System.out.println("Get operation is successful!!");
    }

}
