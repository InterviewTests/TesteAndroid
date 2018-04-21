package com.bruno.santander.santanderchallenge.investimento;


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
public class InvestimentoFragment extends Fragment {

    public InvestimentoFragment() {
        // Required empty public constructor
    }

    public static InvestimentoFragment newInstance(){
        return new InvestimentoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_investimento, container, false);
    }

}
