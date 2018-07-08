package com.meteoro.testeandroid.ui.contact;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meteoro.testeandroid.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContactFragment extends Fragment {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Views
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private Unbinder unbinder;

    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }
}
