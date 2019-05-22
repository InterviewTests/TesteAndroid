 /*

  recebe + carrega + mostra = mensagem
  @FAZER: onResume() checar conexao + abrir home
 */

package br.banco.services.app.alert;

 import android.graphics.Color;
 import android.graphics.PorterDuff;
 import android.os.Bundle;
 import android.os.Handler;
 import android.support.v7.app.AppCompatActivity;
 import android.util.DisplayMetrics;
 import android.util.Log;
 import android.view.KeyEvent;
 import android.view.View;
 import android.widget.Button;
 import android.widget.ProgressBar;
 import android.widget.RelativeLayout;
 import android.widget.TextView;

 import br.banco.services.R;
 import br.banco.services.app.utils.ConnectionChek;


 public class AlertView extends AppCompatActivity implements  AlertPresenter.View {

     public String TAG = "ALERTA";
     private AlertPresenter presenter;
     private int messageCode = 0;
     private ConnectionChek internet;


     public TextView tvArea ;
     public TextView tvTitle;
     public TextView tvMessage;
     public Button btAction;

     private ProgressBar progressBar;
     //private Toolbar toolbar;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_alerts);

         initProgressBar();

         if(getIntent().getExtras() != null) {
             messageCode =  getIntent().getExtras().getInt("messageCode");
          }

         drawView();
         presenter = new AlertPresenter(this);

         Log.d(TAG, "VIEW / "+getClass().getName()+" / onCreate -> START " );



     }



     @Override
     public void onResume() {
         super.onResume();

         presenter.loadAlertView(messageCode, getApplicationContext());
         Log.d(TAG, "VIEW / "+getClass().getName()+" / onResume -> START " );
     }


     @Override
     public void updateAlertView(String[] alertValues) {

         tvArea.setText(alertValues[0]);
         tvTitle.setText(alertValues[1]);
         tvMessage.setText(alertValues[2]);
         btAction.setText(alertValues[3]);

             if(alertValues[4].equals("buttonCircleDefault")){
              btAction.setBackgroundResource( R.drawable.shape_circle_light);
              btAction.setTextColor(Color.parseColor("#333333"));
             }


         if(messageCode==2){ // internet error

          btAction.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v)
              {
              showProgressBar();

              Handler handler = new Handler();
              handler.postDelayed(new Runnable() {
                  @Override
                  public void run() {

                      hideProgressBar();
                      if(checkInternet()){

                          //message.what.

                          Log.d(TAG, " VIEW / "+getClass().getName()+" / -> EXIT ") ;
                          finish();
                      }
                  }
              }, 3000);

                  Log.d(TAG, " VIEW / "+getClass().getName()+" / onClick -> checkInternet") ;
              }
          });

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


     @Override
     public void drawView(){

         tvArea = (TextView) findViewById(R.id.lbArea);
         tvTitle = (TextView) findViewById(R.id.DT_fd_title);
         tvMessage = (TextView) findViewById(R.id.DT_fd_fundName);
         btAction = (Button) findViewById(R.id.btAction3);
     }

     @Override
     public void showProgressBar() {
         progressBar.setVisibility(View.VISIBLE);
     }

     @Override
     public void hideProgressBar() {
         progressBar.setVisibility(View.INVISIBLE);
     }

     private void initProgressBar() {

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
                 .setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
         progressBar.setIndeterminate(true);
         progressBar.setBackgroundColor(Color.TRANSPARENT);

         // layout
         RelativeLayout layout = new RelativeLayout(this);

         // params
         RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(160, 160);
         params.setMargins(marginLeft, marginTop, 0, 0);

         this.addContentView(progressBar, params);
         hideProgressBar();
     }


     @Override
     public boolean onKeyDown(int keyCode, KeyEvent event) {
         if (keyCode == KeyEvent.KEYCODE_BACK) {

             //Toast.makeText(getApplicationContext(), "quer fechar ?" , Toast.LENGTH_LONG).show();
             finish();
             overridePendingTransition(R.anim.an_entrar_esquerda, R.anim.an_sair_esquerda);
             return false;
         }
         return super.onKeyDown(keyCode, event);
     }
 }
