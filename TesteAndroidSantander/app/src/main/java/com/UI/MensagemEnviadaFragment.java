package com.UI;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cerqueira.mellina.testeandroidsantander.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MensagemEnviadaFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_mensagem_enviada, container, false);
    }

}
