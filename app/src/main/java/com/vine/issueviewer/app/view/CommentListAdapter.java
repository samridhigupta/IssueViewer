package com.vine.issueviewer.app.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.vine.issueviewer.app.R;
import com.vine.issueviewer.app.model.Comment;

import java.util.ArrayList;

/**
 * Created by samridhi on 05/03/16.
 */
public class CommentListAdapter extends BaseAdapter {

    private ArrayList<Comment> data;
    private static LayoutInflater inflater=null;

    public CommentListAdapter(Activity activity, ArrayList<Comment> data) {
        this.data = data;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return (null != data ? data.size() : 0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.comment_list_item, parent, false);
            viewHolder.body = (TextView) convertView.findViewById(R.id.body);
            viewHolder.username = (TextView) convertView.findViewById(R.id.username);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String body = data.get(position).getBody();
        viewHolder.body.setText(body);

        String username = data.get(position).getUserName();

        viewHolder.username.setText(username);

        return convertView;

    }

    private class ViewHolder {
        TextView body;
        TextView username;
    }
}
