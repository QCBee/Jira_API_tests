package e2e;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.steps.LoginAPISteps;
import utils.TestData;
import static org.testng.Assert.assertEquals;

public class LoginTest {

    @Test
    public void successfulLogin(){
        Response postLoginResponse = LoginAPISteps.successfulLogin();
        assertEquals(postLoginResponse.statusCode(),200);
        assertEquals(TestData.ACTIVE_SESSION_ID, postLoginResponse.path("session.name"));
    }

}
