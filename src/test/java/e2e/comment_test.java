package e2e;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertEquals;

public class comment_test {
    //Test data
    private String userNameAuth = "webinar5";
    private String userPassAuth = "webinar5";
    private String addCommentUrl = "https://jira.hillel.it/rest/api/2/issue/WEBINAR-12303/comment";
    private String issueUrl = "https://jira.hillel.it/rest/api/2/issue/WEBINAR-12303";
    private String commentValue ="Test comment via REST API";

    @Test
    public void addComment(){
        JSONObject addComment = new JSONObject();
        addComment.put("body",commentValue);

        Response postCommentResponse =
                given().
                        auth().preemptive().basic(userNameAuth,userPassAuth).
                        contentType(ContentType.JSON).
                        body(addComment).
                        when().
                        post(addCommentUrl).
                        then().
                        time(lessThan(5000L)).
                        extract().response();
        String commentUrl = postCommentResponse.path("self");

        //Verify status code
        assertEquals(postCommentResponse.statusCode(),201);
        //Verify content type
        assertEquals(postCommentResponse.contentType(),ContentType.JSON.withCharset("UTF-8").replace(" ",""));
        //Verify that comment is added
        assertEquals("Test comment via REST API", postCommentResponse.path("body"));
        //Verify that author is added
        assertEquals(userNameAuth, postCommentResponse.path("updateAuthor.name"));

        Response deleteCommentResponse =
                given().
                        auth().preemptive().basic(userNameAuth, userPassAuth).
                        when().
                        delete(commentUrl).
                        then().
                        extract().response();
        //Verify status code
        assertEquals(deleteCommentResponse.statusCode(),204);
        //Verify content type
        assertEquals(postCommentResponse.contentType(),ContentType.JSON.withCharset("UTF-8").replace(" ",""));

        Response getDeletedResponse =
                given().
                        auth().preemptive().basic(userNameAuth,userPassAuth).
                        when().
                        get(commentUrl).
                        then().
                        extract().response();
        //Verify status code
        assertEquals(getDeletedResponse.statusCode(), 404);

        Response getIssueResponse =
                given().
                        auth().preemptive().basic(userNameAuth,userPassAuth).
                        when().
                        get(issueUrl).
                        then().
                        extract().response();
        //Verify that comment is NOT added
        Assert.assertFalse(getIssueResponse.toString().contains(commentUrl));
    }

}
