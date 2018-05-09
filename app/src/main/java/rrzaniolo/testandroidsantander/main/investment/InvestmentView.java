package rrzaniolo.testandroidsantander.main.investment;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import rrzaniolo.testandroidsantander.R;
import rrzaniolo.testandroidsantander.base.BaseInnerView;
import rrzaniolo.testandroidsantander.network.investment.models.BaseInfo;
import rrzaniolo.testandroidsantander.network.investment.models.MoreInfo;

/**
 * Implementation for the Investment View.
 * */
public class InvestmentView extends BaseInnerView implements InvestmentContract.View{
    //region --- Variables
    private static InvestmentView instance;
    public static InvestmentView getInstance(){
        if(instance == null)
            instance = new InvestmentView();

        return instance;
    }

    private InvestmentContract.Presenter contractPresenter;
    public InvestmentContract.Presenter getContractPresenter() {
        return contractPresenter;
    }
    public void setContractPresenter(InvestmentContract.Presenter contractPresenter) {
        this.contractPresenter = contractPresenter;
    }

    private View rootView;
    public View getRootView() {
        return rootView;
    }
    public void setRootView(View rootView) {
        this.rootView = rootView;
    }
    //endregion

    //region --- Constructor
    public InvestmentView() {
        setContractPresenter(new InvestmentPresenter(InvestmentView.this));
    }
    //endregion

    //region --- LifeCycle
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRootView(inflater.inflate(R.layout.inner_view_investment, container, false));
        return getRootView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getContractPresenter() != null) {
            getContractPresenter().onStart();
        }
    }
    //endregion

    //region --- Private Methods
    private TextView getTextView(@IdRes int id){
        return (TextView) getRootView().findViewById(id);
    }

    private ImageView getImageView(@IdRes int id){
        return (ImageView) getRootView().findViewById(id);
    }

    private View getView(@IdRes int id){
        return getRootView().findViewById(id);
    }

    private RecyclerView getRecyclerView(@IdRes int id){
        return (RecyclerView) getRootView().findViewById(id);
    }

    private void setRisk(View colorIndicator, ImageView arrowIndicator){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) colorIndicator.getLayoutParams();
        params.height = 35;
        colorIndicator.setLayoutParams(params);

        arrowIndicator.setImageResource(R.drawable.ic_arrow_down);
    }

    private String doubleToString(Double value){
        return String.valueOf(value);
    }
    //endregion

    //region --- Contract Methods
    @Override
    public void setHeaderInfo(String title, String fundName, String subtitle, String definition) {
        getTextView(R.id.fInvestment_tvHeader).setText(title);
        getTextView(R.id.fInvestment_tvName).setText(fundName);
        getTextView(R.id.fInvestment_tvSubtitle).setText(subtitle);
        getTextView(R.id.fInvestment_tvDefinition).setText(definition);
    }

    @Override
    public void setRiskInfo(String riskTitle, Integer risk) {
        getTextView(R.id.vRiskIndicator_tvTitle).setText(riskTitle);

        View colorIndicator = null;
        ImageView arrowIndicator = null;
        switch (risk){
            case 0:
                colorIndicator = getView(R.id.vRiskIndicator_vLower);
                arrowIndicator = getImageView(R.id.vRiskIndicator_ivLower);
                break;
            case 1:
                colorIndicator = getView(R.id.vRiskIndicator_vLow);
                arrowIndicator = getImageView(R.id.vRiskIndicator_ivLow);
                break;
            case 2:
                colorIndicator = getView(R.id.vRiskIndicator_vMedium);
                arrowIndicator = getImageView(R.id.vRiskIndicator_ivMedium);
                break;
            case 3:
                colorIndicator = getView(R.id.vRiskIndicator_vHigh);
                arrowIndicator = getImageView(R.id.vRiskIndicator_ivHigh);
                break;
            case 4:
                colorIndicator = getView(R.id.vRiskIndicator_vHigher);
                arrowIndicator = getImageView(R.id.vRiskIndicator_ivHigher);
                break;
        }

        if(colorIndicator != null && arrowIndicator != null)
            setRisk(colorIndicator, arrowIndicator);

    }

    @Override
    public void setMoreInfo(String infoTitle, MoreInfo moreInfo) {
        String aux = "";
        getTextView(R.id.vMoreInfo_tvTitle).setText(infoTitle);

        aux = doubleToString(moreInfo.getMonth().getFund())+"%";
        getTextView(R.id.vMoreInfo_tvMonthFound).setText(aux);
        aux = doubleToString(moreInfo.getMonth().getCdi())+"%";
        getTextView(R.id.vMoreInfo_tvMonthCdi).setText(aux);

        aux = doubleToString(moreInfo.getYear().getFund())+"%";
        getTextView(R.id.vMoreInfo_tvYearFund).setText(aux);
        aux = doubleToString(moreInfo.getYear().getCdi())+"%";
        getTextView(R.id.vMoreInfo_tvYearCdi).setText(aux);

        aux = doubleToString(moreInfo.getTwelveMonths().getFund())+"%";
        getTextView(R.id.vMoreInfo_tv12MonthFund).setText(aux);
        aux = doubleToString(moreInfo.getTwelveMonths().getCdi())+"%";
        getTextView(R.id.vMoreInfo_tv12MonthCdi).setText(aux);

    }

    @Override
    public void setListInfo(List<BaseInfo> infoList) {
        getRecyclerView(R.id.fInvestment_rvInfo).setAdapter(getContractPresenter().getInfoAdapter(infoList));
        getRecyclerView(R.id.fInvestment_rvInfo).setLayoutManager(new LinearLayoutManager(getBaseView()));
    }

    @Override
    public void setDownInfo(List<BaseInfo> downInfoList) {
        getRecyclerView(R.id.fInvestment_rvDownInfo).setAdapter(getContractPresenter().getDownInfoAdapter(downInfoList));
        getRecyclerView(R.id.fInvestment_rvDownInfo).setLayoutManager(new LinearLayoutManager(getBaseView()));
    }

    @Override
    public void setInvestmentAction() {
        ((Button)getRootView().findViewById(R.id.fInvestment_btn))
                .setOnClickListener(getContractPresenter().onInvestment());
    }

    @Override
    public void showInvestment() {
        getRootView().findViewById(R.id.fInvestment_llRoot).setVisibility(View.VISIBLE);

    }

    @Override
    public void hideInvestment() {
        getRootView().findViewById(R.id.fInvestment_llRoot).setVisibility(View.GONE);

    }

    @Override
    public void showLoading() {
        getRootView().findViewById(R.id.fInvestment_lv).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        getRootView().findViewById(R.id.fInvestment_lv).setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        getRootView().findViewById(R.id.fInvestment_ev).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        getRootView().findViewById(R.id.fInvestment_ev).setVisibility(View.GONE);
    }
    //endregion
}
