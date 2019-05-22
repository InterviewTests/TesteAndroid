package com.example.alessandrofsouza.santanderapp.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.model.Infos;
import com.example.alessandrofsouza.santanderapp.model.Screen;

import java.util.ArrayList;
import java.util.Collections;

public class ListaScreenAdapter extends RecyclerView.Adapter<ListaScreenAdapter.ViewHolder> {

    private static final String TAG = "Santander ";
    public ArrayList<Screen> dataScreenSet;

    private RecyclerView recyclerView;
    private ListaInfoAdapter listaInfoAdapter;
    Context context;


    public ListaScreenAdapter() {
        dataScreenSet = new ArrayList<>();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_layout_invest, parent, false);
        context = view.getContext();

        recycleInfo(view, context);//chama o recycleView
        recycleDownInfo(view, context);//chama o recycleView

        return new ViewHolder(view);
    }

    private void recycleInfo(View root, Context context) {
        recyclerView = root.findViewById(R.id.recycleViewInfo);
        listaInfoAdapter = new ListaInfoAdapter();
        recyclerView.setAdapter(listaInfoAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void recycleDownInfo(View root, Context context) {
        recyclerView = root.findViewById(R.id.recycleViewDownInfo);
        listaInfoAdapter = new ListaInfoAdapter();
        recyclerView.setAdapter(listaInfoAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Screen screen = dataScreenSet.get(position);
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


        holder.infoTitle.setText(screen.getInfoTitle());
        holder.moreInfo_fundo_month.setText(screen.getMoreInfo().getMonth().getFund().toString().concat("%"));
        holder.moreInfo_cdi_month.setText(screen.getMoreInfo().getMonth().getCDI().toString().concat("%"));
        holder.moreInfo_fundo_year.setText(screen.getMoreInfo().getYear().getFund().toString().concat("%"));
        holder.moreInfo_cdi_year.setText(screen.getMoreInfo().getYear().getCDI().toString().concat("%"));
        holder.moreInfo_fundo_12months.setText(screen.getMoreInfo().getMonths12().getFund().toString().concat("%"));
        holder.moreInfo_cdi_12months.setText(screen.getMoreInfo().getMonths12().getCDI().toString().concat("%"));


        ArrayList<Infos> listInfo = screen.getInfo();
        listaInfoAdapter.addListInfo(listInfo);

        ArrayList<Infos> listDownInfo = screen.getDownInfo();
        listaInfoAdapter.addListInfo(listDownInfo);





        final ViewTreeObserver observer = holder.viewColors1.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    holder.viewColors1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    holder.viewColors1.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }*/

                switch (screen.getRisk()){
                    case 1:
                        holder.viewColors1.setScaleY(2);
                        holder.arrowRisk.setX(holder.viewColors1.getX() + holder.viewColors1.getPivotX() - holder.arrowRisk.getWidth() / 5);
                        break;
                    case 2:
                        holder.viewColors2.setScaleY(2);
                        holder.arrowRisk.setX(holder.viewColors2.getX() + holder.viewColors2.getPivotX() - holder.arrowRisk.getWidth() / 5);
                        break;
                    case 3:
                        holder.viewColors3.setScaleY(2);
                        holder.arrowRisk.setX(holder.viewColors3.getX() + holder.viewColors3.getPivotX() - holder.arrowRisk.getWidth() / 5);
                        break;
                    case 4:
                        holder.viewColors4.setScaleY(2);
                        holder.arrowRisk.setX(holder.viewColors4.getX() + holder.viewColors4.getPivotX() - holder.arrowRisk.getWidth() / 5);
                        break;
                    case 5:
                        holder.viewColors5.setScaleY(2);
                        holder.arrowRisk.setX(holder.viewColors1.getX() + holder.viewColors1.getPivotX() - holder.arrowRisk.getWidth() / 5);
                        break;
                    default:
                        holder.arrowRisk.setX(holder.viewColors1.getX() - holder.viewColors1.getPivotX() - holder.arrowRisk.getWidth() / 5);
                        break;
                }
            }
        });



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

        public RecyclerView recycleViewInfo;

        private View viewColors1;
        private View viewColors2;
        private View viewColors3;
        private View viewColors4;
        private View viewColors5;

        private ImageView arrowRisk;


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

            recycleViewInfo = itemView.findViewById(R.id.recycleViewInfo);


            viewColors1 = itemView.findViewById(R.id.viewColors1);
            viewColors2 = itemView.findViewById(R.id.viewColors2);
            viewColors3 = itemView.findViewById(R.id.viewColors3);
            viewColors4 = itemView.findViewById(R.id.viewColors4);
            viewColors5 = itemView.findViewById(R.id.viewColors5);

            arrowRisk = itemView.findViewById(R.id.arrowRisk);

        }

    }

}
