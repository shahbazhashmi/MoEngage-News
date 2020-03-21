package org.moengage.news.utils;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Shahbaz Hashmi on 2020-03-20.
 */
public class BindingUtils {

    private static final String VISIBILITY = "android:visibility";
    private static final String RECYCLERVIEW_ADAPTER = "recyclerViewAdapter";

    @BindingAdapter({VISIBILITY})
    public static void setVisibility(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }


    @BindingAdapter({RECYCLERVIEW_ADAPTER})
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }


}
