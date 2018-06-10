package com.adenilson.testeandroid.investiment.controller;

import com.adenilson.testeandroid.base.BasePresenter;
import com.adenilson.testeandroid.investiment.InvestmentInteractorImpl;
import com.adenilson.testeandroid.investiment.InvestmentView;
import com.adenilson.testeandroid.investiment.OnRequestInvestmentListener;
import com.adenilson.testeandroid.investiment.model.Info;
import com.adenilson.testeandroid.investiment.model.mapper.HeaderMapper;
import com.adenilson.testeandroid.investiment.model.mapper.InfoMapper;
import com.adenilson.testeandroid.investiment.model.mapper.MoreInfoMapper;
import com.adenilson.testeandroid.investiment.model.mapper.RiskMapper;
import com.adenilson.testeandroid.investiment.ui.adapter.section.DowInfoSection;
import com.adenilson.testeandroid.investiment.ui.adapter.section.HeaderSection;
import com.adenilson.testeandroid.investiment.ui.adapter.section.InfoSection;
import com.adenilson.testeandroid.investiment.ui.adapter.section.InvestSection;
import com.adenilson.testeandroid.investiment.ui.adapter.section.InvestmentSection;
import com.adenilson.testeandroid.investiment.ui.adapter.section.MoreInfoSection;
import com.adenilson.testeandroid.investiment.ui.adapter.section.RiskSection;
import com.adenilson.testeandroid.networking.webservices.investiment.InvestmentResponse;
import com.adenilson.testeandroid.networking.webservices.investiment.ScreenResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class InvestmentPresenterImp implements BasePresenter<InvestmentView>, InvestmentPresenter, OnRequestInvestmentListener {

    private InvestmentView mView;
    private InvestmentInteractorImpl mInteractor;

    public InvestmentPresenterImp(InvestmentView view) {
        mView = view;
        mInteractor = new InvestmentInteractorImpl();
    }

    @Override
    public void attachView(InvestmentView view) {
        mView = view;
        mInteractor = new InvestmentInteractorImpl();
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void getFund() {
        mView.showLoading();
        mInteractor.getFund(this);
    }

    @Override
    public void onRequestFundSuccess(InvestmentResponse response) {
        if (mView != null) {
            List<InvestmentSection> investmentSections = getAdapterDataFromResponse(response);
            mView.setInvestmentAdapterData(investmentSections);
            mView.hideLoading();

        }
    }

    private List<InvestmentSection> getAdapterDataFromResponse(InvestmentResponse response) {
        ArrayList<InvestmentSection> investmentSections = new ArrayList<>();
        ScreenResponse screen = response.getScreen();

        HeaderSection headerSection = new HeaderSection(new HeaderMapper().map(screen));
        investmentSections.add(headerSection);

        RiskSection riskSection = new RiskSection(new RiskMapper().map(screen));
        investmentSections.add(riskSection);

        MoreInfoSection moreInfoSection = new MoreInfoSection(new MoreInfoMapper().map(screen));
        investmentSections.add(moreInfoSection);

        List<Info> infos = new InfoMapper().mapInfoList(screen);
        for (Info info : infos) {
            InfoSection infoSection = new InfoSection(info);
            investmentSections.add(infoSection);
        }

        List<Info> dowInfos = new InfoMapper().mapDowInfoList(screen);
        for (Info info : dowInfos) {
            DowInfoSection infoSection = new DowInfoSection(info);
            investmentSections.add(infoSection);
        }

        InvestSection investSection = new InvestSection();
        investmentSections.add(investSection);

        return investmentSections;
    }

    @Override
    public void onRequestFundFailed(int messageResourceId) {
        if (mView != null) {
            mView.showError(messageResourceId);
            mView.hideLoading();
        }
    }
}
