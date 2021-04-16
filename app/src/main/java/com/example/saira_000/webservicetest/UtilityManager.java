package com.example.saira_000.webservicetest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class UtilityManager {

    public static boolean isConnectingToInternet(Context d_context) {
        boolean isConnected = false;
        try {
            ConnectivityManager d_connectivityMgr = (ConnectivityManager) d_context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkinfo = d_connectivityMgr.getActiveNetworkInfo();
            if (networkinfo != null && networkinfo.isConnected()) {
                isConnected = true;
                Log.i("IsConnected", "" + isConnected);
            }
        } catch (Exception ex) {
            Log.i("Er: Check_Connectivity", ex.getMessage());
        }
        return isConnected;
    }
}
