package br.com.testeandroid.feature.contato;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.testeandroid.R;
import br.com.testeandroid.feature.InvestimentoPresenter;
import br.com.testeandroid.model.Cells;
import br.com.testeandroid.utils.Constants;
import br.com.testeandroid.utils.Validador;
import br.com.testeandroid.utils.dinamico.CellDinamico;
import br.com.testeandroid.utils.dinamico.CellGeradorDin;
import br.com.testeandroid.utils.dinamico.DefinirConstraints;
import br.com.testeandroid.utils.dinamico.elementos.ButtonDin;
import br.com.testeandroid.utils.dinamico.elementos.CheckBoxDin;
import br.com.testeandroid.utils.dinamico.elementos.EditTextDin;
import br.com.testeandroid.utils.dinamico.elementos.TextViewDin;

public class ContatoFragment extends Fragment implements ContatoView{

    private ContatoPresenterImpl presenter;
    private View view;

    private ConstraintLayout constraintLayout;

    public ContatoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contato, container, false);

        presenter = new ContatoPresenterImpl(this);

        presenter.getCellArrayList();
        clickFecharMsgSucesso();
        return view;
    }

    private void clickFecharMsgSucesso() {
        view.findViewById(R.id.txtFechar)
                .setOnClickListener(new ClickFecharSucesso());
    }

    private class ClickFecharSucesso implements View.OnClickListener {
        @Override
        public void onClick(final View view) {
            finishSucesso();
        }
    }

    @Override
    public void ConfigureCells(ArrayList<Cells> cells) {

        for (Cells cell : cells) {
            int i = cells.indexOf(cell);
            int inicioID = cell.getId();
            int fimID = getFormLayoutContainer().getId();
            boolean isFirstElement = true;

            if (i > 0) {
                Cells cellanterior = cells.get(i - 1);
                fimID = cellanterior.getId();
                isFirstElement = false;
            }

            addField(cell);
            DefinirConstraints.definirConstraints(getFormLayoutContainer(), cell, inicioID, fimID, isFirstElement);
        }
    }


    @Override
    public void showProgress() {
        view.findViewById(R.id.show).setVisibility(View.VISIBLE);
        view.findViewById(R.id.viewLoading).setVisibility(View.VISIBLE);
    }

    @Override
    public void finishProgress() {
        view.findViewById(R.id.show).setVisibility(View.GONE);
        view.findViewById(R.id.viewLoading).setVisibility(View.GONE);
    }

    @Override
    public void showSucesso() {
        view.findViewById(R.id.viewSucesso).setVisibility(View.VISIBLE);
        view.findViewById(R.id.show).setVisibility(View.VISIBLE);
    }

    @Override
    public void finishSucesso() {
        view.findViewById(R.id.viewSucesso).setVisibility(View.GONE);
        view.findViewById(R.id.show).setVisibility(View.GONE);
    }

    @Override
    public void ErroLoading() {
        view.findViewById(R.id.show).setVisibility(View.VISIBLE);
        view.findViewById(R.id.viewErroLoading).setVisibility(View.VISIBLE);
    }

    private ConstraintLayout getFormLayoutContainer() {
        if (constraintLayout == null) {
            constraintLayout = (ConstraintLayout) view.findViewById(R.id.formLayoutContainer);
        }
        return constraintLayout;
    }

    private void addField(Cells cell) {
        CellDinamico cellDinamico = null;

        switch (cell.getType()) {
            case Constants.TYPE_FIELD:
                cellDinamico = new EditTextDin(getContext());
                break;
            case Constants.TYPE_TEXT:
                cellDinamico = new TextViewDin(getContext());
                break;
            case Constants.TYPE_CHECKBOX:
                cellDinamico = new CheckBoxDin(getContext(), new ExibirEmail());
                break;
            case Constants.TYPE_SEND:
                cellDinamico = new ButtonDin(getContext(), new OnClickButton());
                break;
        }

        if (cellDinamico != null) {
            CellGeradorDin generator = new CellGeradorDin(cellDinamico);
            View view = generator.createCell(cell);
            getFormLayoutContainer().addView(view);
        }
    }

    private class ExibirEmail implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (isChecked) {
                setvisivilEdit(View.VISIBLE);
            } else {
                setvisivilEdit(View.GONE);
            }
        }
    }

    private void setvisivilEdit(int visibility) {
        int childCount = getFormLayoutContainer().getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = getFormLayoutContainer().getChildAt(i);

            if (view instanceof EditText) {
                EditText editText = (EditText) view;

                if (editText.getInputType() == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) {
                    editText.setVisibility(visibility);
                }
            }
        }
    }

    private class OnClickButton implements View.OnClickListener {
        @Override
        public void onClick(final View view) {

            if (Validador.validadeForm(getViews())) {
                showSucesso();
                clear();
            }
        }
    }

    public void clear() {
        List<EditText> textList = getViews();

        for (EditText editText : textList) {
            editText.setText("");
        }
    }

    private List<EditText> getViews() {
        int childCount = getFormLayoutContainer().getChildCount();
        List<EditText> editTexts = new ArrayList<>();

        for (int i = 0; i < childCount; i++) {
            View v = getFormLayoutContainer().getChildAt(i);

            if (v instanceof EditText) {
                editTexts.add((EditText) v);
            }

        }
        return editTexts;
    }
}