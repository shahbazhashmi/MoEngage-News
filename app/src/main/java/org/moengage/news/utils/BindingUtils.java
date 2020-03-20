package org.moengage.news.utils;

import android.view.View;

import androidx.databinding.BindingAdapter;

/**
 * Created by Shahbaz Hashmi on 2020-03-20.
 */
public class BindingUtils {

    private static final String VISIBILITY = "android:visibility";

    @BindingAdapter({VISIBILITY})
    public static void setVisibility(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }


}
