package org.moengage.news.utils;

import android.content.SharedPreferences;

import org.moengage.news.AppController;
import org.moengage.news.BuildConfig;

/**
 * Created by Shahbaz Hashmi on 2020-03-21.
 */
public class SharedPreferenceManager {

    private final String KEY_CACHE_TIMOUT = "cache_timeout";

    private static SharedPreferenceManager sharedPreferenceManager;

    public static SharedPreferenceManager getInstance() {
        if (sharedPreferenceManager == null) {
            sharedPreferenceManager = new SharedPreferenceManager();
        }
        return sharedPreferenceManager;
    }

    SharedPreferences.Editor editor;

    public void setLastUpdatedTimestamp() {
        editor = AppController.getInstance().getSharedPreferences().edit();
        editor.putLong(KEY_CACHE_TIMOUT, System.currentTimeMillis());
        editor.commit();
    }

    /**
     * logic to check if cache data expired
     */
    public boolean isLocalDataExpired() {
        if (System.currentTimeMillis() - AppController.getInstance().getSharedPreferences().getLong(
                KEY_CACHE_TIMOUT,
                0
        ) > BuildConfig.CACHE_TIMEOUT
        ) {
            return true;
        }
        return false;
    }

}
