package br.com.santander.testeandroid.investment.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.santander.testeandroid.R;
import br.com.santander.testeandroid.investment.model.BaseInfo;
import br.com.santander.testeandroid.investment.model.InfoViewHolder;
import br.com.santander.testeandroid.utils.Utils;

public class InfoAdapter extends RecyclerView.Adapter<InfoViewHolder> {
    private List<BaseInfo> infoList;

    public InfoAdapter(List<BaseInfo> infoList) {
        setInfoList(infoList);
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_simple_info, viewGroup, false);

        return new InfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        InfoViewHolder.bindData(holder, infoList.get(position));
    }

    @Override
    public int getItemCount() {
        return getInfoList().size();
    }

    public List<BaseInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<BaseInfo> infoList) {
        this.infoList = infoList;
    }
}
