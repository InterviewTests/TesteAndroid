package com.example.alessandrofsouza.santanderapp.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.model.Infos;
import com.example.alessandrofsouza.santanderapp.model.Screen;

import java.util.ArrayList;
import java.util.Collections;

public class ListaScreenAdapter extends RecyclerView.Adapter<ListaScreenAdapter.ViewHolder> {

    private static final String TAG = "Santander ";
    public ArrayList<Screen> dataScreenSet;


    public ListaScreenAdapter() {
        dataScreenSet = new ArrayList<>();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_layout_invest, parent, false);

        return new ViewHolder(root);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Screen screen = dataScreenSet.get(position);
        Resources res = holder.itemView.getContext().getResources();


        holder.investTitle.setText(screen.getTitle());
        holder.investTitle.setPadding(0, ((int) res.getDimension(R.dimen.lMargin)), 0, 0);

        holder.investFundName.setText(screen.getFundName());
        holder.investFundName.setPadding(0, ((int) res.getDimension(R.dimen.mMargin)), 0, 0);

        holder.whatIs.setText(screen.getWhatIs());
        holder.whatIs.setPadding(0, ((int) res.getDimension(R.dimen.sMargin)), 0, 0);

        holder.definition.setText(screen.getDefinition());
        holder.definition.setPadding(0, ((int) res.getDimension(R.dimen.sMargin) / 2), 0, 0);

        holder.riskTitle.setText(screen.getRiskTitle());
        holder.riskTitle.setPadding(0, ((int) res.getDimension(R.dimen.sMargin)), 0, 0);



        //for (Infos f : screen.info) Log.i(TAG, f.getName());
        ArrayList<Infos> listInfo = screen.getInfo();
        /*for (int i = 0; i < listInfo.size(); i++) {
            Infos f = listInfo.get(i);
            Log.i(TAG, f.getName() + "-" + f.getData());
        }*/










        holder.infoTitle.setText(screen.getInfoTitle());
        holder.moreInfo_fundo_month.setText(screen.getMoreInfo().getMonth().getFund().toString().concat("%"));
        holder.moreInfo_cdi_month.setText(screen.getMoreInfo().getMonth().getCDI().toString().concat("%"));
        holder.moreInfo_fundo_year.setText(screen.getMoreInfo().getYear().getFund().toString().concat("%"));
        holder.moreInfo_cdi_year.setText(screen.getMoreInfo().getYear().getCDI().toString().concat("%"));
        holder.moreInfo_fundo_12months.setText(screen.getMoreInfo().getMonths12().getFund().toString().concat("%"));
        holder.moreInfo_cdi_12months.setText(screen.getMoreInfo().getMonths12().getCDI().toString().concat("%"));

    }



    @Override
    public int getItemViewType(int position) {
        return position;
    }



    @Override
    public int getItemCount() {
        return dataScreenSet.size();
    }



    public void addListScreen(Screen listScreen) {
        dataScreenSet.addAll(Collections.singleton(listScreen));
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView investTitle;
        private TextView investFundName;
        private TextView whatIs;
        private TextView definition;
        private TextView riskTitle;

        private TextView infoTitle;
        private TextView moreInfo_fundo_month;
        private TextView moreInfo_cdi_month;
        private TextView moreInfo_fundo_year;
        private TextView moreInfo_cdi_year;
        private TextView moreInfo_fundo_12months;
        private TextView moreInfo_cdi_12months;

        //public ListView infosView;
        public RecyclerView recyclerViewInfo;
        private TextView info_title;
        private TextView info_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            investTitle = itemView.findViewById(R.id.investTitle);
            investFundName = itemView.findViewById(R.id.investFundName);
            whatIs = itemView.findViewById(R.id.whatIs);
            definition = itemView.findViewById(R.id.definition);
            riskTitle = itemView.findViewById(R.id.riskTitle);

            infoTitle = itemView.findViewById(R.id.infoTitle);
            moreInfo_fundo_month = itemView.findViewById(R.id.moreInfo_fundo_month);
            moreInfo_cdi_month = itemView.findViewById(R.id.moreInfo_cdi_month);
            moreInfo_fundo_year = itemView.findViewById(R.id.moreInfo_fundo_year);
            moreInfo_cdi_year = itemView.findViewById(R.id.moreInfo_cdi_year);
            moreInfo_fundo_12months = itemView.findViewById(R.id.moreInfo_fundo_12months);
            moreInfo_cdi_12months = itemView.findViewById(R.id.moreInfo_cdi_12months);

            //infosView = itemView.findViewById(R.id.infos);
            recyclerViewInfo = itemView.findViewById(R.id.recycleViewInfos);

            info_title = itemView.findViewById(R.id.info_title);
            info_content = itemView.findViewById(R.id.info_content);
        }

    }

}
