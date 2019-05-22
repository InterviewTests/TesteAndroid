package br.banco.services.app.alert;

// area, titulo, mensagem, botao texto, botao estilo

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.banco.services.R;

public class AlertModel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);


    }

}
