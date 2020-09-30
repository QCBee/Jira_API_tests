package e2e;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.LoginAPISteps;
import static org.testng.Assert.assertEquals;

public class SuccessfulLoginTest {

    @Test
    public void successfulLogin(){
        Response postLoginResponse = LoginAPISteps.successfulLogin();
        assertEquals(postLoginResponse.statusCode(),200);
        assertEquals("JSESSIONID", postLoginResponse.path("session.name"));
    }
}
