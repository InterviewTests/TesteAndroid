package com.santander.wesleyalves.santandercode;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.santander.wesleyalves.santandercode._utils.FontUtils;

public class CadastroUsuarioFragment extends Fragment {

    private View root;
    private TextView txt_saudacao;
    private EditText tfield_nome_completo;
    private EditText tfield_email;
    private EditText tfield_telefone;
    private CheckBox ckb_cadastrar_email;
    private Button btn_enviar;

    public static CadastroUsuarioFragment newInstance() {
        return new CadastroUsuarioFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_cadastro_usuario, container, false);
        setHasOptionsMenu(true);

        DefinirObjetosLayout();
        DefinirFontes();

        return root;
    }

    private void DefinirFontes() {
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

    private void DefinirObjetosLayout() {
        txt_saudacao = root.findViewById(R.id.txt_saudacao);
        tfield_nome_completo = root.findViewById(R.id.tfield_nome_completo);
        tfield_email = root.findViewById(R.id.tfield_email);
        tfield_telefone = root.findViewById(R.id.tfield_telefone);
        ckb_cadastrar_email = root.findViewById(R.id.ckb_cadastrar_email);
        btn_enviar = root.findViewById(R.id.btn_enviar);
    }
}
