package com.vine.issueviewer.app.model;

/**
 * Created by samridhi on 05/03/16.
 */
public class Issue {
    private String title;
    private String body;
    private String commentUrl;
    private int issueNumber;

    public Issue(String title, String body, String commentUrl, int issueNumber) {
        this.title = title;
        this.body = body;
        this.commentUrl = commentUrl;
        this.issueNumber = issueNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getCommentUrl() {
        return commentUrl;
    }

    public int getIssueNumber() {
        return issueNumber;
    }
}
