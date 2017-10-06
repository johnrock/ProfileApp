package com.okapp.helpers;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author John Piser developer@earthblood.com.com
 */

public class NetworkHelper {

    public boolean networkAvailable(ConnectivityManager connectivityManager) {
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
