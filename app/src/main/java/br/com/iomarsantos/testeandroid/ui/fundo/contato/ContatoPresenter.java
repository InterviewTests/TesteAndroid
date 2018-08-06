package br.com.iomarsantos.testeandroid.ui.fundo.contato;

import android.support.annotation.NonNull;
import android.util.Log;

import com.androidnetworking.error.ANError;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.data.Repository;
import br.com.iomarsantos.testeandroid.data.model.CellResponse;
import br.com.iomarsantos.testeandroid.ui.base.BasePresenter;
import br.com.iomarsantos.testeandroid.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ContatoPresenter<V extends ContatoView> extends BasePresenter<V>
        implements ContatoBasePresenter<V> {

    @Inject
    public ContatoPresenter(Repository repository,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(repository, schedulerProvider, compositeDisposable);
    }

    @Override
    public void findAllCellsApiCall() {
        getView().showLoading();
        getCompositeDisposable().add(getRepository()
                .getCellApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<CellResponse>() {
                    @Override
                    public void accept(@NonNull CellResponse cellResponse)
                            throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }

                        if (cellResponse.getCells() != null && !cellResponse.getCells().isEmpty()) {
                            getView().createViews(cellResponse.getCells());
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
                    }
                }));
    }
}

