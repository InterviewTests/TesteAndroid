package com.example.alessandrofsouza.santanderapp.presentation.pages.investment0;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.domain.model.Infos;
import com.example.alessandrofsouza.santanderapp.domain.model.MoreInfo;
import com.example.alessandrofsouza.santanderapp.domain.model.Screen;

import java.util.ArrayList;
import java.util.Arrays;

class InvestmentListPresenter0 {

    private final ArrayList<Screen> dataScreenSet;
    private Resources resources;
    private Screen screen;
    private ViewTreeObserver observer;
    private RecyclerView recyclerView;
    private InvestmentInfoAdapter0 investmentInfoAdapter0;
    private InvestmentMoreInfoAdapter0 investmentmoreInfoAdapter0;
    private View view;
    private Context context;


    public InvestmentListPresenter0(ArrayList<Screen> dataScreen) {
        this.dataScreenSet = dataScreen;
    }

    public void onBindRepositoryRowViewAtPosition(int i, final InvestmentAdapter0.ViewHolder viewHolder) {
        resources = viewHolder.itemView.getContext().getResources();
        screen = dataScreenSet.get(i);

        viewHolder.investTitle.setText(screen.getTitle());
        viewHolder.investTitle.setPadding(0, ((int) resources.getDimension(R.dimen.lMargin)), 0, 0);

        viewHolder.investFundName.setText(screen.getFundName());
        viewHolder.investFundName.setPadding(0, ((int) resources.getDimension(R.dimen.mMargin)), 0, 0);

        viewHolder.whatIs.setText(screen.getWhatIs());
        viewHolder.whatIs.setPadding(0, ((int) resources.getDimension(R.dimen.sMargin)), 0, 0);

        viewHolder.definition.setText(screen.getDefinition());
        viewHolder.definition.setPadding(0, ((int) resources.getDimension(R.dimen.sMargin) / 2), 0, 0);

        viewHolder.riskTitle.setText(screen.getRiskTitle());
        viewHolder.riskTitle.setPadding(0, ((int) resources.getDimension(R.dimen.sMargin)), 0, 0);


        observer = viewHolder.arrowRisk.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                for (int j = 0; j < viewHolder.viewRisk.size(); j++) {
                    viewHolder.viewRisk.get(screen.getRisk()).setScaleY(2);
                    viewHolder.arrowRisk.setX(viewHolder.viewRisk.get(screen.getRisk()).getX() + viewHolder.viewRisk.get(screen.getRisk()).getPivotX() - viewHolder.arrowRisk.getWidth() / 3);
                }
            }
        });


        viewHolder.infoTitle.setText(screen.getInfoTitle());
        viewHolder.infoTitle.setPadding(0, ((int) resources.getDimension(R.dimen.sMargin)), 0, 0);




        recycleMoreInfo(viewHolder.itemView, context);//chama o recycleView
        ArrayList<Infos> moreInfo = addListInvestmentMoreInfo(screen.getMoreInfo());
        investmentmoreInfoAdapter0.addListMoreInfo(moreInfo);

        recycleInfo(viewHolder.itemView, context);//chama o recycleView
        ArrayList<Infos> listInfo = screen.getInfo();
        investmentInfoAdapter0.addListInfo(listInfo);

        recycleDownInfo(viewHolder.itemView, context);//chama o recycleView
        ArrayList<Infos> listDownInfo = screen.getDownInfo();
        investmentInfoAdapter0.addListInfo(listDownInfo);

    }


    private ArrayList<Infos> addListInvestmentMoreInfo(MoreInfo moreInfo) {
        ArrayList<Infos> moreInfoList = new ArrayList<>();
        moreInfoList.add(new Infos("", "Fundo", "CDI"));
        moreInfoList.add(new Infos("No mês", moreInfo.getMonth().getFund().toString().concat("%"), moreInfo.getMonth().getCDI().toString().concat("%")));
        moreInfoList.add(new Infos("No ano", moreInfo.getYear().getFund().toString().concat("%"), moreInfo.getYear().getCDI().toString().concat("%")));
        moreInfoList.add(new Infos("12 meses", moreInfo.getMonths12().getFund().toString().concat("%"), moreInfo.getMonths12().getCDI().toString().concat("%")));

        Log.i("TAGX", ""+moreInfoList.size());
        return moreInfoList;
    }


    public int getRepositoriesRowsCount() {
        return dataScreenSet.size();
    }


    private void recycleMoreInfo(View view, Context context) {
        recyclerView = view.findViewById(R.id.recycleViewMoreInfo);
        investmentmoreInfoAdapter0 = new InvestmentMoreInfoAdapter0();
        recyclerView.setAdapter(investmentmoreInfoAdapter0);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    private void recycleInfo(View view, Context context) {
        recyclerView = view.findViewById(R.id.recycleViewInfo);
        investmentInfoAdapter0 = new InvestmentInfoAdapter0();
        recyclerView.setAdapter(investmentInfoAdapter0);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void recycleDownInfo(View view, Context context) {
        recyclerView = view.findViewById(R.id.recycleViewDownInfo);
        investmentInfoAdapter0 = new InvestmentInfoAdapter0();
        recyclerView.setAdapter(investmentInfoAdapter0);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
