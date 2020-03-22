package org.moengage.news.ui.articlelist.filter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.moengage.news.R;
import org.moengage.news.interfaces.FilterItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shahbaz Hashmi on 2020-03-22.
 */
public class ArticleFilterBottomSheetFragment extends BottomSheetDialogFragment implements FilterItemClickListener {

    public static final String TAG = "ArticleFilterBottomSheetFragment";

    private static final String FILTER_LIST = "filter_list";

    FilterItemClickListener filterItemClickListener;

    FilterAdapter filterAdapter;
    RecyclerView recyclerView;

    public static ArticleFilterBottomSheetFragment newInstance(List<String> filterList, FilterItemClickListener filterItemClickListener) {
        ArticleFilterBottomSheetFragment fragment = new ArticleFilterBottomSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(FILTER_LIST, new ArrayList<>(filterList));
        fragment.filterItemClickListener = filterItemClickListener;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_filter_bottomsheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview_filter);
        ArrayList<String> filterList = getArguments().getStringArrayList(FILTER_LIST);
        filterList.add(0, getString(R.string.txt_all_publishers));
        filterAdapter = new FilterAdapter(filterList, this);
        recyclerView.setAdapter(filterAdapter);

    }


    @Override
    public void onFilterClick(int position, String name) {
        dismiss();
        filterItemClickListener.onFilterClick(position, name);
    }
}
