package com.meteoro.testeandroid.ui.investiment.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.meteoro.testeandroid.core.adapter.AdapterDelegateManager;
import com.meteoro.testeandroid.ui.investiment.domain.model.ScreenViewModel;

import javax.inject.Inject;

public class InvestimentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ScreenViewModel data;

    private AdapterDelegateManager<ScreenViewModel> delegateManager;

    private HeaderInfoAdapterDelegate headerInfoAdapterDelegate;
    private InfoAdapterDelegate infoAdapterDelegate;
    private DownInfoAdapterDelegate downInfoAdapterDelegate;

    @Inject
    public InvestimentAdapter() {
        delegateManager = new AdapterDelegateManager<>();

        headerInfoAdapterDelegate = new HeaderInfoAdapterDelegate();
        infoAdapterDelegate = new InfoAdapterDelegate();
        downInfoAdapterDelegate = new DownInfoAdapterDelegate();

        delegateManager.addDelegate(headerInfoAdapterDelegate);
        delegateManager.addDelegate(infoAdapterDelegate);
        delegateManager.addDelegate(downInfoAdapterDelegate);
    }

    public void setData(ScreenViewModel data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return delegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        delegateManager.onBindViewHolder(data, position, holder);
    }

    @Override
    public int getItemCount() {
        return data.modelTypeList().size();
    }

    @Override
    public int getItemViewType(int position) {
        return delegateManager.getItemViewType(data, position);
    }
}
