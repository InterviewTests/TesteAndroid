package br.com.itamarlourenco.santandertecnologia_testeandroid.app;

import java.util.ArrayList;

import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Funds;

public interface ViewContract {
    /**
     * Interact with View
     * */
    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestData();

    }

    /**
     * Classes for fetching data of database, api, or others.
     **/
    interface GetIntractors {

        interface OnFinishedListener<T> {
            void onFinished(T funds);

            void onFailure(Throwable t);
        }

        void getArrayList(OnFinishedListener onFinishedListener);
    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the dateis fetched
     **/
    interface ViewActions<T> {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(T data);

        void onResponseFailure(Throwable throwable);
    }
}
