package com.avanade.santander.contato.presentation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.avanade.santander.R;
import com.avanade.santander.contato.domain.model.Cell;
import com.avanade.santander.contato.domain.model.Formulario;
import com.avanade.santander.contato.domain.model.Type;
import com.avanade.santander.contato.domain.model.TypeField;
import com.avanade.santander.util.DpToPixels;
import com.avanade.santander.util.FormataFonte;
import com.avanade.santander.util.MailUtil;
import com.avanade.santander.util.MaskEditUtil;

import java.util.List;

public class ContatoFragment extends Fragment implements ContatoContract.IView {

    private ContatoContract.IPresenter mPresenter;
    private static int LAST_ID;


    private ConstraintLayout layoutFormulario;
    private ConstraintLayout layoutEnvio;
    private ProgressBar progressBar;
    private ConstraintSet constraintSet;
    private Button btn_nova;

    public ContatoFragment() {
        // Requires empty public constructor
    }

    public static ContatoFragment newInstance() {
        return new ContatoFragment();
    }


//------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------//

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        constraintSet = new ConstraintSet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.contato_fragment, container, false);
        layoutFormulario = root.findViewById(R.id.layout_formulario);
        layoutEnvio = root.findViewById(R.id.layout_envio);
        progressBar = root.findViewById(R.id.progress_bar);
        btn_nova = root.findViewById(R.id.btn_nova);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    /**
     * Lifecycle - Fragmente Running
     */

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


