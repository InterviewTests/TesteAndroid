package com.seletiva.santander.investment.ui.form;

import android.content.Context;
import android.support.annotation.NonNull;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.datasources.cells.CellsDatasource;
import com.seletiva.santander.investment.ui.form.domain.model.Cell;
import com.seletiva.santander.investment.ui.form.domain.model.CellHolder;
import com.seletiva.santander.investment.ui.form.domain.model.SendButtonClickEvent;
import com.seletiva.santander.investment.ui.view.FormComponentView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

public class MainFormPresenter implements MainForm.Presenter {
    private CellsDatasource controller;
    private MainForm.View view;
    private ArrayList<FormComponentView> formComponents;

    @NonNull private Scheduler backgroundScheduler;
    @NonNull private Scheduler mainScheduler;

    public MainFormPresenter(@NonNull MainForm.View view,
                             @NonNull CellsDatasource controller,
                             @NonNull Scheduler backgroundScheduler,
                             @NonNull Scheduler mainScheduler) {
        this.view = view;
        this.controller = controller;
        this.backgroundScheduler = backgroundScheduler;
        this.mainScheduler = mainScheduler;

        formComponents = new ArrayList<>();
    }

    @Override
    public void start() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void stop() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onFormClickEvent(Object event) {
        if (event.getClass() == SendButtonClickEvent.class) {
            if (isFormValid()) {
                view.formValidated();
            }
        }
    }

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
                        buildFormUsingCellHolder(cellHolder);
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

    private void buildFormUsingCellHolder(CellHolder holder) {
        Context context = view.getContext();

        if (context != null) {
            for(Cell cell:holder.getCells()) {
                FormComponentView component = new FormComponentView(context);

                component.inflateWithCellModel(cell);
                formComponents.add(component);
                view.addFormComponent(component);
            }
        }
    }

    private boolean isFormValid() {
        boolean isValid = true;

        for(FormComponentView component:formComponents) {
            if (!component.isValid()) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }
}
