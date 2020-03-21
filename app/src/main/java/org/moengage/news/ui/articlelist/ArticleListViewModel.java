package org.moengage.news.ui.articlelist;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import org.moengage.news.data.ArticleRepository;
import org.moengage.news.interfaces.FetchListDataListener;
import org.moengage.news.models.Article;

import java.util.List;

/**
 * Created by Shahbaz Hashmi on 2020-03-21.
 */
public class ArticleListViewModel extends AndroidViewModel implements FetchListDataListener {

    private static final String TAG = "ArticleListViewModel";

    ArticleRepository articleRepository;

    public ArticleAdapter articleAdapter;

    public ArticleListViewModel(@NonNull Application application) {
        super(application);
        articleAdapter = new ArticleAdapter();
        articleRepository = new ArticleRepository();
        articleRepository.setFetchListDataListener(this);
        articleRepository.getArticles(false);
    }

    @Override
    public void onLoading() {
        Log.d(TAG, "FetchListDataListener : onLoading");
    }

    @Override
    public void onSuccess(List<Article> articleList) {
        Log.d(TAG, "FetchListDataListener : onSuccess");
        articleAdapter.setData(articleList);
    }

    @Override
    public void onUpdatedData(List<Article> articleList) {
        Log.d(TAG, "FetchListDataListener : onUpdatedData");
        articleAdapter.setData(articleList);
    }

    @Override
    public void onError(String errMsg, boolean canRetry) {
        Log.d(TAG, "FetchListDataListener : onError");
    }

    @Override
    public void onErrorPrompt(String errMsg) {
        Log.d(TAG, "FetchListDataListener : onErrorPrompt");
    }
}
