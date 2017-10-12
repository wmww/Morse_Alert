package net.widap.morsealert;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

class MessageTransform {
    private static String[] packageWhitelist = new String[] {
            "com.facebook.orca", // facebook messenger
            "net.dinglisch.android.taskerm", // tasker
    };

    static String transformMessage(String body, String sourcePackage, Context context) {
        System.out.println("transforming " + body + " from " + sourcePackage);

        SharedPreferences prefs = context.getSharedPreferences("morse_alert_main", MODE_PRIVATE);
        if (!prefs.getBoolean("global_toggle", true)) {
            return null;
        }

        Boolean packageGood = false;
        for (String i : packageWhitelist) {
            if (sourcePackage.equals(i)) {
                packageGood = true;
                break;
            }
        }
        if (!packageGood) {
            return null;
        }

        return body;
    }
}
