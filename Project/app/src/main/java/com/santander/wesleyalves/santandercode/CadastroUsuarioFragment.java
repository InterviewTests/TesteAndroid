package com.santander.wesleyalves.santandercode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CadastroUsuarioFragment extends Fragment {

    public static CadastroUsuarioFragment newInstance() {
        return new CadastroUsuarioFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cadastro_usuario, container, false);
        setHasOptionsMenu(true);
        return root;
    }
}
