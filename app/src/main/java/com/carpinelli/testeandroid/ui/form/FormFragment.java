package com.carpinelli.testeandroid.ui.form;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carpinelli.testeandroid.R;

public class FormFragment extends Fragment implements MvpForm.View {

    public FormFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form,container,false);

        return view;
    }
}
