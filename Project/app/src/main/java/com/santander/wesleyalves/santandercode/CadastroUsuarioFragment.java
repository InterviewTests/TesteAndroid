package com.santander.wesleyalves.santandercode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.santander.wesleyalves.santandercode._utils.FieldValidator;
import com.santander.wesleyalves.santandercode._utils.FontUtils;
import com.santander.wesleyalves.santandercode.cadastrousuario.domain.model.Usuario;
import com.santander.wesleyalves.santandercode.fundosinvestimento.FundosInvestimentoActivity;
import com.santander.wesleyalves.santandercode.fundosinvestimento.domain.model.FundoInvestimentoResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CadastroUsuarioFragment extends Fragment implements CadastroUsuarioContract.View {

    private View root;
    private TextView txt_saudacao;
    private EditText tfield_nome_completo;
    private EditText tfield_email;
    private EditText tfield_telefone;
    private CheckBox ckb_cadastrar_email;
    private Button btn_enviar;

    private boolean cadastrarEmail;

    private final String NOME_INVALIDO = "Por favor, preencha o nome!";
    private final String EMAIL_INVALIDO = "Por favor, preencha um email válido!";
    private final String TELEFONE_INVALIDO = "Por favor, preencha um telefone válido!";

    private CadastroUsuarioPresenter usuarioCadastroPresenter;


    public static CadastroUsuarioFragment newInstance() {
        return new CadastroUsuarioFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_cadastro_usuario, container, false);
        setHasOptionsMenu(true);

        usuarioCadastroPresenter = new CadastroUsuarioPresenter();

        definirObjetosLayout();
        definirFontes();
        definirListeners();

        return root;
    }

    private void BtnEnviarClick() {
        boolean formularioValido = true;

        if (!FieldValidator.EditTextValidator(tfield_nome_completo, 1, NOME_INVALIDO))
            formularioValido = false;

        if (cadastrarEmail) {
            if (!FieldValidator.EmailTextValidator(tfield_email, EMAIL_INVALIDO))
                formularioValido = false;
        }

        if (!FieldValidator.PhoneValidator(tfield_telefone, TELEFONE_INVALIDO))
            formularioValido = false;

        if (!formularioValido)
            return;

        tfield_nome_completo.setError(null);
        tfield_email.setError(null);
        tfield_telefone.setError(null);

        if (usuarioCadastroPresenter.salvarUsuario(tfield_nome_completo.getText().toString(), tfield_email.getText().toString(), tfield_telefone.getText().toString(), ckb_cadastrar_email.isChecked()))
            exibirTelaSucesso();
    }

    public void exibirTelaSucesso() {
        getActivity().setResult(Activity.RESULT_OK);
        Intent myIntent = new Intent(getActivity(), FundosInvestimentoActivity.class);
        getActivity().startActivity(myIntent);
    }

    public void definirFontes() {
        Typeface custom_font = Typeface.createFromAsset(root.getContext().getAssets(), FontUtils.DINPRO_BOLD);
        txt_saudacao.setTypeface(custom_font);

        custom_font = Typeface.createFromAsset(root.getContext().getAssets(), FontUtils.DINPRO_REGULAR);
        tfield_nome_completo.setTypeface(custom_font);
        ckb_cadastrar_email.setTypeface(custom_font);

        custom_font = Typeface.createFromAsset(root.getContext().getAssets(), FontUtils.DINPRO_MEDIUM);
        tfield_email.setTypeface(custom_font);
        tfield_telefone.setTypeface(custom_font);
        btn_enviar.setTypeface((custom_font));
    }

    public void definirObjetosLayout() {
        txt_saudacao = root.findViewById(R.id.txt_saudacao);
        tfield_nome_completo = root.findViewById(R.id.tfield_nome_completo);
        tfield_email = root.findViewById(R.id.tfield_email);
        tfield_email.setVisibility(View.GONE);
        tfield_telefone = root.findViewById(R.id.tfield_telefone);
        ckb_cadastrar_email = root.findViewById(R.id.ckb_cadastrar_email);
        btn_enviar = root.findViewById(R.id.btn_enviar);
        btn_enviar.setBackgroundResource(R.drawable.button_bg_color);
    }

    public void definirListeners() {
        ckb_cadastrar_email.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cadastrarEmail = isChecked;
                if (cadastrarEmail)
                    tfield_email.setVisibility(View.VISIBLE);
                else
                    tfield_email.setVisibility(View.GONE);
            }
        });

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BtnEnviarClick();
            }
        });

        tfield_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (cadastrarEmail) {
                        if (FieldValidator.EmailTextValidator(tfield_email, EMAIL_INVALIDO))
                            tfield_email.setError(null);
                    }
                }
            }
        });

        tfield_nome_completo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (FieldValidator.EditTextValidator(tfield_nome_completo, 1, NOME_INVALIDO))
                        tfield_email.setError(null);
                }
            }
        });

        tfield_telefone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (FieldValidator.PhoneValidator(tfield_telefone, TELEFONE_INVALIDO))
                        tfield_email.setError(null);
                }
            }
        });
    }
}
