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
    @Override
    public void desenhaTela(Formulario formulario) {

        progressBar.setVisibility(View.GONE);

        Typeface typefaceTxt = ResourcesCompat.getFont(getContext(), R.font.din_pro_medium);
        Typeface typefaceBox = ResourcesCompat.getFont(getContext(), R.font.din_pro_regular);

        // Titulo
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        lp.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        int top = DpToPixels.convertToPixels(43, getContext());
        lp.setMargins(0, top, 0, 0);

        TextView txtContato = new TextView(getContext());
        txtContato.setId(View.generateViewId());
        txtContato.setText("Contato");
        txtContato.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        txtContato.setTypeface(typefaceTxt);
        txtContato.setLayoutParams(lp);
        layoutFormulario.addView(txtContato);

        // Consumindo JSON
        int width = DpToPixels.convertToPixels(300, getContext());
        int height = DpToPixels.convertToPixels(47, getContext());
        int clearSize = DpToPixels.convertToPixels(30, getContext());
        top = 0;

        View lastView = txtContato;

        for (Cell cell : formulario.getCells()) {

            lp = new ConstraintLayout.LayoutParams(width, height);
            lp.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            lp.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
            lp.topToTop = lastView.getBottom();
            top += DpToPixels.convertToPixels((int)cell.getTopSpacing() + 47, getContext());
            lp.setMargins(8, top, 8, 8);

            if (cell.getType() == Type.field.getTipo()) {
               

                EditText editText = new EditText(getContext());
                editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                editText.setTypeface(typefaceTxt);
                editText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

                if (cell.getTypefield().equals(String.valueOf(TypeField.telnumber.getTipo()))
                        || cell.getTypefield().equals(TypeField.telnumber.name())) {
                    
                    editText.setInputType(InputType.TYPE_CLASS_PHONE);
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
                    editText.addTextChangedListener(MaskEditUtil.insert(editText));

                } else if (cell.getTypefield().equals(String.valueOf(TypeField.email.getTipo()))
                        || cell.getTypefield().equals(TypeField.email.name())) {
                    
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    editText.setVisibility(View.GONE);
                    editText.addTextChangedListener(MailUtil.insert(editText));

                } else {
                    
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                    editText.getBackground().clearColorFilter();
                }

                editText.setId(cell.getId());
                editText.setHint(cell.getMessage());

                editText.setLayoutParams(lp);

                layoutFormulario.addView(editText);

                lastView = editText;

//                // TODO - Label
//                ConstraintLayout.LayoutParams lpLabel = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
//                lpLabel.startToStart = editText.getLeft();
//                lpLabel.topToTop = editText.getTop();
//                lpLabel.bottomToBottom = editText.getBottom();
//                lpLabel.setMargins(2, 0, 0, 0);
//
//                TextView label = new TextView(getContext());
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
//                ImageButton clear = new ImageButton(getContext());
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
                   
                    TextView txtView = new TextView(getContext());
                    txtView.setText(cell.getMessage());
                    txtView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                    txtView.setTypeface(typefaceTxt);
                    view = txtView;

                } else if (cell.getType() == Type.image.getTipo()) {
                   
                    ImageView imageView = new ImageView(getContext());
                    view = imageView;

                } else if (cell.getType() == Type.checkbox.getTipo()) {
                 
                    CheckBox checkBox = new CheckBox(getContext());
                    checkBox.setText(" " + cell.getMessage());
                    checkBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                    checkBox.setTypeface(typefaceBox);
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
                   
                    Button btn = new Button(getContext());
                    btn.setText(cell.getMessage());
                    btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                    btn.setTypeface(typefaceTxt);
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

                            btn_nova.setOnClickListener((v)->{
                                nome.setText("");
                                fone.setText("");
                                mail.setText("");
                                layoutEnvio.setVisibility(View.GONE);
                                layoutFormulario.setVisibility(View.VISIBLE);
                            });


                        }


                    });
                    view = btn;
                } else {

                }

                // view.setId(View.generateViewId());
                view.setId(cell.getId());
                view.setLayoutParams(lp);

                layoutFormulario.addView(view);
                lastView = view;
            }


        }


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
