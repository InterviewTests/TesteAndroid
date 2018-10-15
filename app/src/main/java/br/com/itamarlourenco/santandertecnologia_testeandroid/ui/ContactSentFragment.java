package br.com.itamarlourenco.santandertecnologia_testeandroid.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.itamarlourenco.santandertecnologia_testeandroid.R;
import br.com.itamarlourenco.santandertecnologia_testeandroid.app.ViewContract;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Funds;
import br.com.itamarlourenco.santandertecnologia_testeandroid.services.Intractors.GetFundsIntractorImpl;
import br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.CustomButton;
import br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.CustomGraphic;
import br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.CustomTextView;

public class ContactSentFragment extends BaseFragment implements View.OnClickListener {


    public static ContactSentFragment newInstance(){
        return new ContactSentFragment();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.sentNewContact).setOnClickListener(this);

    }

    @Override
    protected int idLayoutFragment() {
        return R.layout.contact_fragment;
    }

    @Override
    public void onClick(View v) {
        alterFragment(FormFragment.newInstance());
    }
}
