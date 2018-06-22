package com.example.savio.testeandroid.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.savio.testeandroid.R;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder>{

    List<Object> moreInfos;
    private LayoutInflater layoutInflater;
    Context context;

    public InfoAdapter(Context context, List<Object> moreInfos){

        this.context = context;
        this.moreInfos = moreInfos;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.recycler_info, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        List<Object> current = (List<Object>) moreInfos.get(position);

        holder.name.setText(current.get(0).toString());

        if(current.get(1).toString().equals("null")){

            holder.data.setText("Baixar");
            holder.data.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }
        else{

            holder.data.setText(current.get(1).toString());
        }

    }

    @Override
    public int getItemCount() {

        return moreInfos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, data;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.info_name);
            data = itemView.findViewById(R.id.info_data);
        }
    }

}
