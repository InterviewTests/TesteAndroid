package com.carpinelli.testeandroid.ui.invest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carpinelli.testeandroid.R;
import com.carpinelli.testeandroid.model.DownInfo;
import com.carpinelli.testeandroid.model.Info;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownInfoAdapter extends RecyclerView.Adapter<DownInfoAdapter.InfoViewHolder> {

    private Context context;
    private List<DownInfo> infoList;
    private DownInfo info;

    public DownInfoAdapter(Context context, List<DownInfo> infoList) {
        this.context = context;
        this.infoList = infoList;
    }


    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_down_info, parent, false);
        InfoViewHolder holder = new InfoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        info = infoList.get(position);

        holder.tvName.setText(info.getName());

    }

    @Override
    public int getItemCount() {
        return this.infoList != null ? infoList.size() : 0;
    }


    public static class InfoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;

        public InfoViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }


}
