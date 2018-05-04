package com.UI;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.cerqueira.mellina.testeandroidsantander.R;

public class MainActivity extends Activity implements BotoesInferioresFragment.OnSetTitleListener{

    private TituloFragment tituloFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);

        tituloFragment = (TituloFragment) getFragmentManager().findFragmentById(R.id.layout_titulo);

        FormularioFragment lp = new FormularioFragment();
        MensagemEnviadaFragment mef = new MensagemEnviadaFragment();
        InvestimentoFragment inf = new InvestimentoFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

       //Só para teste
        //ft.add(R.id.layout_principal, lp, "layout_contato");
        //ft.add(R.id.layout_barra_inferior, bif, "layout_botoes_inferiores");
      //  ft.add(R.id.layout_principal, mef, "layout_mensagem_enviada");
        ft.add(R.id.layout_principal, inf, "layout_investimento");

        ft.commit();


    }

    @Override
    public void onSetTitle(String text) {
        tituloFragment.setTitleFragment(text);
    }


}
