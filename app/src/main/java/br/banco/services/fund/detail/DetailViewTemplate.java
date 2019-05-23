package br.banco.services.fund.detail;

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
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import br.banco.services.R;
import br.banco.services.app.utils.ConnectionChek;
import br.banco.services.app.utils.ReactAplication;
import br.banco.services.fund.domain.national.DownInfo;
import br.banco.services.fund.domain.national.Fund;
import br.banco.services.fund.domain.national.Info;
import br.banco.services.fund.domain.national.MoreInfo;
import br.banco.services.fund.domain.national.ScreenFund;
import br.banco.services.fund.domain.national.ScreenFundTemplate;


public class DetailViewTemplate extends AppCompatActivity implements
        IDetail.ViewDT {  //ILoadTask

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


         List<ScreenFundTemplate> listaTemplate = new ArrayList<ScreenFundTemplate>();
         List<Object> listaExport = new ArrayList<Object>();

         ArrayList listScreen = new ArrayList<>();

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
              fund.setDefinition("BOOOOO");


             screen = new ScreenFundTemplate(fund);
/*
     // -------------------- FUND

                     screen.setType(0);
                     screen.setData(1);
                     screen.setTitle(" setTitle Here");
                     screen.setSubtitle(" setSubtitle Here");
                     screen.setSession(" setSession Here");
                     screen.setDescription(" setDescription Here setDescription Here setDescription Here setDescription Here setDescription Here setDescription Here");
                     screen.setChartTitle(" setChartTitle Here");

                     listContentData.add(screen);

      // -------------------- MORE INFO

                    screen = new ScreenFundTemplate(fund);
                    screen.setType(2);
                    screen.setData(1);

                    screen.setTitle(" setTitle Here");
                    screen.setTableTitle(" setSession");
                    screen.setTableValue(" setDescription Here ");
                    screen.setChartTitle(" setChartTitle Here");

                    listContentData.add(screen);


        for(int i= 0; i < 8; i++){

                    screen = new ScreenFundTemplate(screen);
                    screen.setType(2);
                    screen.setData(0);
                    screen.setTableTitle(" setTableTitle " + i);
                    screen.setTableValue(" setTableValue " + i);
                    listContentData.add(screen);

        }

        for(int b= 0; b < 5; b++){

                screen = new ScreenFundTemplate(screen);
                screen.setType(3);
                screen.setData(0);
                screen.setTableTitle(" setTableTitle " + b);
                screen.setAdressLabel(" setAdressLabel " + b);
                listContentData.add(screen);

        }

    */

       // if(listContentData != null) {

            DetailAdapter adapter = new DetailAdapter(listContentData, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

            mRecyclerView.setLayoutManager(linearLayoutManager);
            //mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);

       // }else{
           // presenter.loadAlert(1, context);
       // }
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

}
