package com.alex.testeandroid.presentation.funds;

import com.alex.testeandroid.data.entities.funds.Funds;
import com.alex.testeandroid.domain.FundsInteractor;
import com.alex.testeandroid.presentation.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by alex on 27/08/18.
 */
public class FundsPresenter implements BasePresenter {

    //region FIELDS
    private FundsView view;
    private FundsInteractor fundsInteractor;
    private Disposable disposable;
    //endregion

    //region CONSTRUCT
    public FundsPresenter(FundsView view) {
        this.view = view;
        this.fundsInteractor = new FundsInteractor();
    }
    //endregion

    //region METHODS
    //region PUBLIC METHODS
    public void getFunds() {
        view.showProgress(true);

        disposable = fundsInteractor.getFunds()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Funds>() {
                    @Override
                    public void accept(Funds funds) throws Exception {
                        if (view == null) return;
                        view.showProgress(false);
                        view.loadData(funds);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (view == null) return;
                        view.showProgress(false);
                        view.showMessageErrorRequest();
                    }
                });
    }
    //endregion

    //region OVERRIDES METHODS
    @Override
    public void detachView() {
        if (disposable != null) disposable.dispose();
        if (view != null) view = null;
    }
    //endregion
    //endregion
}
