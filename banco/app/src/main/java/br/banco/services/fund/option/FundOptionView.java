package br.banco.services.fund.option;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.banco.services.R;
import br.banco.services.app.utils.ConnectionChek;
import br.banco.services.contact.ContactView;
import br.banco.services.fund.FundModelTemplate;

public class FundOptionView extends AppCompatActivity  implements
                                                        IFundOption.ViewImpl,
                                                        View.OnClickListener  {

    private final String TAG ="FUND";
    private FundOptionPresenter presenter;
    private int messageCode = 4; // Error

    private List<FundOption> fundList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FundOptionAdapter mAdapter;
    private FundModelTemplate template2;

    private ConnectionChek internet;
    //private ProgressBar progressBar;

    //private static IFundDetailption.ViewImpl viewImpl;
    private static  IFundOption.PresenterImpl presenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_list2);

        drawOptionListView();
        drawNavigation();
        presenter = new FundOptionPresenter (this);

        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);


        if(checkInternet()){
           presenter.drawOptionList();

        }else{
           int msgCode = 2;
           presenter.loadAlert( msgCode,  getApplicationContext());
        }


    }

    @Override
    public void onResume() {
    super.onResume();

        if(checkOptionListView() < 1){

            Log.d(TAG, "VIEW / "+getClass().getName()+" / onResume -> checkOptionListView = 0" );

            if(checkInternet()){

                presenter.drawOptionList();
                Log.d(TAG, "VIEW / "+getClass().getName()+" / onResume -> drawOptionList" );

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

    public boolean checkInternet(){

        internet = new ConnectionChek();

        if(internet.isNetworkAvailable(getApplicationContext()) == true){
            Log.d(TAG, " VIEW / "+getClass().getName()+" / checkInternet -> SUCESS") ;
            return true;
        }else{
            Log.d(TAG, " VIEW / "+getClass().getName()+" / checkInternet -> ERROR") ;
            return false;
        }

    }

    public void drawOptionListView(){


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new FundOptionAdapter(fundList);
        recyclerView.setHasFixedSize(true);

        // vertical / match_parent
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        // horizontal // wrap_content
        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        //  click
        recyclerView.addOnItemTouchListener(new FundOptionTouchListener(getApplicationContext(),
                recyclerView, new FundOptionTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                     FundOption fund = fundList.get(position);
                     Intent intent = new Intent();
                     String FDFundoTitulo =  fund.getTitle() ;


                //br.banco.services.app.alert.AlertView.class
                    intent = new Intent(getApplicationContext(),
                            br.banco.services.fund.detail.DetailView.class );
                    intent.putExtra("BD_FUNDO_TITULO", "" + FDFundoTitulo);


                    startActivity(intent);
                    overridePendingTransition(R.anim.an_entrar_esquerda, R.anim.an_sair_esquerda);

                // finish();

               // Toast.makeText(getApplicationContext(), fund.getTitle() + " foi clicado!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

                Intent intent2 = new Intent();

                //br.banco.services.app.alert.AlertView.class
                intent2 = new Intent(getApplicationContext(),
                        br.banco.services.contact.ContactView.class );
                //intent.putExtra("messageCode", messageCode);
                startActivity(intent2);
                overridePendingTransition(R.anim.an_sair, R.anim.an_entrar);

            }
        }));

        Log.d(TAG," / drawOptionListView -> SUCESS " );
    }

    public void updateOptionListView(FundModelTemplate template) {

        FundOption fund;
        String[][][] list = null;

        list = template.getListMoreInfoTemplate();

        if(list !=null && list.length > 0) {

            for (int count = 0; count < list.length; count++) {

                template.setNameLabel(count);
                template.setMonthLabel(count);
                template.setTimeLabel(count);

                template.setName(count);
                template.setMonth(count);
                template.setTime(count);

                fund = new FundOption(
                        "" + template.getName(),
                        "" + template.getMonthLabel() + ": " + template.getMonth(),
                        "" + template.getTimeLabel() + ": " + template.getTime());

                fundList.add(fund);

                Log.d(TAG, "VIEW / updateOptionListView -> SUCESS ");
            }


            mAdapter.notifyDataSetChanged();

        }else{

            Log.d(TAG, "VIEW / "+getClass().getName()+" / updateOptionListView -> ERROR" );
        }


    }

    public int checkOptionListView(){

        int contItens = 0;
        if (recyclerView.getAdapter() != null) {
            contItens = recyclerView.getAdapter().getItemCount();
        }
            return contItens;
    }

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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            //Toast.makeText(getApplicationContext(), "quer fechar ?" , Toast.LENGTH_LONG).show();
            finish();
            //overridePendingTransition(R.anim.an_sair, R.anim.an_entrar);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {

        Intent IT;
        Context context = getApplicationContext();

        Button BTAction1 = (Button) findViewById(R.id.btAction1);
        Button BTAction2 = (Button) findViewById(R.id.btAction2);
        BTAction1.setOnClickListener(this);
        BTAction2.setOnClickListener(this);

        switch (view.getId()) {

           /* case R.id.btAction1:
                IT = new Intent(this, br.banco.services.fund.option.FundOptionView.class);
                startActivity(IT);
                overridePendingTransition(R.anim.an_entrar_esquerda,R.anim.an_sair_esquerda);
                break;
                */
            case R.id.btAction2:
                IT = new Intent(this, br.banco.services.contact.ContactView.class);
                startActivity(IT);
                overridePendingTransition(R.anim.an_entrar_esquerda,R.anim.an_sair_esquerda);
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
