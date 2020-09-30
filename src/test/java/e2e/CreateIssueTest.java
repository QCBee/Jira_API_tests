package e2e;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateIssueTest {
    private String userNameAuth = "webinar5";
    private String userPassAuth = "webinar5";
    private String postCreateIssueRequest = "https://jira.hillel.it/rest/api/2/issue";
    private String getJustCreatedIssuePartialRequest = "http://jira.hillel.it/rest/api/2/issue/";

    //Test data for create issue test
    private String summaryValue = "Test task via API";
    private String idIssueValue = "10105";
    private String issueTypeValue = "Task";
    private String projectIdValue = "10508";
    private String reporterValue = "webinar5";

    @Test
    public void createNewIssue(){
        //JSON Object for Create New Issue
        JSONObject newIssue = new JSONObject();
        JSONObject fields = new JSONObject();
        JSONObject issueType = new JSONObject();
        JSONObject project = new JSONObject();
        JSONObject reporter = new JSONObject();

        fields.put("summary", summaryValue);
        fields.put("issuetype", issueType);
        fields.put("project", project);
        fields.put("reporter", reporter);
        issueType.put("id",idIssueValue);
        issueType.put("name", issueTypeValue);
        project.put("id",projectIdValue);
        reporter.put("name", reporterValue);
        newIssue.put("fields", fields);

        Response postCreateIssueResponse =
                given().
                        auth().preemptive().basic(userNameAuth,userPassAuth).
                        contentType(ContentType.JSON).
                        body(newIssue.toString()).
                        when().
                        post(postCreateIssueRequest).
                        then().
                        extract().response();
        String issueId = postCreateIssueResponse.path("key");
        //Verify status code
        assertEquals(postCreateIssueResponse.statusCode(),201);
        //Verify content type
        assertEquals(postCreateIssueResponse.contentType(), ContentType.JSON.withCharset("UTF-8").replace(" ",""));
        String getJustCreatedIssueFullRequest = getJustCreatedIssuePartialRequest + issueId;
        Response getJustCreatedIssueResponse =
                given().
                        auth().preemptive().basic(userNameAuth,userPassAuth).
                        when().
                        get(getJustCreatedIssueFullRequest).
                        then().
                        extract().response();
        assertEquals(getJustCreatedIssueResponse.statusCode(),200);
        //Verify that issue type id is correct
        assertEquals(issueId, getJustCreatedIssueResponse.path("key"));
        //Verify that summary is correct
        assertEquals("Test task via API", getJustCreatedIssueResponse.path("fields.summary"));
        //Verify that task type is correct
        assertEquals("Task", getJustCreatedIssueResponse.path("fields.issuetype.name"));
        //Verify that project name is correct
        assertEquals("Webinar",getJustCreatedIssueResponse.path("fields.project.name"));
        //Verify that reporter is correct
        assertEquals(userNameAuth, getJustCreatedIssueResponse.path("fields.reporter.name"));
    }


}
