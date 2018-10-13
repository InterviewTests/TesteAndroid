package br.com.itamarlourenco.santandertecnologia_testeandroid.app;

import java.util.ArrayList;

import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;

public interface MainContract {
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
    interface GetCellsIntractors {

        interface OnFinishedListener {
            void onFinished(ArrayList<Cell> cellArrayList);

            void onFailure(Throwable t);
        }

        void getCellArrayList(OnFinishedListener onFinishedListener);
    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the dateis fetched
     **/
    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<Cell> cellArrayList);

        void onResponseFailure(Throwable throwable);
    }
}
