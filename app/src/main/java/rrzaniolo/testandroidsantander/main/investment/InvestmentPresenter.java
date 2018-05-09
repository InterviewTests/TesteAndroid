package rrzaniolo.testandroidsantander.main.investment;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/9/2018.
 * All rights reserved.
 */

import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rrzaniolo.testandroidsantander.main.investment.adapter.InfoAdapter;
import rrzaniolo.testandroidsantander.network.NetworkManager;
import rrzaniolo.testandroidsantander.network.investment.models.BaseInfo;
import rrzaniolo.testandroidsantander.network.investment.models.Screen;
import rrzaniolo.testandroidsantander.network.investment.response.InvestmentResponse;

/**
 * Implementation of the Investment Presenter.
 * */
public class InvestmentPresenter implements InvestmentContract.Presenter{
    //region --- Variable
    private InvestmentContract.View contractView;
    public InvestmentContract.View getContractView() {
        return contractView;
    }
    public void setContractView(InvestmentContract.View contractView) {
        this.contractView = contractView;
    }
    //endregion

    //region --- Constructor
    public InvestmentPresenter(InvestmentContract.View contractView) {
        setContractView(contractView);
    }
    //endregion

    //region --- Private Methods
    private void onSuccess(InvestmentResponse response){
        Screen screen = response.getScreen();

        if(getContractView() != null){
            getContractView().setHeaderInfo(screen.getTitle(), screen.getFundName(), screen.getSubTitle(), screen.getDefinition());
            getContractView().setRiskInfo(screen.getRiskTitle(), screen.getRisk());
            getContractView().setMoreInfo(screen.getInfoTitle(), screen.getMoreInfo());
            getContractView().setListInfo(screen.getInfoList());
            getContractView().setDownInfo(screen.getDownInfoList());
            getContractView().setInvestmentAction();
        }
    }
    //endregion
    //region --- Contract Methods
    @Override
    public void onStart() {
        if(getContractView() != null){
            getContractView().hideError();
            getContractView().showLoading();

            NetworkManager.getInstance().getFound().enqueue(new Callback<InvestmentResponse>() {
                @Override
                public void onResponse(@NonNull Call<InvestmentResponse> call, @NonNull Response<InvestmentResponse> response) {
                    getContractView().hideLoading();
                    //noinspection ConstantConditions
                    onSuccess(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<InvestmentResponse> call, @NonNull Throwable t) {
                    getContractView().hideLoading();
                    getContractView().showError();
                }
            });
        }
    }

    @Override
    public View.OnClickListener onInvestment() {
        return null;
    }

    @Override
    public InfoAdapter getInfoAdapter(List<BaseInfo> infoList) {
        return new InfoAdapter(infoList);
    }

    @Override
    public InfoAdapter getDownInfoAdapter(List<BaseInfo> downInfoList) {
        return new InfoAdapter(downInfoList);
    }
    //endregion
}
