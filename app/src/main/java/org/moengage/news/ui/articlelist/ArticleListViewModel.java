package org.moengage.news.ui.articlelist;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

import org.moengage.news.data.ArticleRepository;
import org.moengage.news.interfaces.FetchListDataListener;
import org.moengage.news.interfaces.FilterItemClickListener;
import org.moengage.news.interfaces.ShowFilterBottomSheetListener;
import org.moengage.news.models.Article;
import org.moengage.news.models.FilterModel;
import org.moengage.news.ui.loader.LoaderHelper;

import java.util.List;

/**
 * Created by Shahbaz Hashmi on 2020-03-21.
 */
public class ArticleListViewModel extends AndroidViewModel implements FetchListDataListener, FilterItemClickListener {

    private static final String TAG = "ArticleListViewModel";

    ArticleRepository articleRepository;

    ShowFilterBottomSheetListener showFilterBottomSheetListener;

    public ArticleAdapter articleAdapter;

    public LoaderHelper loaderHelper;

    public ObservableField<Boolean> ascendingSort = new ObservableField<>();
    public ObservableField<String> selectedPublisher = new ObservableField<>();

    FilterModel filterModel;

    public ArticleListViewModel(@NonNull Application application, ShowFilterBottomSheetListener showFilterBottomSheetListener) {
        super(application);
        filterModel = new FilterModel();
        this.showFilterBottomSheetListener = showFilterBottomSheetListener;
        loaderHelper = new LoaderHelper(() -> articleRepository.getArticles(filterModel, false));
        articleAdapter = new ArticleAdapter();
        articleRepository = new ArticleRepository();
        articleRepository.setFetchListDataListener(this);
        articleRepository.getArticles(filterModel, false);
    }

    @Override
    public void onLoading() {
        loaderHelper.showLoading();
        Log.d(TAG, "FetchListDataListener : onLoading");
    }

    @Override
    public void onSuccess(List<Article> articleList) {
        Log.d(TAG, "FetchListDataListener : onSuccess");
        articleAdapter.setData(articleList);
        populateSortAndFilter();
        loaderHelper.dismiss();
    }

    @Override
    public void onError(String errMsg, boolean canRetry) {
        if (canRetry) {
            loaderHelper.showErrorWithRetry(errMsg);
        } else {
            loaderHelper.showError(errMsg);
        }
        Log.d(TAG, "FetchListDataListener : onError");
    }

    @Override
    public void onErrorPrompt(String errMsg) {
        Log.d(TAG, "FetchListDataListener : onErrorPrompt");
        loaderHelper.dismiss();
    }

    public void toggleSort() {
        ascendingSort.set(!ascendingSort.get());
        filterModel.setSortByDateAsc(ascendingSort.get());
        articleRepository.getArticles(filterModel, false);
    }

    public void onFilterClick() {
        showFilterBottomSheetListener.showFilterBottomSheet(articleRepository.getAllPublishers());
    }

    void populateSortAndFilter() {
        ascendingSort.set(filterModel.isSortByDateAsc());
        selectedPublisher.set(filterModel.getFilterByAuthor());
    }

    @Override
    public void onFilterClick(int position, String name) {
        // 0th index holds all publisher
        selectedPublisher.set(position == 0 ? "" : name);
        filterModel.setFilterByAuthor(selectedPublisher.get());
        articleRepository.getArticles(filterModel, false);
    }
}
