package utils.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.APIPathes;
import utils.Credentials;
import utils.JiraJSONObjects;

import static io.restassured.RestAssured.given;

public class LoginAPISteps {

    static String successfulLoginJSON = JiraJSONObjects.successfulLoginJSON();

    public static Response successfulLogin(){
        Response response =
                given().
                        auth().preemptive().basic(Credentials.userName,Credentials.userPass).
                        contentType(ContentType.JSON).
                        body(successfulLoginJSON).
                        when().
                        post(APIPathes.loginUrl).
                        then().
                        extract().response();
        return response;
    }

}
