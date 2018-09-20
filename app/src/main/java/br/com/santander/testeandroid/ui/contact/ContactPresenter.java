package br.com.santander.testeandroid.ui.contact;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewCompat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import br.com.santander.testeandroid.R;
import br.com.santander.testeandroid.ui.contact.domain.models.Cell;
import br.com.santander.testeandroid.ui.contact.domain.models.CellsList;
import br.com.santander.testeandroid.ui.contact.domain.usecases.GetCells;
import br.com.santander.testeandroid.utils.validators.EmailValidator;
import br.com.santander.testeandroid.utils.validators.PhoneValidator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactPresenter {
    private ContactView view;
    private Resources resources;

    public ContactPresenter(ContactView view) {
        this.view = view;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
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

    public void validateFields(HashMap<Cell, TextInputLayout> viewsToValidate) {
        view.showProgressBar();
        boolean isValid = true;

        Iterator it = viewsToValidate.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            Cell cell = (Cell) pair.getKey();
            TextInputLayout inputLayout = (TextInputLayout) pair.getValue();
            String value = inputLayout.getEditText().getText().toString();

            if (inputLayout.isShown()) {
                switch (cell.getTypeField()) {
                    case TEL_NUMBER:
                        if (PhoneValidator.isEmpty(value)) {
                            inputLayout.setError(resources.getString(R.string.empty_field_error, cell.getMessage()));
                            isValid = false;
                            continue;
                        }

                        if (PhoneValidator.isInvalid(value)) {
                            inputLayout.setError(resources.getString(R.string.invalid_phone_error));
                            isValid = false;
                            continue;
                        }

                        clearErros(inputLayout);
                        break;
                    case EMAIL:
                        if (EmailValidator.isEmpty(value)) {
                            inputLayout.setError(resources.getString(R.string.empty_field_error, cell.getMessage()));
                            isValid = false;
                            continue;
                        }

                        if (EmailValidator.isInvalid(value)) {
                            inputLayout.setError(resources.getString(R.string.invalid_email_error));
                            isValid = false;
                            continue;
                        }

                        clearErros(inputLayout);
                        break;
                    default:
                        if (value.isEmpty()) {
                            inputLayout.setError(resources.getString(R.string.empty_field_error, cell.getMessage()));
                            isValid = false;
                            continue;
                        }

                        clearErros(inputLayout);
                        break;
                }
            }
        }

        if (isValid) {
            view.showSuccessMessage();
        } else {
            view.showForm();
        }
    }

    private void clearErros(TextInputLayout inputLayout) {
        inputLayout.setError(null);
        ColorStateList colorStateList = ColorStateList.valueOf(resources.getColor(R.color.valid_field));
        ViewCompat.setBackgroundTintList(inputLayout.getEditText(), colorStateList);
    }
}
