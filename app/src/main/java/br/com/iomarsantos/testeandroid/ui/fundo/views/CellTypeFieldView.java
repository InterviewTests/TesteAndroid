package br.com.iomarsantos.testeandroid.ui.fundo.views;

import android.content.Context;
import android.service.autofill.Validator;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.entity.Cell;
import br.com.iomarsantos.testeandroid.entity.Type;
import br.com.iomarsantos.testeandroid.entity.TypeField;
import br.com.iomarsantos.testeandroid.formatter.PhoneFormat;
import br.com.iomarsantos.testeandroid.ui.fundo.contato.ContatoView;
import br.com.iomarsantos.testeandroid.ui.fundo.views.input.ClassInputType;
import br.com.iomarsantos.testeandroid.ui.fundo.views.input.InputType;
import br.com.iomarsantos.testeandroid.validator.DefaultValidation;
import br.com.iomarsantos.testeandroid.validator.ValidEmail;
import br.com.iomarsantos.testeandroid.validator.ValidPhone;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class CellTypeFieldView implements CellView {

    private EditText editText;
    private View view;
    private Cell cell;

    @BindView(R.id.text_input_layout_cell_type_field)
    TextInputLayout textInputLayoutField;

    @BindView(R.id.image_view_cell_type_field_clear)
    ImageView imageViewClear;

    public CellTypeFieldView(ContatoView contatoView, Cell cell) {
        this.view = LayoutInflater.from(contatoView.getActivity()).inflate(R.layout.view_cell_type_field, null);
        this.cell = cell;
        ButterKnife.bind(this, view);
        this.editText = this.textInputLayoutField.getEditText();
        this.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0){
                    imageViewClear.setVisibility(View.VISIBLE);
                }else {
                    imageViewClear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        configuraView(cell);
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    public void configuraView(Cell cell) {
        this.textInputLayoutField.setHint(cell.getMessage());
        configureInputType(cell);
        configureVisibility(cell);
    }

    private void configureInputType(Cell cell) {
        if (cell.getTypefield() != null && !"".equals(cell.getTypefield())) {
            ClassInputType classInputType = new InputType().get(cell);
            if (classInputType != null) {
                int input = classInputType.getInputType();
                this.editText.setInputType(input);
            }
        }
    }

    private void configureVisibility(Cell cell) {
        if (cell.getHidden()){
            this.view.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.image_view_cell_type_field_clear)
    public void clearText() {
        this.editText.setText("");
    }


}
