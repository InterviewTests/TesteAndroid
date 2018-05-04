package com.UI;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.cerqueira.mellina.testeandroidsantander.R;

public class MainActivity extends Activity implements BotoesInferioresFragment.OnSetTitleListener, BotoesInferioresFragment.OnOpenFragmentListener {

    private TituloFragment tituloFragment;

    InvestimentoFragment inf;
    FormularioFragment lp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  requestWindowFeature(Window.FEATURE_NO_TITLE);

        tituloFragment = (TituloFragment) getFragmentManager().findFragmentById(R.id.layout_titulo);

        inf = new InvestimentoFragment();
        lp = new FormularioFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //ft.add(R.id.layout_principal, lp, "layout_contato");

        ft.add(R.id.layout_principal, inf, "layout_investimento");

        ft.commit();
    }

    @Override
    public void onSetTitle(String text) {
        tituloFragment.setTitleFragment(text);
    }

    @Override
    public void onOpenFragment(String text) {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (text.equals("Investimento")) {
            ft.replace(R.id.layout_principal, inf, "layout_investimento");
            ft.commit();
        }
        if (text.equals("Contato")) {
            ft.replace(R.id.layout_principal, lp, "layout_investimento");
            ft.commit();
        }

    }
}
