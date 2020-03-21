package org.moengage.news.ui.loader;

import org.moengage.news.interfaces.LoaderListener;

/**
 * Created by Shahbaz Hashmi on 2020-03-21.
 */
public class LoaderHelper {

    public enum States {
        LOADING, ERROR, TRY_AGAIN, SHOWING_DATA
    }

    public LoaderViewModel loaderViewModel;

    public LoaderHelper(LoaderListener loaderListener) {
        loaderViewModel = new LoaderViewModel(loaderListener);
    }


    public void dismiss() {
        loaderViewModel.setLoaderState(States.SHOWING_DATA);
        loaderViewModel.dismiss();
    }

    public void showLoading() {
        loaderViewModel.setLoaderState(States.LOADING);
        loaderViewModel.show();
    }

    public void showError(String errorText) {
        loaderViewModel.setText(errorText);
        loaderViewModel.setLoaderState(States.ERROR);
        loaderViewModel.show();
    }

    public void showErrorWithRetry(String errorText) {
        loaderViewModel.setText(errorText);
        loaderViewModel.setLoaderState(States.TRY_AGAIN);
        loaderViewModel.show();
    }
}
