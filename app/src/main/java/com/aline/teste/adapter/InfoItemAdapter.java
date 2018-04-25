package com.aline.teste.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aline.teste.Models.InfoItem;
import com.aline.teste.R;

import java.util.List;

/**
 * Created by t100 on 25/04/2018.
 */

public class InfoItemAdapter extends RecyclerView.Adapter<InfoItemAdapter.InfoHolder>{


    private List<InfoItem> listaInfo;
    private Context context;

    public InfoItemAdapter(List<InfoItem> listaInfo, Context context) {
        this.listaInfo = listaInfo;
        this.context = context;
    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.item_info_list ,
               parent, false);
       return new InfoHolder(view);
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, int position) {

        InfoItem  infoItem = listaInfo.get(position);
        holder.infoName.setText(infoItem.getName());
        holder.infoData.setText(infoItem.getData());
    }

    @Override
    public int getItemCount() {
        return listaInfo != null ? listaInfo.size() : 0;
    }

    public class InfoHolder extends RecyclerView.ViewHolder {

        private TextView infoName;
        private TextView infoData;
        public InfoHolder(View itemView) {
            super(itemView);
            infoName = itemView.findViewById(R.id.info_name);
            infoData = itemView.findViewById(R.id.info_data);
        }
    }
}
