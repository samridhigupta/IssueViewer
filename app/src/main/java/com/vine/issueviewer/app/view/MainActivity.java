package com.vine.issueviewer.app.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.vine.issueviewer.app.R;
import com.vine.issueviewer.app.intent.LoadFeedData;
import com.vine.issueviewer.app.helper.NetworkUtil;
import com.vine.issueviewer.app.model.Issue;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    Button nextButton;
    Button previousButton;
    IssueListAdapter adapter;
    ListView listView;
    private ArrayList<Issue> issues;
    private int pageNumber =1;
    private NetworkUtil networkUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "creating");
        setContentView(R.layout.activity_main);
        nextButton = (Button)findViewById(R.id.nextButton);
        previousButton = (Button)findViewById(R.id.previousButton);
        previousButton.setEnabled(false);

        networkUtil = new NetworkUtil(this);
        if(networkUtil.hasActiveNetwork()){
            listView = (ListView)findViewById(R.id.listview_issues);
            issues = new ArrayList<Issue>();
            adapter = new IssueListAdapter(this, issues);
            listView.setAdapter(adapter);

            updateList();

            final MainActivity thisActivity = this;
            final Context context = this;
            OnIssueClickListener onIssueClickListener = new OnIssueClickListener(context,thisActivity);
            listView.setOnItemClickListener(onIssueClickListener.getListener(getLayoutInflater()));
        }else{
            Toast.makeText(this, "check internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    public void showPreviousIssues(View view) {
        pageNumber-=1;
        if(pageNumber>1)
            previousButton.setEnabled(true);
        else{
            previousButton.setEnabled(false);
        }
        nextButton.setEnabled(true);
        updateList();
    }

    private void updateList() {
        if(networkUtil.hasActiveNetwork()){
            LoadFeedData loadFeedData = new LoadFeedData(this,this,pageNumber);
            loadFeedData.execute();
        }else{
            Toast.makeText(this, "check internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    public void showNextIssues(View view) {
        pageNumber+=1;
        if(pageNumber>1) {
            previousButton.setEnabled(true);
        }
        updateList();
    }

    public void setAdaptorWith(ArrayList<Issue> newIssues) {
        if(newIssues.size() ==0)
            nextButton.setEnabled(false);
        issues.clear();
        issues.addAll(newIssues);
        adapter.notifyDataSetChanged();
        Log.d(LOG_TAG, "adaptor has list size"+ adapter.getCount());
    }
}