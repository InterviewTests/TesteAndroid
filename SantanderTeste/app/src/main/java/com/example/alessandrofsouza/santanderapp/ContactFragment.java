package com.example.alessandrofsouza.santanderapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContactFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.contact, container, false);
        Typeface dinRegular = ResourcesCompat.getFont(getActivity(), R.font.dinpro_regular);

        TextView tv = view.findViewById(R.id.textContact);
        tv.setText(R.string.contato);
        tv.setTypeface(dinRegular);

        return view;
    }

}
