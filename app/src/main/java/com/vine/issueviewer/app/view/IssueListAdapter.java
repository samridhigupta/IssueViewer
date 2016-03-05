package com.vine.issueviewer.app.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.vine.issueviewer.app.R;
import com.vine.issueviewer.app.model.Issue;

import java.util.ArrayList;

/**
 * Created by samridhi on 05/03/16.
 */
public class IssueListAdapter extends BaseAdapter {

    private static final int noOfChars = 140;
    private ArrayList<Issue> data;
    private static LayoutInflater inflater=null;

    public IssueListAdapter(Activity activity, ArrayList<Issue> data) {
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
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.body = (TextView) convertView.findViewById(R.id.body);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String title = data.get(position).getTitle();
        viewHolder.title.setText(title);

        String body = data.get(position).getBody();
        if(null!=body && body.length()>noOfChars)
            body =  body.substring(0, noOfChars);

        viewHolder.body.setText(body);
        return convertView;

    }

    private class ViewHolder {
        TextView title;
        TextView body;
    }
}
