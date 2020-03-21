package org.moengage.news.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by Shahbaz Hashmi on 2020-03-20.
 */
public class AppUtils {

    public static boolean isNetworkAvailable() {
        try {
            final String command = "ping -c 1 google.com";
            return Runtime.getRuntime().exec(command).waitFor() == 0;
        } catch (Exception e) {
            return false;
        }
    }


    public static void showToast(final Context context, final String message, final boolean longDuration) {
        try {
            if (context instanceof Activity && ((Activity) context).isFinishing()) {
                return;
            }

            if (TextUtils.isEmpty(message) || context == null)
                return;

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> Toast.makeText(context.getApplicationContext(), message, longDuration ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show());
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
