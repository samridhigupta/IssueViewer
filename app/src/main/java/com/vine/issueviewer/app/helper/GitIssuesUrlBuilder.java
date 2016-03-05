package com.vine.issueviewer.app.helper;

import android.net.Uri;
import com.vine.issueviewer.app.model.Issue;

/**
 * Created by samridhi on 05/03/16.
 */
public class GitIssuesUrlBuilder {

    private static final String SCHEME = "https";
    private static final String HOST = "api.github.com";
    private static final String ISSUES = "issues";
    private static final String COMMENTS = "comments";
    private static final String REPOS = "repos";
    private final String org;
    private final String repo;

    public GitIssuesUrlBuilder(String org, String repo){
        this.org = org;
        this.repo = repo;
    }

    public String buildUrlWith(String paramName,String paramValue, int pageNum){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(SCHEME)
                .authority(HOST)
                .appendPath(REPOS)
                .appendPath(org)
                .appendPath(repo)
                .appendPath(ISSUES)
                .appendQueryParameter(paramName, paramValue)
                .appendQueryParameter("page", String.valueOf(pageNum));
        Uri builtUri = builder.build();
        return (builtUri.toString());
    }

    public String buildUrlWith(Issue issue) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(SCHEME)
                .authority(HOST)
                .appendPath(REPOS)
                .appendPath(org)
                .appendPath(repo)
                .appendPath(ISSUES)
                .appendPath(String.valueOf(issue.getIssueNumber()))
                .appendPath(COMMENTS);
        Uri builtUri = builder.build();
        return (builtUri.toString());
    }
}