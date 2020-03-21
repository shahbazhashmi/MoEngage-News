package org.moengage.news;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import org.moengage.news.data.db.ArticleDbHelper;

/**
 * Created by Shahbaz Hashmi on 2020-03-20.
 */
public class AppController extends Application {

    private static AppController mInstance;
    private static Resources mResources;
    private static ArticleDbHelper mArticleDbHelper;
    private static SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mResources = getResources();
        mArticleDbHelper = new ArticleDbHelper(this);
        mSharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public static Resources getResourses() {
        return mResources;
    }

    public static ArticleDbHelper getArticleDbHelper() {
        return mArticleDbHelper;
    }

    public static SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }
}
