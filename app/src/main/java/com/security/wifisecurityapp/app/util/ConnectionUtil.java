package com.security.wifisecurityapp.app.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by zeina.oliveira on 27/08/2015.
 */

public class ConnectionUtil {

    private Context context;

    /**
     * Verify if there's Internet connection
     *
     * @return boolean <b>true</b>connected, <b>false</b>not connected,
     */
    public boolean isInternetConnected() {
        boolean connected = false;
        final ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr != null && connMgr.getActiveNetworkInfo() != null) {
            connected = connMgr.getActiveNetworkInfo() != null
                    && connMgr.getActiveNetworkInfo().isConnectedOrConnecting();
        }

        return connected;
    }
}
