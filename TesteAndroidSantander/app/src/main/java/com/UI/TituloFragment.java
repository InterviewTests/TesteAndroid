package com.UI;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cerqueira.mellina.testeandroidsantander.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class TituloFragment extends Fragment {

    private TextView txtTituloFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_titulo, container, false);

        txtTituloFragment = view.findViewById(R.id.txtTituloFragment);
        return view;
    }

    public void setTitleFragment(String text) {
        if (text != null) {
            txtTituloFragment.setText(text);
        }
    }
}
