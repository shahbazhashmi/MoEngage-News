package org.moengage.news;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.moengage.news.data.ArticleRepository;

/**
 * Created by Shahbaz Hashmi on 2020-03-22.
 */
public class SyncDataBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "SyncDataReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "called");
        new ArticleRepository().fetchAndSaveArticles(false);
    }
}
