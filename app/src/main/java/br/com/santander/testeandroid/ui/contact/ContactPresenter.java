package br.com.santander.testeandroid.ui.contact;

import android.widget.EditText;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import br.com.santander.testeandroid.ui.contact.domain.models.Cell;
import br.com.santander.testeandroid.ui.contact.domain.models.CellsList;
import br.com.santander.testeandroid.ui.contact.domain.models.Type;
import br.com.santander.testeandroid.ui.contact.domain.usecases.GetCells;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactPresenter {
    private ContactView view;

    public ContactPresenter(ContactView view) {
        this.view = view;
    }

    public void loadScreenInfo() {
        view.prepareToolbar();
        view.showProgressBar();

        GetCells.getCells(new Callback<CellsList>() {
            @Override
            public void onResponse(Call<CellsList> call, Response<CellsList> response) {
                if (response.isSuccessful()) {
                    CellsList cellsList = response.body();

                    if (cellsList != null) {
                        view.loadInformationSuccess(cellsList.getCellList());
                        return;
                    }

                    view.loadInformationFailed();
                }
                view.loadInformationFailed();
            }

            @Override
            public void onFailure(Call<CellsList> call, Throwable t) {
                view.loadInformationFailed();
            }
        });
    }

    public void validateFields(HashMap<Cell, EditText> viewsToValidate) {
        view.showSuccessMessage();
    }
}
