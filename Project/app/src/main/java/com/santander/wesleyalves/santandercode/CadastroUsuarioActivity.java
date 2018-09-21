package com.santander.wesleyalves.santandercode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.santander.wesleyalves.santandercode._utils.ActivityUtils;

public class CadastroUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        CadastroUsuarioFragment cadastroUsuarioFragment =
                (CadastroUsuarioFragment) getSupportFragmentManager().findFragmentById(R.id.cadastro_usuario_fragment);
        if (cadastroUsuarioFragment == null) {
            // Create the fragment
            cadastroUsuarioFragment = CadastroUsuarioFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), cadastroUsuarioFragment, R.id.cadastro_usuario_fragment);
        }
    }
}
