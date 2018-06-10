package info.dafle.testeandroid.mvp.contato;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;

import java.util.List;

import info.dafle.testeandroid.BasePresenter;
import info.dafle.testeandroid.BaseView;
import info.dafle.testeandroid.model.Cell;

public interface ContatoContract {

    interface View extends BaseView<Presenter> {

        void showProgress();

        void hideProgress();

        void buildLayout(@NonNull List<Cell> cells);

        void showError(String message);

        boolean isActive();

        void removeErrorFromView(TextInputLayout textInputLayout);

        void setErrorToEditText(TextInputLayout textInputLayout, String error);

        android.view.View findViewById(int id);

        void showMessageSuccess(boolean show);
    }

    interface Presenter extends BasePresenter {

        void loadCells();

        void validateForm(List<Cell> cells);
    }

    interface Model {

        void getCells(LoadCellsCallback loadCellsCallback);

        interface LoadCellsCallback {

            void onCellsLoaded(List<Cell> cells);

            void onErrorFetchData(String error);
        }
    }
}
