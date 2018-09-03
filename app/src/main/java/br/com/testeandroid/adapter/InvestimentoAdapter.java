package br.com.testeandroid.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.testeandroid.R;
import br.com.testeandroid.model.DownInfo;
import br.com.testeandroid.model.Info;

public class InvestimentoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final int VIEW_TYPE_INFO = 0;
    final int VIEW_TYPE_DOWNINFO = 1;


    private ArrayList<Info> infos;
    private ArrayList<DownInfo> downInfos;

    public InvestimentoAdapter(ArrayList<Info> infos , ArrayList<DownInfo> downInfos) {
        this.infos = infos;
        this.downInfos = downInfos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(viewType == VIEW_TYPE_INFO){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.itens_info_inventimento, parent, false);
            return new InvestimentoAdapter.InfoViewHolder(view);
        }

        if(viewType == VIEW_TYPE_DOWNINFO){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.itens_info_inventimento, parent, false);
            return new InvestimentoAdapter.DownInfoViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

        if(viewHolder instanceof InvestimentoAdapter.InfoViewHolder){
            ((InvestimentoAdapter.InfoViewHolder) viewHolder).populate(infos.get(position));
        }

        if(viewHolder instanceof InvestimentoAdapter.DownInfoViewHolder){
            ((InvestimentoAdapter.DownInfoViewHolder) viewHolder).populate(downInfos.get(position - infos.size()));
        }
    }

    @Override
    public int getItemCount(){
        return infos.size() + downInfos.size();
    }

    @Override
    public int getItemViewType(int position){
        if(position < infos.size()){
            return VIEW_TYPE_INFO;
        }

        if(position - infos.size() < downInfos.size()){
            return VIEW_TYPE_DOWNINFO;
        }

        return -1;
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvDescricao;

        public InfoViewHolder(View itemView){
            super(itemView);

            tvTitulo = (TextView) itemView.findViewById(R.id.txtTituloItem);
            tvDescricao = (TextView) itemView.findViewById(R.id.txtDescricaoItem);
        }

        public void populate(Info info){
            tvTitulo.setText(info.getName());
            tvDescricao.setText(info.getData());
        }
    }

    public class DownInfoViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitulo, tvDescricao;
        ImageView imgDownload;

        public DownInfoViewHolder(View itemView){
            super(itemView);

            tvTitulo = (TextView) itemView.findViewById(R.id.txtTituloItem);
            tvDescricao = (TextView) itemView.findViewById(R.id.txtDescricaoItem);
            imgDownload = (ImageView) itemView.findViewById(R.id.imgDownload);
        }

        public void populate(DownInfo downInfow){
            tvTitulo.setText(downInfow.getName());

            if (TextUtils.isEmpty(downInfow.getData())){
                tvDescricao.setText("Baixar");
                tvDescricao.setTextColor(Color.RED);
                imgDownload.setVisibility(View.VISIBLE);
            }else {
                tvDescricao.setText(downInfow.getData());
            }
        }
    }
}