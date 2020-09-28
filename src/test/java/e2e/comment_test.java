//package e2e;
//
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import org.testng.annotations.Test;
//
//import static io.restassured.RestAssured.given;
//
//
//public class comment_test {
//    private String userNameAuth = "webinar5";
//    private String userPassAuth = "webinar5";
//    private String postCommentUrl = "https://jira.hillel.it/rest/api/2/issue/WEBINAR-12303/comment";
//    @Test
//    private void addComment(){
//        Response postComment =
//                given().
//                        auth().preemptive().basic(userNameAuth,userPassAuth).
//                        contentType(ContentType.JSON).
//                        body("{\n" +
//                                "\t\"body\":\"Test comment via REST API\"\n" +
//                                "}").
//                        when().
//                        post(postCommentUrl).
//                        then().
//
//    }
//
//}
