package com.vine.issueviewer.app.model;

import org.json.JSONArray;

/**
 * Created by samridhi on 05/03/16.
 */
public class Response {
    private JSONArray jsonResponse;
    private int code;

    public Response(JSONArray jsonResponse, int code) {

        this.jsonResponse = jsonResponse;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public JSONArray jsonResponse() {
        return jsonResponse;
    }
}