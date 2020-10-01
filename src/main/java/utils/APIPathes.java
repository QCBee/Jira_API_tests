package utils;

public interface APIPathes {
    String baseUrl = "https://jira.hillel.it";
    String issueUrl  = baseUrl + "/rest/api/2/issue/";
    String loginUrl = baseUrl + "/rest/auth/1/session";
    String commentIssueUrl  = issueUrl + "WEBINAR-12303/comment";
}
