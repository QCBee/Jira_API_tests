package e2e;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ViewTicketAPISteps;
import static org.testng.Assert.assertEquals;

public class ExistingIssueTest {
    private String userNameAuth = "webinar5";
    private String userPassAuth = "webinar5";
    private String ticketUrl = "";

    @Test
    public void getExistingIssue(){
        Response getExistingIssueResponse = ViewTicketAPISteps.viewTicket();
        assertEquals(getExistingIssueResponse.statusCode(),200);
        assertEquals("WEBINAR-12303", getExistingIssueResponse.path("key"));
    }
}
