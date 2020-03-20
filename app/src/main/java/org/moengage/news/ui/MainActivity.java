package org.moengage.news.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.moengage.news.R;
import org.moengage.news.data.ArticleRepository;

public class MainActivity extends AppCompatActivity {

    ArticleRepository articleRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articleRepository = new ArticleRepository();

        articleRepository.fetchAndSaveArticles();
    }
}
