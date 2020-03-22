package org.moengage.news.ui.articlelist.filter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.moengage.news.R;
import org.moengage.news.interfaces.FilterItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shahbaz Hashmi on 2020-03-22.
 */
public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterItemHolder> {

    List<String> filterItemList;

    FilterItemClickListener filterItemClickListener;

    FilterAdapter(ArrayList<String> filterItemList, FilterItemClickListener filterItemClickListener) {
        this.filterItemList = filterItemList;
        this.filterItemClickListener = filterItemClickListener;
    }

    class FilterItemHolder extends RecyclerView.ViewHolder {

        TextView publisherTv = itemView.findViewById(R.id.textview_publisher);

        public FilterItemHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(int position) {
            String name = filterItemList.get(position);
            publisherTv.setText(name);

            itemView.setOnClickListener(view -> filterItemClickListener.onFilterClick(position, name));
        }
    }


    @NonNull
    @Override
    public FilterItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_filter, parent, false);

        return new FilterItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterItemHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return filterItemList.size();
    }
}
