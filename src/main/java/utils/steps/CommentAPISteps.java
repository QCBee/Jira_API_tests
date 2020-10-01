package utils.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Credentials;
import utils.JiraJSONObjects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static utils.APIPathes.*;

public class CommentAPISteps {

    static String newCommentJSON = JiraJSONObjects.CommentJSON();

    public static Response addComment(){
        Response response =
                given().
                        auth().preemptive().basic(Credentials.userName,Credentials.userPass).
                        contentType(ContentType.JSON).
                        body(newCommentJSON).
                        when().
                        post(commentIssueUrl).
                        then().
                        time(lessThan(5000L)).
                        extract().response();
        return response;
    }

    public static Response deleteComment(String commentUrl){
        Response response =
                given().
                        auth().preemptive().basic(Credentials.userName, Credentials.userPass).
                        when().
                        delete(commentUrl).
                        then().
                        extract().response();

        return response;
    }

    public static Response getComment(String commentUrl){
        Response response =
                given().
                        auth().preemptive().basic(Credentials.userName,Credentials.userPass).
                        when().
                        get(commentUrl).
                        then().
                        extract().response();
        return response;
    }
}
