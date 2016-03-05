package com.vine.issueviewer.app.model;

/**
 * Created by samridhi on 05/03/16.
 */
public class Comment {
    private String body;
    private String userName;

    public String getBody() {
        return body;
    }

    public Comment(String body, String userName) {
        this.body = body;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}

