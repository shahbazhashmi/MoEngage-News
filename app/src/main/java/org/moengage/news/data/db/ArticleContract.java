package org.moengage.news.data.db;

import android.provider.BaseColumns;

/**
 * Created by Shahbaz Hashmi on 2020-03-20.
 */
public final class ArticleContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ArticleContract() {
    }

    /* Inner class that defines the table contents */
    public static class ArticleEntry implements BaseColumns {
        public static final String TABLE_NAME = "article";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_URL_TO_IMAGE = "url_to_image";
        public static final String COLUMN_NAME_PUBLISHED_AT = "published_at";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_SOURCE_ID = "source_id";
        public static final String COLUMN_NAME_SOURCE_NAME = "SOURCE_NAME";
    }

    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
    public static String[] projection = {
            ArticleEntry.COLUMN_NAME_AUTHOR,
            ArticleEntry.COLUMN_NAME_TITLE,
            ArticleEntry.COLUMN_NAME_DESCRIPTION,
            ArticleEntry.COLUMN_NAME_URL,
            ArticleEntry.COLUMN_NAME_URL_TO_IMAGE,
            ArticleEntry.COLUMN_NAME_PUBLISHED_AT,
            ArticleEntry.COLUMN_NAME_CONTENT,
            ArticleEntry.COLUMN_NAME_SOURCE_ID,
            ArticleEntry.COLUMN_NAME_SOURCE_NAME
    };
}
