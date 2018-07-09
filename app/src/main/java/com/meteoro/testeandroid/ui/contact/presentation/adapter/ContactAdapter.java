package com.meteoro.testeandroid.ui.contact.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.meteoro.testeandroid.core.adapter.AdapterDelegateManager;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;

import javax.inject.Inject;

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private CellsViewModel data;

    private AdapterDelegateManager<CellsViewModel> delegateManager;

    private FieldAdapterDelegate fieldAdapterDelegate;
    private TextAdapterDelegate textAdapterDelegate;
    private CheckboxAdapterDelegate checkboxAdapterDelegate;
    private SendAdapterDelegate sendAdapterDelegate;

    @Inject
    public ContactAdapter() {
        delegateManager = new AdapterDelegateManager<>();

        fieldAdapterDelegate = new FieldAdapterDelegate();
        textAdapterDelegate = new TextAdapterDelegate();
        checkboxAdapterDelegate = new CheckboxAdapterDelegate();
        sendAdapterDelegate = new SendAdapterDelegate();

        delegateManager.addDelegate(fieldAdapterDelegate);
        delegateManager.addDelegate(textAdapterDelegate);
        delegateManager.addDelegate(checkboxAdapterDelegate);
        delegateManager.addDelegate(sendAdapterDelegate);
    }

    public void setData(CellsViewModel data) {
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
        return data.cellsTypeList().size();
    }

    @Override
    public int getItemViewType(int position) {
        return delegateManager.getItemViewType(data, position);
    }
}