//------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------//

    private ConstraintLayout.LayoutParams layoutParams(int LAST_ID) {
        ConstraintLayout.LayoutParams lp;

        int width = DpToPixels.convertToPixels(300, getContext());
        int height = DpToPixels.convertToPixels(47, getContext());
        lp = new ConstraintLayout.LayoutParams(width, height);

        int marginLeft = DpToPixels.convertToPixels(8, getContext());
        int marginTop = DpToPixels.convertToPixels(32, getContext());
        int marginRight = marginLeft;
        int marginBottom = DpToPixels.convertToPixels(8, getContext());
        lp.setMargins(marginLeft, marginTop, marginRight, marginBottom);

        lp.startToStart = ConstraintSet.PARENT_ID;
        lp.endToEnd = ConstraintSet.PARENT_ID;
        lp.topToBottom = LAST_ID;

        return lp;
    }

    @Override
    public void desenhaTela(Formulario formulario) {

        progressBar.setVisibility(View.GONE);

        // Titulo do Formulário
        TextView txtTitulo = new TextView(getContext());
        txtTitulo.setId(View.generateViewId());
        txtTitulo.setText("Contato");
        txtTitulo.setTextColor(Color.BLACK);
        txtTitulo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        txtTitulo.setTypeface(FormataFonte.formataProMedium(getContext()));
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.topToTop = ConstraintSet.PARENT_ID;
        lp.startToStart = ConstraintSet.PARENT_ID;
        lp.endToEnd = ConstraintSet.PARENT_ID;
        lp.setMargins(0, DpToPixels.convertToPixels(32, getContext()), 0, 0 );
        txtTitulo.setLayoutParams(lp);
        layoutFormulario.addView(txtTitulo);

        LAST_ID = txtTitulo.getId();

        // -----------------------------------------------------------------------------------------
        // JSON consumido  - Fomulário dinâmico ----------------------------------------------------
        // -----------------------------------------------------------------------------------------
        View view;

        for (Cell cell : formulario.getCells()) {

            if (cell.getType() == Type.field.getTipo())
                view = getEditText(cell);                   // Adiciona Entrada de Texto na Tela
            else
                view = getCellView(cell);                   // Adiciona outras View na Tela

            view.setId(cell.getId());
            view.setLayoutParams(layoutParams(LAST_ID));

            layoutFormulario.addView(view);

            LAST_ID = view.getId();
        }
    }


    private View getCellView(Cell cell) {

        if (cell.getType() == Type.text.getTipo()) {
            // TextView
            TextView txtView = new TextView(getContext());
            txtView.setText(cell.getMessage());
            txtView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            txtView.setTypeface(FormataFonte.formataProMedium(getContext()));
            return txtView;

        } else if (cell.getType() == Type.image.getTipo()) {
            // ImageView
            ImageView imageView = new ImageView(getContext());
            return imageView;

        } else if (cell.getType() == Type.checkbox.getTipo()) {
            // Checkbox
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(" " + cell.getMessage());
            checkBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            checkBox.setTypeface(FormataFonte.formataProRegular(getContext()));
            checkBox.setChecked(false);
            checkBox.setButtonDrawable(R.drawable.checkbox);
            checkBox.setTextColor(Color.LTGRAY);
            checkBox.setOnClickListener(v -> {
                EditText editText = layoutFormulario.findViewById(cell.getShow());
                if (checkBox.isChecked())
                    editText.setVisibility(View.VISIBLE);
                else
                    editText.setVisibility(View.GONE);
            });

            return checkBox;

        } else if (cell.getType() == Type.send.getTipo()) {
            // Button
            Button btn = new Button(getContext());
            btn.setText(cell.getMessage());
            btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            btn.setTypeface(FormataFonte.formataProRegular(getContext()));
            btn.setBackgroundResource(R.drawable.btn_red_rounded);
            btn.setTextColor(Color.WHITE);
            btn.setOnClickListener((v1) -> {

                EditText nome = (EditText) layoutFormulario.getViewById(2);
                EditText fone = (EditText) layoutFormulario.getViewById(6);
                EditText mail = (EditText) layoutFormulario.getViewById(4);

                if (nome.getText().toString().length() < 3) {
                    Toast.makeText(getContext(), "Preencha o campo nome correntamente", Toast.LENGTH_LONG).show();
                } else if (fone.getText().toString().length() < 14) {
                    Toast.makeText(getContext(), "Preencha o campo telefone corretamente", Toast.LENGTH_LONG).show();
                } else if (!MailUtil.isValid(mail.getText().toString())) {
                    Toast.makeText(getContext(), "Preencha o campo e-mail corretamente", Toast.LENGTH_LONG).show();
                } else {
                    layoutEnvio.setVisibility(View.VISIBLE);
                    layoutFormulario.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    nome.setText("");
                    fone.setText("");
                    mail.setText("");
                }

                btn_nova.setOnClickListener((v) -> {
                    nome.setText("");
                    fone.setText("");
                    mail.setText("");
                    layoutEnvio.setVisibility(View.GONE);
                    layoutFormulario.setVisibility(View.VISIBLE);
                });


            });
            return btn;

        } else {
            // TODO - Views que ainda podem ser configuradas

        }
        return new View(getContext());
    }

    private View getEditText(Cell cell) {
        EditText view = new EditText(getContext());
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        view.setTypeface(FormataFonte.formataProMedium(getContext()));
        view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

        // Cell TELEFONE
        if (cell.getTypefield().equals(TypeField.telnumber.getTipo())
                || cell.getTypefield().equals(TypeField.telnumber.name())) {

            view.setInputType(InputType.TYPE_CLASS_PHONE);
            view.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
            view.addTextChangedListener(MaskEditUtil.insert(view));

            // Cell E-MAIL
        } else if (cell.getTypefield().equals(String.valueOf(TypeField.email.getTipo()))
                || cell.getTypefield().equals(TypeField.email.name())) {

            view.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            view.setVisibility(View.GONE);
            view.addTextChangedListener(MailUtil.insert(view));

            // Cell TEXTO
        } else {
            view.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
            view.getBackground().clearColorFilter();
        }
        view.setHint(cell.getMessage());
        return view;
    }


    @Override
    public void setLoadingIndicator(boolean active) {
        if (active)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingFormularioError() {
        showMessage("Erro ao buscar informações de contato.\nVerifique sua conexão.");
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    public void novaMensagem(View view) {
        // TODO - Verificar o que o ProductOwner vair fazer com a mensagem enviada
        mPresenter.refreshFormulario();
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(ContatoContract.IPresenter presenter) {
        mPresenter = presenter;
    }
}
