package e2e;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ExistingIssueTest {
    private String userNameAuth = "webinar5";
    private String userPassAuth = "webinar5";
    private String ticketUrl = "http://jira.hillel.it/rest/api/2/issue/WEBINAR-12303";
    @Test
    public void getExistingIssue(){
        Response getExistingIssueResponse =
                given().
                        auth().preemptive().basic(userNameAuth,userPassAuth).
                        when().
                        get(ticketUrl).
                        then().
                        extract().response();
        assertEquals(getExistingIssueResponse.statusCode(),200);
        assertEquals("WEBINAR-12303", getExistingIssueResponse.path("key"));
    }
}
