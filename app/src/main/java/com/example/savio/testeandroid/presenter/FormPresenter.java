package com.example.savio.testeandroid.presenter;

import com.example.savio.testeandroid.contract.FormContract;
import com.example.savio.testeandroid.model.FormModel;

import java.util.List;

public class FormPresenter implements FormContract.Presenter {

    //instance view and model
    private FormContract.View view;
    private FormContract.Model model;

    //constructor receive the view context
    public FormPresenter(FormContract.View view){

        this.view = view;
        initPresenter();
    }

    //initiates de model and the view
    private void initPresenter(){

        model = new FormModel();
        view.initView();
    }

    @Override
    public void requestData() {

        //get the datas and send to view
        List<Object> data = model.getData();
        view.setViewData(data);
    }

    @Override
    public boolean testRequestData() {

        List<Object> data = model.getData();

        if (data.isEmpty()) return true;

        return false;
    }


}
