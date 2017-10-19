package com.vine.issueviewer.app.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.vine.issueviewer.app.R;
import com.vine.issueviewer.app.intent.LoadCommentData;
import com.vine.issueviewer.app.helper.NetworkUtil;
import com.vine.issueviewer.app.model.Comment;
import com.vine.issueviewer.app.model.Issue;

import java.util.ArrayList;

/**
 * Created by samridhi on 05/03/16.
 */
public class OnIssueClickListener {
    private ArrayList<Comment> comments;
    private ListView lv;
    private CommentListAdapter commentListAdapter;
    private Context context;
    private MainActivity activity;

    public OnIssueClickListener(final Context context, final MainActivity activity) {
        this.context = context;
        this.activity = activity;
    }

    public AdapterView.OnItemClickListener getListener(final LayoutInflater layoutInflater) {
        final OnIssueClickListener thisObject = this;
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Issue issue = (Issue) parent.getAdapter().getItem(position);

                if(new NetworkUtil(context).hasActiveNetwork()) {
                    LoadCommentData loadCommentData = new LoadCommentData(context, thisObject, issue);
                    loadCommentData.execute();
                    comments = new ArrayList<Comment>();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                    LayoutInflater inflater = layoutInflater;
                    View convertView = (View) inflater.inflate(R.layout.comment_list, null);
                    alertDialog.setView(convertView);
                    alertDialog.setTitle(issue.getTitle());
                    alertDialog.setCancelable(true);
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    lv = (ListView) convertView.findViewById(R.id.listView1);
                    commentListAdapter = new CommentListAdapter(activity, comments);
                    lv.setAdapter(commentListAdapter);
                    alertDialog.show();
                }else{
                    Toast.makeText(context, "check internet Connection", Toast.LENGTH_SHORT).show();

                }

            }
        };
    }

    public void setCommentAdaptorWith(ArrayList<Comment> newComments) {
        comments.clear();
        comments.addAll(newComments);
        commentListAdapter.notifyDataSetChanged();
    }
}
