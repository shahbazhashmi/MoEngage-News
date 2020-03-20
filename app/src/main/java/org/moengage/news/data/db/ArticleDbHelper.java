package org.moengage.news.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shahbaz Hashmi on 2020-03-20.
 */
public class ArticleDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FeedReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ArticleContract.ArticleEntry.TABLE_NAME + " (" +
                    ArticleContract.ArticleEntry.COLUMN_NAME_URL + " TEXT PRIMARY KEY," +
                    ArticleContract.ArticleEntry.COLUMN_NAME_AUTHOR + " TEXT," +
                    ArticleContract.ArticleEntry.COLUMN_NAME_TITLE + " TEXT," +
                    ArticleContract.ArticleEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    ArticleContract.ArticleEntry.COLUMN_NAME_URL_TO_IMAGE + " TEXT," +
                    ArticleContract.ArticleEntry.COLUMN_NAME_PUBLISHED_AT + " TEXT," +
                    ArticleContract.ArticleEntry.COLUMN_NAME_SOURCE_ID + " TEXT," +
                    ArticleContract.ArticleEntry.COLUMN_NAME_SOURCE_NAME + " TEXT," +
                    ArticleContract.ArticleEntry.COLUMN_NAME_CONTENT + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ArticleContract.ArticleEntry.TABLE_NAME;

    public ArticleDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}