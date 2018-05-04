package com.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cerqueira.mellina.testeandroidsantander.R;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ItemHolder>  {

    private List<Info> Infos;
    private Context context;

    public InfoAdapter(Context context, List<Info> Infos) {
        this.context = context;
            this.Infos = Infos;

    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_info, parent, false);
        ItemHolder holder = new ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Info info = Infos.get(position);

        holder.txtName.setText(String.valueOf(info.getName()));
        holder.txtData.setText(info .getData());


    }

    @Override
    public int getItemCount() {
        return Infos.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtData;



        public ItemHolder(View view) {
            super(view);
            txtName = view.findViewById(R.id.txtName);
            txtData = view.findViewById(R.id.txtData);


        }

    }
}
