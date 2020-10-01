package utils.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.APIPathes;
import utils.Credentials;
import utils.JiraJSONObjects;

import static io.restassured.RestAssured.given;

public class IssueAPISteps {

    static  String newIssueJSON = JiraJSONObjects.newIssueJSON();

    public static Response createIssue(){
        Response response =
            given().
                    auth().preemptive().basic(Credentials.userName,Credentials.userPass).
                    contentType(ContentType.JSON).
                    body(newIssueJSON).
                    when().
                    post(APIPathes.issueUrl).
                    then().
                    extract().response();
            return response;
    }

    public static Response getIssue(String ticketId){
        Response response =
                given().
                        auth().preemptive().basic(Credentials.userName,Credentials.userPass).
                        when().
                        get(APIPathes.issueUrl + ticketId).
                        then().
                        extract().response();

        return response;
    }

    public static Response deleteIssue(String ticketId){
        Response response =
                given().
                        auth().preemptive().basic(Credentials.userName,Credentials.userPass).
                        contentType(ContentType.JSON).
                        when().
                        delete(APIPathes.issueUrl + ticketId).
                        then().
                        extract().response();
        return response;
    }
}
