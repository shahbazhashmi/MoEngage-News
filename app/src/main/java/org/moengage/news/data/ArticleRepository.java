package org.moengage.news.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.moengage.news.AppController;
import org.moengage.news.Config;
import org.moengage.news.R;
import org.moengage.news.data.db.ArticleContract;
import org.moengage.news.interfaces.FetchListDataListener;
import org.moengage.news.models.Article;
import org.moengage.news.models.Source;
import org.moengage.news.utils.AppUtils;
import org.moengage.news.utils.HttpUtility;
import org.moengage.news.utils.executors.DefaultExecutorSupplier;

import java.util.ArrayList;
import java.util.List;

import static org.moengage.news.data.db.ArticleContract.ArticleEntry.TABLE_NAME;
import static org.moengage.news.data.db.ArticleContract.projection;

/**
 * Created by Shahbaz Hashmi on 2020-03-20.
 */
public class ArticleRepository {

    private static final String TAG = "ArticleRepository";

    SQLiteDatabase db;

    FetchListDataListener fetchListDataListener;

    private List<Article> mArticleList;
    private Article mArticle;


    public void getArticles() {

        boolean isLocalDataAvailable = isLocalDataAvailable();

        if (isLocalDataAvailable) {
            getArticlesFromDb();
            fetchListDataListener.onSuccess(mArticleList);
        }

        if (!AppUtils.isNetworkAvailable(AppController.getInstance())) {
            if (isLocalDataAvailable) {
                fetchListDataListener.onErrorPrompt(AppController.getResourses().getString(R.string.error_internet));
            } else {
                fetchListDataListener.onError(AppController.getResourses().getString(R.string.error_internet), true);
            }
            return;
        }

        fetchAndSaveArticles();
    }


    public void fetchAndSaveArticles() {

        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {

            try {

                String requestURL = Config.NEWS_FEED_URL;
                Log.d(TAG, "fetchAndGetListData -> API calling - " + requestURL);
                HttpUtility.sendGetRequest(requestURL);
                JSONObject response = HttpUtility.getJsonResponse();

                Log.d(TAG, "fetchAndGetListData -> API response - " + response.toString());

                mArticleList = new ArrayList<>();
                JSONArray articles = response.getJSONArray("articles");

                db = AppController.getArticleDbHelper().getWritableDatabase();

                for (int i = 0; i < articles.length(); i++) {

                    JSONObject c = articles.getJSONObject(i);
                    String author = c.optString("author");
                    String title = c.optString("title");
                    String description = c.optString("description");
                    String url = c.optString("url");
                    String urlToImage = c.optString("urlToImage");
                    String publishedAt = c.optString("publishedAt");
                    String content = c.optString("content");
                    JSONObject sourceJson = c.getJSONObject("source");
                    String sourceId = sourceJson.optString("id");
                    String sourceName = sourceJson.optString("name");

                    Source source = new Source(sourceId, sourceName);
                    Article article = new Article(author, title, description, url, urlToImage, publishedAt, content, source);

                    mArticleList.add(article);

                    // Create a new map of values, where column names are the keys
                    ContentValues values = new ContentValues();
                    values.put(ArticleContract.ArticleEntry.COLUMN_NAME_AUTHOR, author);
                    values.put(ArticleContract.ArticleEntry.COLUMN_NAME_TITLE, title);
                    values.put(ArticleContract.ArticleEntry.COLUMN_NAME_DESCRIPTION, description);
                    values.put(ArticleContract.ArticleEntry.COLUMN_NAME_URL, url);
                    values.put(ArticleContract.ArticleEntry.COLUMN_NAME_URL_TO_IMAGE, urlToImage);
                    values.put(ArticleContract.ArticleEntry.COLUMN_NAME_PUBLISHED_AT, publishedAt);
                    values.put(ArticleContract.ArticleEntry.COLUMN_NAME_CONTENT, content);
                    values.put(ArticleContract.ArticleEntry.COLUMN_NAME_SOURCE_ID, sourceId);
                    values.put(ArticleContract.ArticleEntry.COLUMN_NAME_SOURCE_NAME, sourceName);

                    //  Insert the new row, returning the primary key value of the new row
                    long newRowId = db.insert(TABLE_NAME, null, values);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            DefaultExecutorSupplier.getInstance().forMainThreadTasks().execute(() -> {
                fetchListDataListener.onUpdatedData(mArticleList);
            });

        });
    }


    public boolean isLocalDataAvailable() {
        db = AppController.getArticleDbHelper().getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return count > 0;
    }

    public void getArticlesFromDb() {

        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {

            db = AppController.getArticleDbHelper().getReadableDatabase();

            Cursor c = db.query(
                    ArticleContract.ArticleEntry.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );

            mArticleList = new ArrayList<>();
            while (c.moveToNext()) {

                String author = c.getString(c.getColumnIndexOrThrow(ArticleContract.ArticleEntry.COLUMN_NAME_AUTHOR));
                String title = c.getString(c.getColumnIndexOrThrow(ArticleContract.ArticleEntry.COLUMN_NAME_TITLE));
                String description = c.getString(c.getColumnIndexOrThrow(ArticleContract.ArticleEntry.COLUMN_NAME_DESCRIPTION));
                String url = c.getString(c.getColumnIndexOrThrow(ArticleContract.ArticleEntry.COLUMN_NAME_URL));
                String urlToImage = c.getString(c.getColumnIndexOrThrow(ArticleContract.ArticleEntry.COLUMN_NAME_URL_TO_IMAGE));
                String publishedAt = c.getString(c.getColumnIndexOrThrow(ArticleContract.ArticleEntry.COLUMN_NAME_PUBLISHED_AT));
                String content = c.getString(c.getColumnIndexOrThrow(ArticleContract.ArticleEntry.COLUMN_NAME_CONTENT));
                String sourceId = c.getString(c.getColumnIndexOrThrow(ArticleContract.ArticleEntry.COLUMN_NAME_SOURCE_ID));
                String sourceName = c.getString(c.getColumnIndexOrThrow(ArticleContract.ArticleEntry.COLUMN_NAME_SOURCE_NAME));

                Source source = new Source(sourceId, sourceName);
                Article article = new Article(author, title, description, url, urlToImage, publishedAt, content, source);

                mArticleList.add(article);
            }
            c.close();

        });
    }

}
