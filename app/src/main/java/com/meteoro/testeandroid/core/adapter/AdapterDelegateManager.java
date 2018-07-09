package com.meteoro.testeandroid.core.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

public class AdapterDelegateManager<T> {

    private int viewTypeCount = 0;
    private SparseArray<AdapterDelegate<T>> delegates = new SparseArray<>();

    public void addDelegate(@NonNull AdapterDelegate<T> delegate) {
        delegates.append(viewTypeCount, delegate);
        viewTypeCount++;
    }

    public void removeAllDelegates() {
        delegates.clear();
    }

    public int getItemViewType(@NonNull T data, int position) {
        return getDelegateViewType(data, position);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getDelegateWithType(viewType).onCreateViewHolder(parent);
    }

    public void onBindViewHolder(T data, int position, RecyclerView.ViewHolder holder) {
        getDelegateForDataType(data, position).onBindViewHolder(data, position, holder);
    }

    public AdapterDelegate<T> getAdapterDelegate(int position) {
        return delegates.get(delegates.keyAt(position));
    }

    public int size() {
        return delegates.size();
    }

    private int getDelegateViewType(T data, int position) {
        for (int i = 0; i < delegates.size(); i++) {
            int key = delegates.keyAt(i);
            AdapterDelegate<T> delegate = delegates.get(key);
            if (delegate.isViewForData(data, position)) {
                return key;
            }
        }

        throw new IllegalArgumentException("Missing adapter for data at position " + position);
    }

    private AdapterDelegate<T> getDelegateForDataType(T data, int position) {
        for (int i = 0; i < delegates.size(); i++) {
            AdapterDelegate<T> delegate = delegates.get(delegates.keyAt(i));
            if (delegate.isViewForData(data, position)) {
                return delegate;
            }
        }

        throw new IllegalArgumentException("Missing adapter for data type " +
                data.getClass().getSimpleName());
    }

    private AdapterDelegate<T> getDelegateWithType(int viewType) {
        return delegates.get(viewType);
    }
}
