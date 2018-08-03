package br.com.iomarsantos.testeandroid.ui.fundo.contato;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.androidnetworking.error.ANError;

import java.util.List;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.data.Repository;
import br.com.iomarsantos.testeandroid.data.model.CellResponse;
import br.com.iomarsantos.testeandroid.entity.Cell;
import br.com.iomarsantos.testeandroid.entity.TypeField;
import br.com.iomarsantos.testeandroid.ui.base.BasePresenter;
import br.com.iomarsantos.testeandroid.ui.base.rx.SchedulerProvider;
import br.com.iomarsantos.testeandroid.ui.fundo.views.CellType;
import br.com.iomarsantos.testeandroid.ui.fundo.views.CellView;
import br.com.iomarsantos.testeandroid.utils.ViewUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ContatoPresenter<V extends ContatoView> extends BasePresenter<V>
        implements ContatoBasePresenter<V> {

    private static final String TAG = "ContatoPresenter";
    private CellView cellView;

    @Inject
    ContatoPresenter(Repository repository,
                     SchedulerProvider schedulerProvider,
                     CompositeDisposable compositeDisposable) {
        super(repository, schedulerProvider, compositeDisposable);
    }

    @Override
    public void findAllCellsApiCall() {
        getView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getCellApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<CellResponse>() {
                    @Override
                    public void accept(@NonNull CellResponse response)
                            throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }

                        if (response.getCells() != null) {
                            Log.i(TAG, "Sucesso: " + response.getCells());
                            configureViews(response.getCells());
                        }

                        getView().hideLoading();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }

                        getView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }

                        Log.e(TAG, "Erro: " + throwable.getMessage());

                    }
                }));
    }

    private void configureViews(List<Cell> cells) {
        for (Cell cell : cells) {
            CellView cellView = new CellType().get(cell, getView());
            if (cellView != null) {
                View view = cellView.getView();
                getView().addView(view);
                configureMargin(cell, cellView);
                configureFields(cell, view, cellView);
            }
        }
    }

    private void configureFields(Cell cell, View view, CellView cellView) {
        this.cellView = cellView;
        String typefield = cell.getTypefield();
        if (typefield != null) {
            switch (typefield) {
                case TypeField.TEXT:
                    getView().configureTextField(view);
                    break;
                case TypeField.PHONE_NUMBER:
                    getView().configuraPhoneField(view);
                    break;
                case TypeField.EMAIL:
                    getView().configureEmailField(view);
                    break;
            }
        }
    }

    private void configureMargin(Cell cell, CellView cellView) {
        LinearLayout.LayoutParams viewLayoutParams = (LinearLayout.LayoutParams) cellView.getView().getLayoutParams();
        viewLayoutParams.topMargin = getMargin(cell);
        cellView.getView().setLayoutParams(viewLayoutParams);
    }

    private int getMargin(Cell cell) {
        return ViewUtils.dpToPx(cell.getTopSpacing());
    }

}
