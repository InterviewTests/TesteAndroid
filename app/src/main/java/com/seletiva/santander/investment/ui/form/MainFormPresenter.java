package com.seletiva.santander.investment.ui.form;

import android.support.annotation.NonNull;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.controllers.cells.CellsController;
import com.seletiva.santander.investment.ui.form.domain.model.CellHolder;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

public class MainFormPresenter implements MainForm.Presenter {
    private CellsController controller;
    private MainForm.View view;

    @NonNull private Scheduler backgroundScheduler;
    @NonNull private Scheduler mainScheduler;

    public MainFormPresenter(@NonNull MainForm.View view,
                             @NonNull CellsController controller,
                             @NonNull Scheduler backgroundScheduler,
                             @NonNull Scheduler mainScheduler) {
        this.view = view;
        this.controller = controller;
        this.backgroundScheduler = backgroundScheduler;
        this.mainScheduler = mainScheduler;
    }

    @Override
    public void start() {}

    @Override
    public void loadCells() {
        view.showProgressDialog();

        controller.getCells()
                .subscribeOn(backgroundScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<CellHolder>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(CellHolder cellHolder) {
                        view.hideProgressDialog();
                        view.buildCellsUsingHolder(cellHolder);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgressDialog();
                        view.showMessage(R.string.conn_error_cells);
                    }

                    @Override
                    public void onComplete() {}
                });
    }
}
