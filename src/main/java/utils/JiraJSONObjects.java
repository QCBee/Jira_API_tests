package utils;

import org.json.simple.JSONObject;

import static utils.TestData.*;

public class JiraJSONObjects {

    public static String successfulLoginJSON() {
        JSONObject login = new JSONObject();
        login.put("username", VALID_USER_NAME);
        login.put("password", VALID_USER_PASS);
        return login.toString();
    }

    public static String newIssueJSON(){
        //JSON Object for Create New Issue
        JSONObject newIssue = new JSONObject();
        JSONObject fields = new JSONObject();
        JSONObject issueType = new JSONObject();
        JSONObject project = new JSONObject();
        JSONObject reporter = new JSONObject();

        fields.put("summary", SUMMARY_VALUE);
        fields.put("issuetype", issueType);
        fields.put("project", project);
        fields.put("reporter", reporter);
        issueType.put("id", ID_ISSUE_VALUE);
        issueType.put("name", ISSUE_TYPE_VALUE);
        project.put("id", PROJECT_ID_VALUE);
        reporter.put("name", REPORTER_VALUE);
        newIssue.put("fields", fields);
        return newIssue.toString();
    }

    public static String CommentJSON(){
        JSONObject comment = new JSONObject();
        comment.put("body", COMMENT_VALUE);
        return comment.toString();
    }
}
