package e2e;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.steps.IssueAPISteps;
import utils.TestData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class IssueTest {

    @Test
    public void createIssueTest(){
        Response postCreateIssueResponse = IssueAPISteps.createIssue();
        assertEquals(postCreateIssueResponse.statusCode(),201);
        assertEquals(postCreateIssueResponse.contentType(), ContentType.JSON.withCharset("UTF-8").replace(" ",""));
        assertTrue(postCreateIssueResponse.path("key").toString().contains("WEBINAR-"));

        String ticketId = postCreateIssueResponse.path("key");
        System.out.println("Ticket Key: " + ticketId);
        Response getJustCreatedIssueResponse = IssueAPISteps.getIssue(ticketId);
        assertEquals(getJustCreatedIssueResponse.statusCode(),200);
        assertEquals(ticketId, getJustCreatedIssueResponse.path("key"));
        assertEquals(TestData.SUMMARY_VALUE, getJustCreatedIssueResponse.path("fields.summary"));
        assertEquals(TestData.ISSUE_TYPE_VALUE, getJustCreatedIssueResponse.path("fields.issuetype.name"));
        assertEquals(TestData.PROJECT_VALUE,getJustCreatedIssueResponse.path("fields.project.name"));
        assertEquals(TestData.REPORTER_VALUE, getJustCreatedIssueResponse.path("fields.reporter.name"));

        Response deleteIssueResponse = IssueAPISteps.deleteIssue(ticketId);
        assertEquals(deleteIssueResponse.statusCode(),204);

        Response getDeletedIssueResponse = IssueAPISteps.getIssue(ticketId);
        assertEquals(getDeletedIssueResponse.statusCode(),404);
    }

}
