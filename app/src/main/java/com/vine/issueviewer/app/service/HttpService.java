package com.vine.issueviewer.app.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.vine.issueviewer.app.model.Response;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by samridhi on 05/03/16.
 */
public class HttpService {

    private Context context;

    public HttpService(Context context) {

        this.context = context;
    }

    public Response getIssues(URL url) throws IOException {
        InputStream inputStream = null;
        int responseCode = -1;
        JSONArray jsonResponse = null;

        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            responseCode = conn.getResponseCode();
            inputStream = conn.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            if (sb.length() == 0) {
                return null;
            }
            jsonResponse = new JSONArray(sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            return new Response(jsonResponse, responseCode);
        }
    }
}

