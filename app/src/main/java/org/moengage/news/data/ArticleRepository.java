package org.moengage.news.data;

import android.util.Log;

import org.json.JSONObject;
import org.moengage.news.Config;
import org.moengage.news.models.Article;
import org.moengage.news.models.ArticleJsonParser;
import org.moengage.news.utils.HttpUtility;
import org.moengage.news.utils.executors.DefaultExecutorSupplier;

import java.util.List;

/**
 * Created by Shahbaz Hashmi on 2020-03-20.
 */
public class ArticleRepository {

    private static final String TAG = "ArticleRepository";

    private List<Article> mArticleList;
    private Article mArticle;


    public void fetchAndGetArticles() {
        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {

            try {

                String requestURL = Config.NEWS_FEED_URL;
                Log.d(TAG, "fetchAndGetListData -> API calling - " + requestURL);
                HttpUtility.sendGetRequest(requestURL);
                JSONObject response = HttpUtility.getJsonResponse();

                Log.d(TAG, "fetchAndGetListData -> API response - " + response.toString());

                List<Article> articles = ArticleJsonParser.getArticleList(response);

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

}
