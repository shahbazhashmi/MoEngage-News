package org.moengage.news.ui.articlelist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.moengage.news.R;
import org.moengage.news.models.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shahbaz Hashmi on 2020-03-21.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {

    List<Article> articleArrayList;

    ArticleAdapter() {
        articleArrayList = new ArrayList<>();
    }

    class ArticleHolder extends RecyclerView.ViewHolder {

        public ArticleHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(int position) {

        }
    }

    void setData(List<Article> articleArrayList) {
        if (articleArrayList != null && !articleArrayList.isEmpty()) {
            if (!this.articleArrayList.equals(articleArrayList)) {
                this.articleArrayList = articleArrayList;
                notifyDataSetChanged();
            }
        }
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_article, parent, false);

        return new ArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }
}
