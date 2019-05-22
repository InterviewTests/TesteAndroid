 /*

  recebe + carrega + mostra = mensagem
  @FAZER: onResume() checar conexao + abrir home
 */

package br.banco.services.splash;

 import android.content.Intent;
 import android.os.Bundle;
 import android.os.Handler;
 import android.support.v7.app.AppCompatActivity;
 import android.util.Log;
 import android.widget.TextView;

 import br.banco.services.R;
 import br.banco.services.app.utils.ConnectionChek;

 public class SplashView extends AppCompatActivity implements  SplashPresenter.View {

     private String TAG = "SPLASH";
     private SplashPresenter presenter;

     private int messageStart = 0; // Load
     private int messageCode = 4; // Error

     private ConnectionChek internet;
     private Handler handler;

     private Intent intent;
     public TextView mTextView;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         internet = new ConnectionChek();

         if(internet.isNetworkAvailable(getApplicationContext()) == true){
             messageCode = 3; // Sucess
         }else{
             messageCode = 4; // Error
         }

         setContentView(R.layout.activity_splash);

         drawSplashView();
         mTextView = (TextView) findViewById(R.id.tvStartText);

         presenter = new SplashPresenter(this);
       //  Log.d(TAG, " onCreate // messageCode = " + messageCode) ;

     }

     @Override
     public void onResume() {
         super.onResume();
         presenter.loadAlertView(messageStart, getApplicationContext());
         Log.d(TAG, "VIEW / "+getClass().getName()+" / onResume -> ALERT " );

     }

     @Override
     public void updateSplashView(String[] alertValues) {

         mTextView.setText(alertValues[2] );

         // delay
         handler = new Handler();
         handler.postDelayed(new Runnable() {
             @Override
             public void run() {
                  //br.banco.services.app.alert.AlertView.class
                 intent = new Intent(getApplicationContext(),
                         br.banco.services.fund.option.FundOptionView.class );
                 intent.putExtra("messageCode", messageCode);
                 startActivity(intent);
                 finish();

             }
         }, 3000);

         Log.d(TAG, "VIEW / "+getClass().getName()+" / updateSplashView -> AlertView / " + messageCode );

     }

     @Override
     public void drawSplashView(){

         mTextView = (TextView) findViewById(R.id.DT_fd_fundName);
     }




 }
