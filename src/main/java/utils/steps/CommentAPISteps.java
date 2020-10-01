package utils.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Credentials;
import utils.JiraJSONObjects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CommentAPISteps {

    static String newCommentJSON = JiraJSONObjects.CommentJSON();

    public static Response addComment(){
        Response response =
                given().
                        auth().preemptive().basic(Credentials.userName,Credentials.userPass).
                        contentType(ContentType.JSON).
                        body(newCommentJSON).
                        when().
                        post("https://jira.hillel.it/rest/api/2/issue/WEBINAR-12303/comment").
                        then().
                        time(lessThan(5000L)).
                        extract().response();
        return response;
    }

    public static Response deleteComment(String deleteCommentUrl){
        Response response =
                given().
                        auth().preemptive().basic(Credentials.userName, Credentials.userPass).
                        when().
                        delete(deleteCommentUrl).
                        then().
                        extract().response();
        return response;
    }

    public static Response getDeletedComment(String commentUrl){
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
