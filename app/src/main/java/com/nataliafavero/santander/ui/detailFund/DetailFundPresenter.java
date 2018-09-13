package com.nataliafavero.santander.ui.detailFund;

import android.support.annotation.NonNull;

import com.nataliafavero.santander.data.model.Info;
import com.nataliafavero.santander.data.model.MoreInfo;
import com.nataliafavero.santander.data.model.Screen;
import com.nataliafavero.santander.data.model.ScreenResponse;
import com.nataliafavero.santander.data.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nataliafavero on 11/09/18.
 */

public class DetailFundPresenter implements DetailFundContract.Presenter {

    private final DetailFundContract.View mView;
    private final ApiService apiService;

    public DetailFundPresenter(@NonNull DetailFundContract.View view) {
        mView = view;
        mView.setPresenter(this);
        apiService = new ApiService();
    }

    @Override
    public void start() {
        getFund();
    }

    @Override
    public void getFund() {
        apiService.getApi().getFund().enqueue(new Callback<ScreenResponse>() {
            @Override
            public void onResponse(Call<ScreenResponse> call, Response<ScreenResponse> response) {
                if (response.isSuccessful()) {

                    DetailFundModel model = createBasicModelFund(response.body().getScreen());
                    List<DetailFundInfoModel> moreInfo = createListMoreInfo(response.body().getScreen().getMoreInfo());
                    List<DetailFundInfoModel> info = createListInfo(response.body().getScreen().getInfo(), response.body().getScreen().getDownInfo());

                    mView.showFund(model, moreInfo, info);
                }
            }

            @Override
            public void onFailure(Call<ScreenResponse> call, Throwable t) {

            }
        });
    }

    private List<DetailFundInfoModel> createListMoreInfo(MoreInfo moreInfo) {
        List<DetailFundInfoModel> list = new ArrayList<>();
        list.add(new DetailFundInfoModel("No mês", moreInfo.getMonth().getFund().toString()+"%", moreInfo.getMonth().getCDI().toString()+"%"));
        list.add(new DetailFundInfoModel("No ano", moreInfo.getYear().getFund().toString()+"%", moreInfo.getYear().getCDI().toString()+"%"));
        list.add(new DetailFundInfoModel("12 meses", moreInfo.getTwelveMoths().getFund().toString()+"%", moreInfo.getTwelveMoths().getCDI().toString()+"%"));
        return list;

    }

    private List<DetailFundInfoModel> createListInfo(List<Info> info, List<Info> downInfo) {
        info.addAll(downInfo);

        List<DetailFundInfoModel> list = new ArrayList<>();

        for (Info inf:info) {
            list.add(new DetailFundInfoModel(inf.getName(), "", inf.getData()));
        }

        return list;
    }

    private DetailFundModel createBasicModelFund(Screen response) {
        DetailFundModel model = new DetailFundModel();
        model.setDefinition(response.getDefinition());
        model.setFundName(response.getFundName());
        model.setInfoTitle(response.getInfoTitle());
        model.setDefinition(response.getDefinition());
        model.setRisk(response.getRisk());
        model.setRiskTitle(response.getRiskTitle());
        model.setTitle(response.getTitle());
        model.setWhatIs(response.getWhatIs());
        return model;
    }
}
