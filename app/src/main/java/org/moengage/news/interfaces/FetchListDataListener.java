package org.moengage.news.interfaces;

import org.moengage.news.models.Article;

import java.util.List;

/**
 * Created by Shahbaz Hashmi on 2020-03-20.
 */
public interface FetchListDataListener {

    void onSuccess(List<Article> articleList);

    void onUpdatedData(List<Article> articleList);

    void onError(String errMsg, boolean canRetry);

    void onErrorPrompt(String errMsg);

}
