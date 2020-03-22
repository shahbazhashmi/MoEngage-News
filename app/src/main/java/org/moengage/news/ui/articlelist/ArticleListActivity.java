package org.moengage.news.ui.articlelist;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import org.moengage.news.BaseActivity;
import org.moengage.news.R;
import org.moengage.news.databinding.ActivityArticleListBinding;
import org.moengage.news.ui.articlelist.filter.ArticleFilterBottomSheetFragment;

public class ArticleListActivity extends BaseActivity {

    ActivityArticleListBinding binding;
    ArticleListViewModel viewModel;

    @Override
    protected void onNetworkChange(boolean isConnected) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_list);
        viewModel = new ArticleListViewModel(getApplication(), publishers -> {
            ArticleFilterBottomSheetFragment filterBottomDialogFragment =
                    ArticleFilterBottomSheetFragment.newInstance(publishers, viewModel);
            filterBottomDialogFragment.show(getSupportFragmentManager(),
                    ArticleFilterBottomSheetFragment.TAG);
        });
        binding.setVm(viewModel);
    }
}
