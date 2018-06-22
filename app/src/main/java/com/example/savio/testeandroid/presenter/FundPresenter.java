package com.example.savio.testeandroid.presenter;

import com.example.savio.testeandroid.contract.FundContract;
import com.example.savio.testeandroid.model.FundModel;

import java.util.List;

public class FundPresenter implements FundContract.Presenter {

    //instance view and model
    private FundContract.View view;
    private FundContract.Model model;

    //constructor receive the view context
    public FundPresenter(FundContract.View view){

        this.view = view;
        initPresenter();
    }

    //initiates de model and the view
    private void initPresenter(){

        model = new FundModel();
        view.initView();
    }

    @Override
    public void requestData() {

        //get the datas and send to view
        List<Object> data = model.getData();
        view.setViewData(data);
    }

}
