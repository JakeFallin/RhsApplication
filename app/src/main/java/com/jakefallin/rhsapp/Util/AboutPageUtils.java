package com.jakefallin.rhsapp.Util;

/**
 * Created by Jake on 6/1/2016.
 */

import android.content.Context;
import android.content.pm.PackageManager;

public class AboutPageUtils {

    public static Boolean isAppInstalled(Context context, String appName){
        PackageManager pm = context.getPackageManager();
        boolean installed;
        try {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }
}
