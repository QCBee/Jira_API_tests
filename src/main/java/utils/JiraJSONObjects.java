package utils;

import org.json.simple.JSONObject;

public class JiraJSONObjects {
    //Test data
    private static String userName = "webinar5";
    private static String userPass = "webinar5";

    public static String successfulLoginJSON() {
        //JSON Object for Login
        JSONObject login = new JSONObject();
        login.put("username", userName);
        login.put("password", userPass);
        return login.toString();
    }
}
