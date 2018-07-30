package com.carpinelli.testeandroid.ui.invest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carpinelli.testeandroid.R;
import com.carpinelli.testeandroid.model.invest.Info;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private Context context;
    private List<Info> infoList;
    private Info info;

    public InfoAdapter(Context context, List<Info> infoList) {
        this.context = context;
        this.infoList = infoList;
    }


    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_info, parent, false);
        InfoViewHolder holder = new InfoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        info = infoList.get(position);

        holder.tvName.setText(info.getName());
        holder.tvData.setText(info.getData());

    }

    @Override
    public int getItemCount() {
        return this.infoList != null ? infoList.size() : 0;
    }


    public static class InfoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvData)
        TextView tvData;

        public InfoViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }


}
