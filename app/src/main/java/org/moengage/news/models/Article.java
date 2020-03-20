package org.moengage.news.models;

/**
 * Created by Shahbaz Hashmi on 2020-03-20.
 */
public class Article {

    String author;
    String title;
    String description;
    String url;
    String urlToImage;
    String publishedAt;
    String content;
    Source source;

    public Article(String author, String title, String description, String url, String urlToImage, String publishedAt, String content, Source source) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.source = source;
    }
}
