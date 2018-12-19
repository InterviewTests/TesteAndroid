package com.avanade.santander.contato.presentation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.InputType;
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

    private ConstraintLayout layoutFormulario;
    private ConstraintLayout layoutEnvio;
    private ProgressBar progressBar;
    private ConstraintSet constraintSet;

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

    @Override
    public void desenhaTela(Formulario formulario) {

        progressBar.setVisibility(View.GONE);


        // Titulo do Formulário
        TextView txtTitulo = new TextView(getContext());
        txtTitulo.setId(View.generateViewId());
        txtTitulo.setText("Contato");
        txtTitulo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        txtTitulo.setTypeface(FormataFonte.formataProMedium(getContext()));
        layoutFormulario.addView(txtTitulo);

        constraintSet.clone(layoutFormulario);
        constraintSet.connect(txtTitulo.getId(), ConstraintSet.LEFT, layoutFormulario.getId(), ConstraintSet.LEFT);
        constraintSet.connect(txtTitulo.getId(), ConstraintSet.RIGHT, layoutFormulario.getId(), ConstraintSet.RIGHT);
        int top = DpToPixels.convertToPixels(43, getContext());
        constraintSet.connect(
                txtTitulo.getId(), ConstraintSet.TOP,
                layoutFormulario.getId(), ConstraintSet.TOP,
                DpToPixels.convertToPixels(60, getContext())
        );
        constraintSet.applyTo(layoutFormulario);

        int lastId = txtTitulo.getId();

        // -----------------------------------------------------------------------------------------
        // JSON consumido  - Fomulário dinâmico ----------------------------------------------------
        // -----------------------------------------------------------------------------------------

        int width = DpToPixels.convertToPixels(300, getContext());
        int height = DpToPixels.convertToPixels(47, getContext());
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(width, height);

        int topSpacing = DpToPixels.convertToPixels(47, getContext());



        for (Cell cell : formulario.getCells()) {

            if (cell.getType() == Type.field.getTipo()) {

                View view = getEditText(cell, lp);

                view.setId(cell.getId());
                layoutFormulario.addView(view);

                constraintSet.clone(layoutFormulario);
                constraintSet.connect(view.getId(), ConstraintSet.LEFT, layoutFormulario.getId(), ConstraintSet.LEFT);
                constraintSet.connect(view.getId(), ConstraintSet.RIGHT, layoutFormulario.getId(), ConstraintSet.RIGHT);
                constraintSet.connect(view.getId(), ConstraintSet.TOP, lastId, ConstraintSet.BOTTOM, 47);
                constraintSet.applyTo(layoutFormulario);

                lastId = view.getId();
//                // TODO - Label
//                ConstraintLayout.LayoutParams lpLabel = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
//                lpLabel.startToStart = editText.getLeft();
//                lpLabel.topToTop = editText.getTop();
//                lpLabel.bottomToBottom = editText.getBottom();
//                lpLabel.setMargins(2, 0, 0, 0);
//
//                TextView label = new TextView(this);
//                label.setId(4000 + cell.getId());
//                label.setText(cell.getMessage());
//                label.setLayoutParams(lpLabel);
//
//                // TODO - Clear Button
//                ConstraintLayout.LayoutParams lpClear = new ConstraintLayout.LayoutParams(clearSize, clearSize);
//                lpClear.endToEnd = editText.getRight();
//                lpClear.topToTop = editText.getTop();
//                lpClear.bottomToBottom = editText.getBottom();
//                lpClear.setMargins(0, 0, 8, 0);
//
//                ImageButton clear = new ImageButton(this);
//                clear.setId(View.generateViewId());
//                clear.setBackground(getResources().getDrawable(R.drawable.rouded_button_white));
//                clear.setImageResource(android.R.drawable.presence_offline);
//                clear.setOnClickListener(v1 -> editText.setText(""));
//                clear.setLayoutParams(lpClear);


//                layout.addView(label);
//                layout.addView(clear);

            } else {

                View view = new View(getContext());

                if (cell.getType() == Type.text.getTipo()) {
                    // TextView
                    TextView txtView = new TextView(getContext());
                    txtView.setText(cell.getMessage());
                    txtView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                    txtView.setTypeface(FormataFonte.formataProMedium(getContext()));
                    view = txtView;

                } else if (cell.getType() == Type.image.getTipo()) {
                    // ImageView
                    ImageView imageView = new ImageView(getContext());
                    view = imageView;

                } else if (cell.getType() == Type.checkbox.getTipo()) {
                    // Checkbox
                    CheckBox checkBox = new CheckBox(getContext());
                    checkBox.setText(" " + cell.getMessage());
                    checkBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                    checkBox.setTypeface(FormataFonte.formataProRegular(getContext()));
                    checkBox.setChecked(false);
                    checkBox.setButtonDrawable(R.drawable.checkbox);
                    checkBox.setTextColor(Color.DKGRAY);
                    checkBox.setOnClickListener(v -> {
                        EditText editText = layoutFormulario.findViewById(cell.getShow());
                        if (checkBox.isChecked())
                            editText.setVisibility(View.VISIBLE);
                        else
                            editText.setVisibility(View.GONE);
                    });
                    //((CheckBox) view).setTextAppearance(R.style.din_pro);
                    view = checkBox;

                } else if (cell.getType() == Type.send.getTipo()) {
                    // Button
                    Button btn = new Button(getContext());
                    btn.setLayoutParams(lp);
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


                    });
                    view = btn;

                } else {
                    // TODO - Views que ainda podem ser configuradas
                }
                view.setId(cell.getId());
                layoutFormulario.addView(view);

                constraintSet.clone(layoutFormulario);
                constraintSet.connect(view.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
                constraintSet.connect(view.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
                constraintSet.connect(view.getId(), ConstraintSet.TOP, lastId, ConstraintSet.BOTTOM, topSpacing);
                constraintSet.applyTo(layoutFormulario);

                lastId = view.getId();

            }
        }
    }

    public View getEditText(Cell cell, ViewGroup.LayoutParams lp){
        EditText view = new EditText(getContext());
        view.setLayoutParams(lp);
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

    @Override
    public void novaMensagem() {
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
