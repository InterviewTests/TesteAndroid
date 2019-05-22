package br.banco.services.fund.detail;

/**
 *
 * Sequencia de carregamento:
 *
 *  FUND, CONTACT:
 *  View> Presenter-> Model> Screen> Rules>  dbase[Json / Share / sqlite]
 *  Rules[...] <Model <Presenter[view] <View[...]
 *
 */


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import br.banco.services.R;
import br.banco.services.app.utils.ConnectionChek;
import br.banco.services.app.utils.ReactAplication;

import br.banco.services.fund.domain.national.ScreenFund;
import br.banco.services.fund.domain.national.ScreenFundTemplate;
import br.banco.services.fund.domain.national.Fund;
import br.banco.services.fund.domain.national.DownInfo;
import br.banco.services.fund.domain.national.Info;
import br.banco.services.fund.domain.national.MoreInfo;

import br.banco.services.fund.interactor.ILoadTask;


public class DetailView extends AppCompatActivity implements
                                             IDetail.ViewDT,
                                             View.OnClickListener   {  //ILoadTask

    //  ----- DATA

    private final String TAG ="FUND";
    private static IDetail.PresenterDT presenter;
    private ScreenFund screenFund;
    private ArrayList<DetailModel> listDetail = new ArrayList<>();

    // ----- DESIGN

    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    public  Context context;
    public ReactAplication RX = new ReactAplication();
    private int messageCode = 4; // Error

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_detail_main1);



        if ((savedInstanceState != null) &&
                (savedInstanceState.getSerializable("KEY") != null)) {

              //VEW = (VIWE) savedInstanceState.getSerializable("starttime");
        }

         presenter = new DetailsPresenter(); //01
        ((DetailsPresenter) presenter).setView(this); // presenter.setView(this);

         getContext();
         drawNavigation();

         //initProgressBar();
         //showProgressBar();

         presenter.decideLoadContent(savedInstanceState); //02
         //RX.onNext("presenter: " + (presenter!=null));

        // start
         ArrayList list= new ArrayList();
         drawRecyclerView();
         updateRecyclerView( context,  list);

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       // outState.putParcelableArrayList(KEY_AREA, presenter.getScreen());

    }


    @Override
    public void onResume() {
        super.onResume();

       // checkOnResume();
       // RX.onNext("onResume->" + getClass().getName());

    }

    // RecyclerView

    public void drawRecyclerView(){
      mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    public int checkOptionListView(){

        int contItens = 0;
        if (mRecyclerView.getAdapter() != null) {
            contItens = mRecyclerView.getAdapter().getItemCount();
        }
        return contItens;
    }

    public void updateRecyclerView(Context context, ArrayList listContentData){

        String BD_FUNDO_TITULO = "###";
        try {

            Bundle extras = getIntent().getExtras();

          //  Log.e("TESTE","extras = "+ (extras!=null));

            if (extras != null) {

                if (extras.getString("BD_FUNDO_TITULO") != null) {
                    BD_FUNDO_TITULO = extras.getString("BD_FUNDO_TITULO");
                }else{

                }

            }

        }catch (Exception ex){
            Log.e("TESTE","ERRO Bundle " );
        }


        Log.e("TESTE","BD_FUNDO_TITULO = " + BD_FUNDO_TITULO );

         List<ScreenFundTemplate> listaTemplate = new ArrayList<ScreenFundTemplate>();
         List<Object> listaExport = new ArrayList<Object>();
         ArrayList listScreen = new ArrayList<>();



        /**
         *
         *  1 - Carregar um LIST contendo todos os arrays -> de> listaExport
         *  2 - extrair essas arrays para detro de classes
         *  3  - injetar nas classes do Recicledview
         *
         */



         ArrayList<Fund>     listFund = new ArrayList<Fund>();
         ArrayList<MoreInfo> listMoreingo = new ArrayList<MoreInfo>();
         ArrayList<Info>     listInfo = new ArrayList<Info>();
         ArrayList<DownInfo> listDownInfo = new ArrayList<DownInfo>();

        // carrgar tamanhos de database ou pacote

         int  tScreen = 10; // < - carregar de pacote
         int  tFund = 5;
         int  tMoreingo = 3;
         int  tInfo = 8;
         int  tDownInfo = 4;

             ArrayList<ScreenFundTemplate> list= new ArrayList<ScreenFundTemplate>();

              ScreenFundTemplate screen;
              Fund fund = new Fund();
              fund.setDefinition("title here");




        /**
         *
         *  HEADER, BOODY, FOOTER, outros conteudos...
         *
          */



        // -------------------- FUNDO

              String TituloFundo ="Vinci Valorem FI Multimercado";
              if(BD_FUNDO_TITULO.length() > 2){
                 TituloFundo =  BD_FUNDO_TITULO;
              }

                     screen = new ScreenFundTemplate(fund);
                     screen.setType(0);
                     screen.setData(1);
                     screen.setTitle("" + TituloFundo);
                     screen.setSubtitle("O que é?"); //
                     screen.setSession("Fundos de investimento");
                     screen.setDescription("O Fundo tem por objetivo proporcionar aos seus cotistas rentabilidade no longo prazo através de investimentos.");
                     screen.setChartTitle("Grau de risco do investimento");

                     listContentData.add(screen);

      // -------------------- MORE INFO

        /**
         *
         * aqui deve ser um ARRAY contendo  MAPS para criar a tabela do MORE INFO
         *
         */
        screen = new ScreenFundTemplate(fund);
                    screen.setType(1);
                    screen.setData(1);

                    screen.setTitle("No mes");
                    screen.setTableTitle("No ano");
                    screen.setTableValue("12 Meses");

                    screen.setChartTitle("Fundo");
                    screen.setSession("CDI");

                    listContentData.add(screen);

        // --------------------  INFO

        for(int i= 0; i < 8; i++){

                    screen = new ScreenFundTemplate(screen);
                    screen.setType(2);
                    screen.setData(0);
                    screen.setTableTitle("Aplicação inicial " + i);
                    screen.setTableValue("R$ "+(i+1)+".000,00" + i);
                    listContentData.add(screen);

        }

        // -------------------- DOWNLOAD

        for(int b= 0; b < 5; b++){

                    screen = new ScreenFundTemplate(screen);
                    screen.setType(3);
                    screen.setData(0);
                    screen.setTableTitle("Essenciais" + b);
                    screen.setAdressLabel("BAIXAR" + b);
                    listContentData.add(screen);

        }

        // -------------------- FOOTER

                    screen = new ScreenFundTemplate(screen);
                    screen.setType(12);
                    screen.setData(1);
                    screen.setAdressLabel("Investir");
                    screen.setAdressLink("ACTION_CLICK");
                    listContentData.add(screen);



        if(listContentData != null) {

            DetailAdapter adapter = new DetailAdapter(listContentData, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

            mRecyclerView.setLayoutManager(linearLayoutManager);
            //mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);

        }else{
           // presenter.loadAlert(1, context);
        }
    }

    // views

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void initProgressBar() {

        int marginTop = 0;
        int marginLeft = 0;

        // size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        marginTop = (height > 0) ? (height / 2) - 80 : 0 ;
        marginLeft = (width > 0) ? (width / 2) - 80 : 0 ;

        // bar
        progressBar = new ProgressBar(this, null,
                android.R.attr.progressBarStyleSmall);
        progressBar.getIndeterminateDrawable()
                .setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        progressBar.setIndeterminate(true);
        progressBar.setBackgroundColor(Color.TRANSPARENT);

        // layout
        RelativeLayout layout = new RelativeLayout(this);

        // params
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(160, 160);
        params.setMargins(marginLeft, marginTop, 0, 0);

        this.addContentView(progressBar, params);
        hideProgressBar();

       // Log.d(TAG, "VIEW / "+getClass().getName()+" -> initProgressBar" );

    }

    public void showToolbar(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // Log.d(TAG, "VIEW / "+getClass().getName()+" -> showToolbar" );

    }

    public boolean getContext(){
        this.context = getApplicationContext();
       // Log.d(TAG, "VIEW / "+getClass().getName()+" -> getContext" );
        return (context != null);
    }

    // content

    public void updateAlertView( int msgCode, Context context){

        Handler handler;
        messageCode = msgCode;
        // delay
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent;
                intent = new Intent(getApplicationContext(),
                        br.banco.services.app.alert.AlertView.class );
                intent.putExtra("messageCode", messageCode);
                startActivity(intent);

                overridePendingTransition(R.anim.an_sair, R.anim.an_entrar);
                // finish();

            }
        }, 100);

        Log.d(TAG," / updateAlertView -> ERROR -> messageCode  = " +  messageCode);

    }

    public void checkOnResume(){

        if(checkOptionListView() < 1){

            Log.d(TAG, "VIEW/"+getClass().getName()+"/onResume->lOAD:0" );

            if(ConnectionChek.isNetworkAvailable(getApplicationContext()) == true){

                // presenter.drawOptionList();
                //  presenter.decideLoadContent(STATE_KEY); //02

                Log.d(TAG, "VIEW/"+getClass().getName()+"/onResume->DRAW" );

                if(checkOptionListView() < 1){

                    int msgCode = 4;
                    presenter.loadAlert( msgCode,  getApplicationContext());
                }

            }else{
                int msgCode = 2;
                presenter.loadAlert( msgCode,  getApplicationContext());
            }

        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            //Toast.makeText(getApplicationContext(), "quer fechar ?" , Toast.LENGTH_LONG).show();
            finish();
            overridePendingTransition(R.anim.an_sair, R.anim.an_entrar);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View view) {

        Intent IT;
        Context context = getApplicationContext();

        switch (view.getId()) {

            case R.id.btAction1:
                IT = new Intent(this, br.banco.services.fund.option.FundOptionView.class);
                startActivity(IT);
                overridePendingTransition(R.anim.an_entrar_esquerda,R.anim.an_sair_esquerda);
                finish();
                break;


            case R.id.btAction2:
                 IT = new Intent(this, br.banco.services.contact.ContactView.class);
                 startActivity(IT);
                 overridePendingTransition(R.anim.an_entrar_esquerda,R.anim.an_sair_esquerda);
                 finish();
                 break;

        }



    }

    public void drawNavigation(){
        Button BTAction1 = (Button) findViewById(R.id.btAction1);
        Button BTAction2 = (Button) findViewById(R.id.btAction2);
        BTAction1.setOnClickListener(this);
        BTAction2.setOnClickListener(this);
    }

}
