package br.com.tisoares.app.testeandroid.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.tisoares.app.testeandroid.R;

public class SucessoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucesso);
        setTitle(R.string.ttl_contato);

        findViewById(R.id.btn_sus_contato).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreContato();
            }
        });

        findViewById(R.id.btn_sus_investimento).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFundos();
            }
        });

        findViewById(R.id.btn_enviar_msg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreContato();
            }
        });

    }

    private void abreFundos() {
        Intent f = new Intent(this, FundosActivity.class);
        startActivity(f);
    }

    private void abreContato() {
        Intent c = new Intent(this, FormActivity.class);
        startActivity(c);
    }
}
