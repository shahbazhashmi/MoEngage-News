package org.moengage.news.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shahbaz Hashmi on 2020-03-20.
 */
public class ArticleJsonParser {

    public static List<Article> getArticleList(JSONObject response) {
        ArrayList<Article> articleArrayList = new ArrayList<Article>();

        try {
            JSONArray articles = response.getJSONArray("articles");

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

                articleArrayList.add(article);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return articleArrayList;
    }

}
