package com.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cerqueira.mellina.testeandroidsantander.R;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ItemHolder>  {

    private List<Info> Infos;
    private Context context;
    private static final boolean VISIVEL = true;
    private static final boolean INVISIVEL = false;

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
        holder.txtData.setText(info.getData());
        if(info.getData().equals("null")){


            alteraVisibilidadeImageData(holder, VISIVEL);
            alteraVisibilidadeTextData(holder, INVISIVEL);

        }
        else {

            alteraVisibilidadeImageData(holder, INVISIVEL);
            alteraVisibilidadeTextData(holder, VISIVEL);

        }

    }

    private void alteraVisibilidadeTextData(ItemHolder holder, boolean visibilidade) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)  holder.txtData.getLayoutParams();
        if(visibilidade){
            params.weight = 1.0f;
        }else{
            params.weight = 0.0f;
            holder.txtData.setText("");
        }

        holder.txtData.setLayoutParams(params);
    }

    private void alteraVisibilidadeImageData(ItemHolder holder, boolean visibilidade) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                holder.imageData.getLayoutParams();
        if(visibilidade){
            params.weight = 1.0f;
            holder.imageData.setVisibility(View.VISIBLE);
        }else{
            params.weight = 0.0f;
            holder.imageData.setVisibility(View.INVISIBLE);
        }

        holder.imageData.setLayoutParams(params);
    }


    @Override
    public int getItemCount() {
        return Infos.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtData;
        public ImageButton imageData;



        public ItemHolder(View view) {
            super(view);
            txtName = view.findViewById(R.id.txtName);
            txtData = view.findViewById(R.id.txtData);
            imageData = view.findViewById(R.id.imageData);

        }

    }
}
