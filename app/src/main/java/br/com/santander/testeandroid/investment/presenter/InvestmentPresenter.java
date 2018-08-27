package br.com.santander.testeandroid.investment.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.santander.testeandroid.base.BasePresenter;
import br.com.santander.testeandroid.investment.InvestmentContract;
import br.com.santander.testeandroid.investment.InvestmentInteractor;
import br.com.santander.testeandroid.investment.model.BaseInfo;
import br.com.santander.testeandroid.investment.model.InvestmentResponse;
import br.com.santander.testeandroid.investment.model.Screen;

public class InvestmentPresenter extends BasePresenter implements
        InvestmentInteractor.InvestmentListener {

    private InvestmentContract contractView;
    private InvestmentInteractor investmentInteractor;

    public InvestmentPresenter(InvestmentContract contractView) {
        setContractView(contractView);
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (investmentInteractor == null)
            investmentInteractor = new InvestmentInteractor();

        getContractView().hideContainer();
        getContractView().showLoading();
        getContractView().hideError();
        investmentInteractor.getFound(this);
    }

    public void onInvestmentAction() {
        //todo change color button
    }

    private InvestmentContract getContractView() {
        return contractView;
    }

    private void setContractView(@NonNull InvestmentContract contractView) {
        this.contractView = contractView;
    }

    @Override
    public void onGetFoundSuccess(InvestmentResponse response) {
        getContractView().hideLoading();
        getContractView().showContainer();

        Screen screen = response.getScreen();

        if (screen != null) {
            getContractView().setHeaderInfo(screen.getTitle(), screen.getFundName(),
                    screen.getWhatIs(), screen.getDefinition());
            getContractView().setMoreInfo(screen.getInfoTitle(), screen.getMoreInfo());
            getContractView().setRiskInfo(screen.getRiskTitle(), screen.getRisk());
            getContractView().setListInfo(joinInfoList(screen.getInfoList(), screen.getDownInfoList()));
            getContractView().setInvestmentListener();
        }
    }

    @Override
    public void onGetFoundFailure() {
        getContractView().hideLoading();
        getContractView().showError();
    }

    private List joinInfoList(List<BaseInfo> infoList, List<BaseInfo> downInfoList) {
        List<BaseInfo> joinedList = new ArrayList<>();
        joinedList.addAll(infoList);
        joinedList.addAll(downInfoList);

        return joinedList;
    }

}
