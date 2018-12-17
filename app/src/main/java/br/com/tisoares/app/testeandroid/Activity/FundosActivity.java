package br.com.tisoares.app.testeandroid.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import br.com.tisoares.app.testeandroid.Adapter.DownInfoAdapter;
import br.com.tisoares.app.testeandroid.Adapter.InfoAdapter;
import br.com.tisoares.app.testeandroid.Config.AppConfig;
import br.com.tisoares.app.testeandroid.Helper.RequestFromServer;
import br.com.tisoares.app.testeandroid.Model.Fundo;
import br.com.tisoares.app.testeandroid.R;

public class FundosActivity extends AppCompatActivity {

    private Fundo fundo;
    private TextView txtTitle, txtFundName, txtWhatIs, txtDefinition, txtRiskTitle, txtInfoTitle,
            txtMesFund, txtMesCDI, txtAnoFund, txtAnoCDI, txt12Fund, txt12CDI;
    private SeekBar barRisk;
    private RecyclerView listInfo, listDownInfo;

    private InfoAdapter infoAdapter;
    private DownInfoAdapter downInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundos);

        setTitle(R.string.ttl_investimento);

        carregaCampos();

        findViewById(R.id.btn_sus_contato).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreContato();
            }
        });

        solicitaFundos();
    }

    /**
     * Abre a Activity FormActivity
     */
    private void abreContato() {
        Intent f = new Intent(this, FormActivity.class);
        startActivity(f);
    }

    /**
     * Solicita para o servidor as informasções dos fundos
     */
    private void solicitaFundos() {
        new RequestFromServer(this, AppConfig.SVR_FUNDOS) {
            @Override
            public void onReceive(String response) {
                recebeFundos(response);
            }

            @Override
            public void onError(String erros) {
                Toast.makeText(FundosActivity.this, erros, Toast.LENGTH_LONG).show();
            }
        };
    }

    /**
     * Recebe do servidor dos fundos e trata para criar o objeto Fundos
     * @param response String dos servidor contendo um JSON com as informações dos fundos
     */
    private void recebeFundos(String response) {
        fundo = new Fundo();
        if (fundo.createByJSON(response)){
            carregaDados();
        }else{
            Toast.makeText(FundosActivity.this, R.string.erro_carregar_fundo, Toast.LENGTH_LONG).show();
        }
    }

    private void carregaDados() {
        txtTitle.setText(fundo.getTitle());
        txtFundName.setText(fundo.getFundName());
        txtWhatIs.setText(fundo.getWhatIs());
        txtDefinition.setText(fundo.getDefinition());
        txtRiskTitle.setText(fundo.getRiskTitle());
        txtInfoTitle.setText(fundo.getInfoTitle());
        txtMesFund.setText(fundo.getMoreInfo().getMonth().getFund()+"%");
        txtMesCDI.setText(fundo.getMoreInfo().getMonth().getCDI()+"%");
        txtAnoFund.setText(fundo.getMoreInfo().getYear().getFund()+"%");
        txtAnoCDI.setText(fundo.getMoreInfo().getYear().getCDI()+"%");
        txt12Fund.setText(fundo.getMoreInfo().get_12months().getFund()+"%");
        txt12CDI.setText(fundo.getMoreInfo().get_12months().getCDI()+"%");
        barRisk.setProgress(fundo.getRisk());

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        infoAdapter = new InfoAdapter(fundo.getInfos());
        listInfo.setAdapter(infoAdapter);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        downInfoAdapter = new DownInfoAdapter(fundo.getInfos());
        listDownInfo.setAdapter(downInfoAdapter);
    }

    private void carregaCampos() {
        txtTitle = findViewById(R.id.txt_title);
        txtFundName = findViewById(R.id.txt_fund_name);
        txtWhatIs = findViewById(R.id.txt_what_is);
        txtDefinition = findViewById(R.id.txt_definition);
        txtRiskTitle = findViewById(R.id.txt_risk_title);
        txtInfoTitle = findViewById(R.id.txt_info_title);
        txtMesFund = findViewById(R.id.txt_mes_fund);
        txtMesCDI = findViewById(R.id.txt_mes_cdi);
        txtAnoFund = findViewById(R.id.txt_ano_fund);
        txtAnoCDI = findViewById(R.id.txt_ano_cdi);
        txt12Fund = findViewById(R.id.txt_12_fund);
        txt12CDI = findViewById(R.id.txt_12_cdi);
        barRisk = findViewById(R.id.risk);
        listInfo = findViewById(R.id.list_info);
        listDownInfo = findViewById(R.id.list_down_info);

        //barRisk.setEnabled(false);

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listInfo.setLayoutManager(layoutManager);

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager downLayoutManager = new LinearLayoutManager(this);
        listDownInfo.setLayoutManager(downLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_compartilhar, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
