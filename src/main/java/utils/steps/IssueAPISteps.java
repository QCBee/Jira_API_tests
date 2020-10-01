package utils.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Credentials;
import utils.JiraJSONObjects;

import static io.restassured.RestAssured.given;

public class IssueAPISteps {

    static  String newIssueJSON = JiraJSONObjects.newIssueJSON();
    private String userNameAuth = "webinar5";
    private String userPassAuth = "webinar5";
    private String getExistingIssueRequest = "http://jira.hillel.it/rest/api/2/issue/WEBINAR-12303";
    private String postCreateIssueRequest = "https://jira.hillel.it/rest/api/2/issue";
    private static String ticketUrl = "http://jira.hillel.it/rest/api/2/issue/";

    public static Response createIssue(){
        Response response =
            given().
                    auth().preemptive().basic(Credentials.userName,Credentials.userPass).
                    contentType(ContentType.JSON).
                    body(newIssueJSON).
                    when().
                    post("https://jira.hillel.it/rest/api/2/issue").
                    then().
                    extract().response();
            return response;
    }

    public static Response getIssue(String ticketId){
        Response response =
                given().
                        auth().preemptive().basic(Credentials.userName,Credentials.userPass).
                        when().
                        get(ticketUrl + ticketId).
                        then().
                        extract().response();
        return response;
    }
}
