package android.teste.com.br.testeandroidapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.teste.com.br.testeandroidapp.R;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements ContatoFragment.ContatoSubmitCallback, SuccessFragment.NovaMensagemCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadButtons();

        exibirFragment(ContatoFragment.newInstance(this));
    }

    private void loadButtons() {
        final Button btnInvestimento = (Button) findViewById(R.id.btn_investimento);
        final Button btnContato = (Button) findViewById(R.id.btn_contato);

        btnContato.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.colorPrimaryDark));

        btnInvestimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirFragment(InvestimentoFragment.newInstance());
                btnInvestimento.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.colorPrimaryDark));
                btnContato.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.buttonNormal));
            }
        });

        btnContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirFragment(ContatoFragment.newInstance(MainActivity.this));
                btnInvestimento.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.buttonNormal));
                btnContato.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.colorPrimaryDark));
            }
        });
    }

    /**
     * Exibe um fragment no container
     * @param fragment Fragment a ser exibido
     */
    private void exibirFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onContatoSubmit() {
        exibirFragment(SuccessFragment.newInstance(this));
    }

    @Override
    public void sendNewMessage() {
        exibirFragment(ContatoFragment.newInstance(this));
    }
}
