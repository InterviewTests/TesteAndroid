package com.example.alessandrofsouza.santanderapp.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.model.Infos;

import java.util.ArrayList;
import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {

    private ArrayList<Infos> dataSet;

    public InfoAdapter() { dataSet = new ArrayList<>(); }

    /*public InfoAdapter(Context context, List<Infos> infos) {
        super(context, 0, infos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.invest_info, parent, false);
        }

        Infos currentMoreInfo = getItem(position);

        TextView info_title = listItemView.findViewById(R.id.info_title);
        info_title.setText(currentMoreInfo.getName());

        TextView info_content = listItemView.findViewById(R.id.info_content);
        info_content.setText(currentMoreInfo.getData());

        return listItemView;
    }

    public void addListInfos(ArrayList<Infos> listInfos) {
        dataSet.addAll(listInfos);
        notifyDataSetChanged();
    }*/

    @Override
    public InfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewInfo = LayoutInflater.from(parent.getContext()).inflate(R.layout.invest_info, parent, false);

        return new ViewHolder(viewInfo);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Infos info = dataSet.get(position);
        Resources res = holder.itemView.getContext().getResources();

        holder.info_title.setText(info.getName());
        holder.info_content.setText(info.getData());
    }



    @Override
    public int getItemViewType(int position) {
        //Infos cell = dataSet.get(position);
        //int viewType = cell.getType();
        return position;
    }



    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void addListInfo(ArrayList<Infos> listInfos) {
        dataSet.addAll(listInfos);
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView info_title;
        private TextView info_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            info_title = itemView.findViewById(R.id.info_title);
            info_content = itemView.findViewById(R.id.info_content);
        }
    }


}
