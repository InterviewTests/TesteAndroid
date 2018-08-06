package br.com.iomarsantos.testeandroid.ui.fundo.investimentos;

import android.support.annotation.NonNull;

import com.androidnetworking.error.ANError;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.data.Repository;
import br.com.iomarsantos.testeandroid.data.model.ScreenResponse;
import br.com.iomarsantos.testeandroid.ui.base.BasePresenter;
import br.com.iomarsantos.testeandroid.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class InvestimentoPresenter<V extends InvestimentoView> extends BasePresenter<V>
        implements InvestimentoBasePresenter<V> {

    private static final String TAG = "InvestimentoPresenter";

    @Inject
    public InvestimentoPresenter(Repository repository,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(repository, schedulerProvider, compositeDisposable);
    }

    @Override
    public void findAllFundApiCall() {
        getView().showLoading();
        getCompositeDisposable().add(getRepository()
                .getFundApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ScreenResponse>() {
                    @Override
                    public void accept(@NonNull ScreenResponse response)
                            throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }

                        if (response.getScreen() != null) {
                            //Log.i(TAG, "Sucesso: " + response.getScreen());
                            getView().updateVies(response.getScreen());
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

                        //Log.e(TAG, "Erro: " + throwable.getMessage());

                    }
                }));
    }
}
