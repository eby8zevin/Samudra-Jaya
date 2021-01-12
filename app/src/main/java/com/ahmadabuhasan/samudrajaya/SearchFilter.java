package com.ahmadabuhasan.samudrajaya;

import android.widget.Filter;

import java.util.ArrayList;

public class SearchFilter extends Filter {

    ArrayList<ModelSj> filterList;
    RecyclerViewAdapter recyclerViewAdapter;

    public SearchFilter(ArrayList<ModelSj> filterList, RecyclerViewAdapter recyclerViewAdapter) {
        this.filterList = filterList;
        this.recyclerViewAdapter = recyclerViewAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();
        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();
            ArrayList<ModelSj> filteredData = new ArrayList<>();
            for (int i = 0; i < filterList.size(); i++) {
                if (filterList.get(i).getNamaBarang().toUpperCase().contains(constraint) ||
                        filterList.get(i).getKodeBarang().toUpperCase().contains(constraint)) {
                    filteredData.add(filterList.get(i));
                }
            }
            filterResults.count = filteredData.size();
            filterResults.values = filteredData;
        } else {
            filterResults.count = filterList.size();
            filterResults.values = filterList;
        }
        return filterResults;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        recyclerViewAdapter.arrayModelSj = (ArrayList<ModelSj>) results.values;
        recyclerViewAdapter.notifyDataSetChanged();
    }
}