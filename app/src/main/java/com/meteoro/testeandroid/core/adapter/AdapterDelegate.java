package com.meteoro.testeandroid.core.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface AdapterDelegate<T> {
    boolean isViewForData(T data, int position);

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(T data, int position, RecyclerView.ViewHolder holder);
}
