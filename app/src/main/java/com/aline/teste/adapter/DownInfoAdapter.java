package com.aline.teste.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aline.teste.Models.DownInfoItem;
import com.aline.teste.R;

import java.util.List;

/**
 * Created by t100 on 25/04/2018.
 */

public class DownInfoAdapter extends RecyclerView.Adapter<DownInfoAdapter.DownViewHolder> {

    private List<DownInfoItem> listaMoreInfo;
    private Context context;

    public DownInfoAdapter(List<DownInfoItem> listaMoreInfo, Context context) {
        this.listaMoreInfo = listaMoreInfo;
        this.context = context;
    }

    @Override
    public DownViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_down_info_list,
                parent, false);
        return new DownViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DownViewHolder holder, int position) {

        DownInfoItem downInfoItem = listaMoreInfo.get(position);
        holder.downName.setText(downInfoItem.getName());
        if(downInfoItem.getData() == null){
            holder.downData.setText(R.string.holder_baixar_data);
            holder.downData.setTextColor(holder.itemView.getResources().getColor(R.color.colorDetalhes));
        }else{
            holder.downData.setText(String.valueOf(downInfoItem.getData()));
        }

    }

    @Override
    public int getItemCount() {
     return listaMoreInfo != null ? listaMoreInfo.size() : 0;
    }

    public class DownViewHolder extends RecyclerView.ViewHolder {

        private TextView downName;
        private TextView downData;

        public DownViewHolder(View itemView) {
            super(itemView);
            downName = itemView.findViewById(R.id.down_info_name);
            downData = itemView.findViewById(R.id.down_info_data);
        }
    }
}
