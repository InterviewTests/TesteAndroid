package br.com.iomarsantos.testeandroid.ui.base;

import android.util.Log;

import com.androidnetworking.common.ANConstants;
import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.data.Repository;
import br.com.iomarsantos.testeandroid.data.model.ApiError;
import br.com.iomarsantos.testeandroid.utils.AppConstants;
import br.com.iomarsantos.testeandroid.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Classe base que implementa a interface Presenter e fornece uma implementação básica para adicionar ou remover uma referencia a uma view
 */
public class BasePresenter<V extends BaseView> implements Presenter<V> {

    private static final String TAG = "BasePresenter";

    private final Repository mRepository;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;

    private V mBaseView;

    @Inject
    public BasePresenter(Repository repository,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        this.mRepository = repository;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V view) {
        mBaseView = view;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mBaseView = null;
    }

    public boolean isViewAttached() {
        return mBaseView != null;
    }

    public V getView() {
        return mBaseView;
    }

    public Repository getRepository() {
        return mRepository;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    public void handleApiError(ANError error) {

        if (error == null || error.getErrorBody() == null) {
            getView().onError(R.string.msg_api_error);
            return;
        }

        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_ERROR
                && error.getErrorDetail().equals(ANConstants.CONNECTION_ERROR)) {
            getView().onError(R.string.msg_connection_error);
            return;
        }

        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_ERROR
                && error.getErrorDetail().equals(ANConstants.REQUEST_CANCELLED_ERROR)) {
            getView().onError(R.string.msg_api_retry_error);
            return;
        }

        final GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        try {
            ApiError apiError = gson.fromJson(error.getErrorBody(), ApiError.class);

            if (apiError == null || apiError.getMessage() == null) {
                getView().onError(R.string.msg_api_error);
                return;
            }

            switch (error.getErrorCode()) {
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                case HttpsURLConnection.HTTP_FORBIDDEN:
                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                case HttpsURLConnection.HTTP_NOT_FOUND:
                default:
                    getView().onError(apiError.getMessage());
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            Log.e(TAG, "handleApiError", e);
            getView().onError(R.string.msg_api_error);
        }
    }

}