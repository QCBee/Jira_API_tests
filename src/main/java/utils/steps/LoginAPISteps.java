package utils.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.JiraJSONObjects;

import static io.restassured.RestAssured.given;

public class LoginAPISteps {

    static String successfulLoginJSON = JiraJSONObjects.successfulLoginJSON();

    public static Response successfulLogin(){
        Response response =
                given().
                        contentType(ContentType.JSON).
                        body(successfulLoginJSON).
                        when().
                        post("https://jira.hillel.it/rest/auth/1/session/").
                        then().
                        extract().response();
        return response;
    }

}
