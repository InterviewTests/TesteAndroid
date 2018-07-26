package info.dafle.testeandroid.mvp.contato;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.view.View;

import java.util.List;

import info.dafle.testeandroid.model.Cell;
import info.dafle.testeandroid.util.Util;

public class ContatoPresenter implements ContatoContract.Presenter {

    private ContatoContract.View mView;
    private ContatoModel mModel;

    ContatoPresenter(@NonNull ContatoContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mModel = new ContatoModel();
    }

    @Override
    public void start() {
        loadCells();
    }

    @Override
    public void loadCells() {

        mView.showProgress();
        mModel.getCells(new ContatoContract.Model.LoadCellsCallback() {
            @Override
            public void onCellsLoaded(List<Cell> cells) {
                if (!mView.isActive()) {
                    return;
                }
                mView.buildLayout(cells);
                mView.hideProgress();
            }

            @Override
            public void onErrorFetchData(String error) {
                mView.showError(error);
                mView.hideProgress();
            }
        });
    }

    @Override
    public void validateForm(List<Cell> cells) {

        for (Cell cell : cells) {

            View view = mView.findViewById(cell.getId());

            if (Type.getFromInt(cell.getType()).equals(Type.field)) {

                TextInputLayout textInputLayout = (TextInputLayout) view;

                mView.removeErrorFromView(textInputLayout);

                if (cell.isRequired() && textInputLayout.getEditText().getText().toString().equals("")) {

                    mView.setErrorToEditText(textInputLayout, "Este Campo não pode ser vazio");
                    return;
                }

                if (TypeField.getFromObject(cell.getTypefield()).equals(TypeField.email) && !new Util().isValidEmail(textInputLayout.getEditText().getText().toString())) {

                    mView.setErrorToEditText(textInputLayout, "Por favor preencha um email válido");
                    return;
                }

                if (TypeField.getFromObject(cell.getTypefield()).equals(TypeField.telNumber) && !new Util().isValidPhoneNumber(textInputLayout.getEditText().getText().toString())) {

                    mView.setErrorToEditText(textInputLayout, "Por favor preencha um telefone válido");
                    return;
                }
            }
        }

        mView.showMessageSuccess(true);
    }
}
