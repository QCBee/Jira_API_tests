package e2e;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class login_test {
    private String userNameAuth = "webinar5";
    private String userPassAuth = "webinar5";
    private String postLoginRequest = "https://jira.hillel.it/rest/auth/1/session/";

    @Test
    public void successfulLogin(){
        Response postLoginResponse =
                given().
                        auth().preemptive().basic(userNameAuth,userPassAuth).
                        contentType(ContentType.JSON).
                        body("{\n" +
                                "\t\"username\":\"webinar5\",\n" +
                                "\t\"password\":\"webinar5\"\n" +
                                "}").
                        when().
                        post(postLoginRequest).
                        then().
                        contentType(ContentType.JSON).
                        statusCode(200).
                        extract().response();
    }
}
