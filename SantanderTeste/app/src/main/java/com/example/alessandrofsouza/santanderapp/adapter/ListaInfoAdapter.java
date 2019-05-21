package com.example.alessandrofsouza.santanderapp.adapter;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.model.Infos;

import java.util.ArrayList;

class ListaInfoAdapter extends RecyclerView.Adapter<ListaInfoAdapter.ViewHolder> {

    private ArrayList<Infos> dataSet;
    private static final String TAG = "Santander ";

    public ListaInfoAdapter() {
        dataSet = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewText = LayoutInflater.from(parent.getContext()).inflate(R.layout.invest_info, parent, false);

        return new ViewHolder(viewText);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Infos info = dataSet.get(position);
        Resources res = holder.itemView.getContext().getResources();


        holder.info_title.setText(info.getName());

        if(info.getData() != null) {
            holder.buttonDown.setAlpha(0);
            holder.buttonDown.setClickable(false);
            holder.info_content.setText(info.getData());
        } else {
            holder.info_content.setAlpha(0);
            holder.buttonDown.setText(res.getText(R.string.baixar));
        }
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void addListInfo(ArrayList<Infos> listInfo) {
        dataSet.addAll(listInfo);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView info_title;
        private TextView info_content;
        private Button buttonDown;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            info_title = itemView.findViewById(R.id.info_title);
            info_content = itemView.findViewById(R.id.info_content);
            buttonDown = itemView.findViewById(R.id.buttonDown);
        }
    }


}
