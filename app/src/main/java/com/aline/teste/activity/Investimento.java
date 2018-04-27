package com.aline.teste.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aline.teste.MVP.presenter.PresenterFund;
import com.aline.teste.Models.MoreInfo;
import com.aline.teste.Models.Screen;
import com.aline.teste.R;
import com.aline.teste.adapter.DownInfoAdapter;
import com.aline.teste.adapter.InfoItemAdapter;
import com.aline.teste.eventbus.EventFund;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class Investimento extends AppCompatActivity {

    private Screen screen = null;
    PresenterFund presenterFund = new PresenterFund();
    RecyclerView recyclerViewInfo;
    RecyclerView recyclerViewDown;
    InfoItemAdapter adapterInfo;
    DownInfoAdapter adapterDown;
    TextView btnInvestimento;


    @Subscribe
    public void onEvent(EventFund event) {
        screen = event.getScreenFund();
        updateUi(screen);
        getListInfo();
        getListDown();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investimento);

        Toolbar toolbar = findViewById(R.id.include_toolbar);
        setSupportActionBar(toolbar);
        TextView tituloToolbar = findViewById(R.id.titulo_toolbar);
        tituloToolbar.setText(R.string.btn_investimento);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenterFund.callNetworkFund();

        TextView btnContato = findViewById(R.id.btn_contato);
        btnContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Investimento.this, MainActivity.class));
                finish();
            }
        });

        final ConstraintLayout constraintLayout = findViewById(R.id.constraint_invest);

        TextView btnInvestir = findViewById(R.id.btn_investir);
        btnInvestir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(constraintLayout, R.string.sanck_sem_evento_btn,
                        Snackbar.LENGTH_LONG).show();
            }
        });

    }

    private void updateUi(Screen screen) {
        TextView title = findViewById(R.id.title);
        title.setText(screen.getTitle());

        TextView fundName = findViewById(R.id.fund_name);
        fundName.setText(screen.getFundName());

        TextView whatsIs = findViewById(R.id.whats_is);
        whatsIs.setText(screen.getWhatIs());

        TextView definition = findViewById(R.id.definition);
        definition.setText(screen.getDefinition());

        TextView riskTitle = findViewById(R.id.risk_title);
        riskTitle.setText(screen.getRiskTitle());



        int risk = screen.getRisk();
        switch (risk){
            case 1:
                ImageView riskUm = findViewById(R.id.risk_um);
                riskUm.setPadding(0,16,0,0);
                break;
            case 2 :
                ImageView riskDois = findViewById(R.id.risk_dois);
                riskDois.setPadding(0,16,0,0);
                break;

            case 3:
                ImageView riskTres = findViewById(R.id.risk_tres);
                riskTres.setPadding(0,16,0,0);
                break;

            case 4:
                ImageView riskQuatro = findViewById(R.id.risk_quatro);
                riskQuatro.setPadding(0,0,0,0);

                break;

            case 5:
                ImageView riskCinco = findViewById(R.id.risk_cinco);
                riskCinco.setPadding(0,16,0,0);
                break;
                default:break;
        }


        TextView infoTitle = findViewById(R.id.info_title);
        infoTitle.setText(screen.getInfoTitle());

        MoreInfo moreInfo = screen.getMoreInfo();
        double fundMonth = moreInfo.getMonth().getFund();
        double cdiMonth = moreInfo.getMonth().getCDI();

        TextView monthFund = findViewById(R.id.info_month_fund);
        monthFund.setText(String.valueOf(fundMonth));

        TextView monthCdi = findViewById(R.id.info_month_cdi);
        monthCdi.setText(String.valueOf(cdiMonth));

        double fundYear = moreInfo.getYear().getFund();
        double cdiYear = moreInfo.getYear().getCDI();

        TextView yearFund = findViewById(R.id.info_year_fund);
        yearFund.setText(String.valueOf(fundYear));

        TextView yearCdi = findViewById(R.id.info_year_cdi);
        yearCdi.setText(String.valueOf(cdiYear));

        double fundTwelve = moreInfo.getJsonMember12months().getFund();
        double cdiFund = moreInfo.getJsonMember12months().getCDI();

        TextView twelveYear = findViewById(R.id.info_twelve_fund);
        twelveYear.setText(String.valueOf(fundTwelve));

        TextView twelveCdi = findViewById(R.id.info_twelve_cdi);
        twelveCdi.setText(String.valueOf(cdiFund));

    }

    private void getListInfo() {
        recyclerViewInfo = findViewById(R.id.recycler_info);
        recyclerViewInfo.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Investimento.this,
                LinearLayoutManager.VERTICAL, false);
        recyclerViewInfo.setLayoutManager(layoutManager);
        adapterInfo = new InfoItemAdapter(screen.getInfo(), Investimento.this);
        recyclerViewInfo.setAdapter(adapterInfo);

    }

    private void getListDown() {
        recyclerViewDown = findViewById(R.id.recycler_down);
        recyclerViewDown.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Investimento.this,
                LinearLayoutManager.VERTICAL, false);
        recyclerViewDown.setLayoutManager(layoutManager);
        adapterDown = new DownInfoAdapter(screen.getDownInfo(), Investimento.this);
        recyclerViewDown.setAdapter(adapterDown);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_open, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView btnInvestimento = findViewById(R.id.btn_investimento);
        btnInvestimento.setBackgroundColor(getResources().getColor(R.color.colorBtnCheck));

    }
}
