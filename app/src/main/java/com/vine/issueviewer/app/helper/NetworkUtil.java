package com.vine.issueviewer.app.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by samridhi on 05/03/16.
 */
public class NetworkUtil {

    private Context context;

    public NetworkUtil(Context context){
        this.context = context;
    }
    public boolean hasActiveNetwork() {
        ConnectivityManager connManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
        return (activeNetwork != null) && activeNetwork.isConnected();
    }
}
