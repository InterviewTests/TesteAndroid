package com.UI;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cerqueira.mellina.testeandroidsantander.R;

public class BotoesInferioresFragment extends Fragment {

    private Button btnInvestimento;
    private Button btnContato;

    private OnSetTitleListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_botoes_inferiores, container, false);

        btnInvestimento = (Button) view.findViewById(R.id.btnInvestimento);
        btnContato = (Button) view.findViewById(R.id.btnContato);

        btnInvestimento.setOnClickListener(onClickListenerInvestimento);
        btnContato.setOnClickListener(onClickListenerContato);

        return view;
    }

    private View.OnClickListener onClickListenerInvestimento = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onSetTitle("INVESTIMENTO");
            }
        }
    };

    private View.OnClickListener onClickListenerContato = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onSetTitle("CONTATO");
            }
        }
    };
    
    //O contexto como parametro só é permitido a partir do android 23
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof OnSetTitleListener)) {
            throw new RuntimeException("A activity deve implementar a interface OnSetTitleListener");
        }
        listener = (OnSetTitleListener) activity;

    }

    public interface OnSetTitleListener {
        void onSetTitle(String text);
    }
}
