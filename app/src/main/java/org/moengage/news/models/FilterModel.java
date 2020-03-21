package org.moengage.news.models;

/**
 * Created by Shahbaz Hashmi on 2020-03-21.
 */
public class FilterModel {

    enum Sort {
        ASC, DESC
    }

    private Sort sortByDate = Sort.ASC;
    private String filterByAuthor = "";

    public boolean isSortByDateAsc() {
        return sortByDate == Sort.ASC;
    }

    public void setSortByDateAsc(boolean isAsc) {
        this.sortByDate = isAsc ? Sort.ASC : Sort.DESC;
    }

    public String getFilterByAuthor() {
        return filterByAuthor;
    }

    public void setFilterByAuthor(String filterByAuthor) {
        this.filterByAuthor = filterByAuthor;
    }
}
