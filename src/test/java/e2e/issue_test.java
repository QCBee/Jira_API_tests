package e2e;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class issue_test {
    private String userNameAuth = "webinar5";
    private String userPassAuth = "webinar5";
    private String getExistingIssueRequest = "http://jira.hillel.it/rest/api/2/issue/WEBINAR-12303";
    private String postCreateIssueRequest = "https://jira.hillel.it/rest/api/2/issue";
    private String getJustCreatedIssuePartialRequest = "http://jira.hillel.it/rest/api/2/issue/";

    @Test
    public void createNewIssue(){
        Response postCreateIssueResponse =
                given().
                        auth().preemptive().basic(userNameAuth,userPassAuth).
                        contentType(ContentType.JSON).
                        body("{\n" +
                                "   \"fields\":{\n" +
                                "      \"summary\":\"Test task via API\",\n" +
                                "      \"issuetype\":{\n" +
                                "         \"id\":\"10105\",\n" +
                                "         \"name\":\"Task\"\n" +
                                "      },\n" +
                                "      \"project\":{\n" +
                                "         \"id\":\"10508\"\n" +
                                "      },\n" +
                                "   \"reporter\": {\n" +
                                "      \"name\": \"webinar5\"\n" +
                                "    }\n" +
                                "   }\n" +
                                "}\u2029").
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

    @Test
    public void getExistingIssue(){
        Response getExistingIssueResponse =
            given().
                    auth().preemptive().basic(userNameAuth,userPassAuth).
                    when().
                    get(getExistingIssueRequest).
                    then().
                    extract().response();
        assertEquals(getExistingIssueResponse.statusCode(),200);
        assertEquals("WEBINAR-12303", getExistingIssueResponse.path("key"));
    }
}
