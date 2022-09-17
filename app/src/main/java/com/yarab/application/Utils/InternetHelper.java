package com.yarab.application.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import java.io.IOException;

public class InternetHelper {

    public static boolean isConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo type_wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo type_mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean mobile =  type_mobile.isConnectedOrConnecting();
        boolean wifi =  type_wifi.isConnectedOrConnecting();
        return wifi || mobile;
    }

    public static boolean isOnline() {
        System.out.println("executeCommand");
        Runtime localRuntime = Runtime.getRuntime();
        try {
            int i = localRuntime.exec("/system/bin/ping -c 1 8.8.8.8")
                    .waitFor();
            System.out.println(" mExitValue " + i);
            boolean bool = false;
            if (i == 0) {
                bool = true;
            }
            return bool;
        } catch (InterruptedException localInterruptedException) {
            localInterruptedException.printStackTrace();
            System.out.println(" Exception:" + localInterruptedException);
            return false;
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
            System.out.println(" Exception:" + localIOException);
        }
        return false;
    }



}
