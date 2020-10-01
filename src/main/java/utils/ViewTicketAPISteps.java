package utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ViewTicketAPISteps {

    public static Response viewTicket(){
        Response response =
                given().
                        auth().preemptive().basic(Credentials.userName,Credentials.userPass).
                        when().
                        get("http://jira.hillel.it/rest/api/2/issue/WEBINAR-12303").
                        then().
                        extract().response();
        return response;
    }
}
