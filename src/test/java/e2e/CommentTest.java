package e2e;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.steps.CommentAPISteps;
import utils.steps.IssueAPISteps;
import utils.TestData;
import static org.testng.Assert.assertEquals;

public class CommentTest {
    //Test data
    private String issueUrl = "https://jira.hillel.it/rest/api/2/issue/WEBINAR-12303";


    @Test
    public void addCommentTest() {
        //Flow for adding new comment
        Response postCommentResponse = CommentAPISteps.addComment();
        String commentUrl = postCommentResponse.path("self");
        assertEquals(postCommentResponse.statusCode(), 201);
        assertEquals(postCommentResponse.contentType(), ContentType.JSON.withCharset("UTF-8").replace(" ", ""));
        assertEquals(TestData.COMMENT_VALUE, postCommentResponse.path("body"));
        assertEquals(TestData.REPORTER_VALUE, postCommentResponse.path("updateAuthor.name"));

        //Flow for deleting added comment
        Response deleteCommentResponse = CommentAPISteps.deleteComment(commentUrl);
        assertEquals(deleteCommentResponse.statusCode(), 204);
        assertEquals(postCommentResponse.contentType(), ContentType.JSON.withCharset("UTF-8").replace(" ", ""));

        Response getDeletedIssueResponse = CommentAPISteps.getDeletedComment(commentUrl);
        assertEquals(getDeletedIssueResponse.statusCode(),404);

        Response getIssueResponse = IssueAPISteps.getIssue(issueUrl);
        Assert.assertFalse(getIssueResponse.toString().contains(commentUrl));
    }
}
