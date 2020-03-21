package org.moengage.news.ui.loader;

import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import org.moengage.news.interfaces.LoaderListener;

/**
 * Created by Shahbaz Hashmi on 2020-03-21.
 */
public class LoaderViewModel extends ViewModel {

    private LoaderListener mLoaderListener;

    LoaderViewModel(LoaderListener loaderListener) {
        mLoaderListener = loaderListener;
    }

    public ObservableField<LoaderHelper.States> loaderState = new ObservableField<LoaderHelper.States>();
    public ObservableField<String> text = new ObservableField<String>();
    public ObservableBoolean isVisible = new ObservableBoolean(false);

    void setLoaderState(LoaderHelper.States loaderState) {
        this.loaderState.set(loaderState);
    }

    void setText(String text) {
        this.text.set(text);
    }

    void show() {
        isVisible.set(true);
    }

    void dismiss() {
        isVisible.set(false);
    }

    public void onRetryClick(View view) {
        mLoaderListener.onRetryClick();
    }

}
