package com.bruno.santander.santanderchallenge.contato;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bruno.santander.santanderchallenge.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContatoFragment extends Fragment {

    public ContatoFragment() {
        // Required empty public constructor
    }

    public static ContatoFragment newInstance(){
        return new ContatoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contato, container, false);
    }

}
