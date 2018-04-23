package com.bruno.santander.santanderchallenge.investimento.presentation;

import android.app.Activity;
import android.util.Log;

import com.bruno.santander.santanderchallenge.investimento.model.ScreenMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class InvestimentoPresenter implements InvestimentoContract.Presenter {

    private Activity activity;
    private InvestimentoContract.View view;
    private CompositeDisposable disposable;

    public InvestimentoPresenter(Activity activity, InvestimentoContract.View view){
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void getFund() {
        disposable = new CompositeDisposable();

        disposable.add(
                Single.fromCallable(() -> {
                    ObjectMapper mapper = new ObjectMapper();

                    return mapper.readValue(activity.getAssets().open("fund.json"), ScreenMapper.class);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((myObject) -> view.onSuccessGetFund(myObject)));
    }

    @Override
    public void invest() {
        Log.d("Investimento", "invest");
//        TODO fake investing on it
    }

    @Override
    public void dispose() {
        if(disposable.size() > 0 && !disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
