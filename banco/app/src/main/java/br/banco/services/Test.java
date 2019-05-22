 package br.banco.services;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.banco.services.app.utils.Version;
import br.banco.services.fund.data.template.FromScreen;
import br.banco.services.fund.interactor.LoadScreen;

public class Test extends AppCompatActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.teste);

        TextView tvTest1;
        tvTest1 = (TextView) findViewById(R.id.tvTest);

        String TestClass = "USER CASE / FromScreen";
        FromScreen dadosTeste =  new FromScreen();

        tvTest1.setText(dadosTeste.getStringTest());

    }


}
