package com.vine.issueviewer.app.intent;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.vine.issueviewer.app.helper.GitIssuesUrlBuilder;
import com.vine.issueviewer.app.model.Comment;
import com.vine.issueviewer.app.model.Issue;
import com.vine.issueviewer.app.model.Response;
import com.vine.issueviewer.app.service.HttpService;
import com.vine.issueviewer.app.view.OnIssueClickListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by samridhi on 05/03/16.
 */
public class LoadCommentData extends AsyncTask<String,Void, Response> {

    private static final String LOG_TAG = LoadCommentData.class.getSimpleName();
    private Response response;
    private final HttpService httpService;
    private Context context;
    private OnIssueClickListener activity;
    private Issue issue;

    public LoadCommentData(Context context, OnIssueClickListener mainActivity, Issue issue) {
        super();
        this.context = context;
        this.activity = mainActivity;
        this.issue = issue;
        this.response = new Response(new JSONArray(),
                HttpURLConnection.HTTP_NO_CONTENT);
        this.httpService = new HttpService(context);
    }


    @Override
    protected Response doInBackground(String... params) {
        final String ORG_NAME = "rails";
        final String REPO_NAME = "rails";
        String urlString = new GitIssuesUrlBuilder(ORG_NAME,REPO_NAME).buildUrlWith(issue);
        try {
            URL url = new URL(urlString);
            Log.d(LOG_TAG, "starting http request on url: " + urlString);
            response = httpService.getIssues(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;

    }

    private void getDataIntoList(JSONArray jsonArrayResponse) {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        for (int i = 0; i < jsonArrayResponse.length(); i++) {
            try {
                JSONObject jsonObject = jsonArrayResponse.getJSONObject(i);
                String body = jsonObject.getString("body");
                String username = jsonObject.getJSONObject("user").getString("login");
                comments.add(new Comment(body,username));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        activity.setCommentAdaptorWith(comments);
    }

    @Override
    protected void onPostExecute(Response response) {
        if (response != null && response.getCode() == HttpURLConnection.HTTP_OK) {
            JSONArray jsonResponse = response.jsonResponse();
            getDataIntoList(jsonResponse);
        } else {
            if(response == null) {
                Log.d(LOG_TAG, "Response is null, check internet Connection");
                Toast.makeText(context, "Response received from api is null, check internet Connection", Toast.LENGTH_SHORT).show();
            }else{
                Log.d(LOG_TAG, "Response code "+ response.getCode());
                Toast.makeText(context, "Response code "+ response.getCode(), Toast.LENGTH_SHORT).show();
            }
        }
    }


}

