 /*

  recebe + carrega + mostra = mensagem
  @FAZER: onResume() checar conexao + abrir home
 */

package br.banco.services.contact.message;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
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

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import br.banco.services.R;
import br.banco.services.app.utils.ConnectionChek;

import br.banco.services.contact.Contact;

 public class ContactMsgView extends AppCompatActivity implements  ContactMsgPresenter.View {

     public String TAG = "CONTACT";
     public GMailSenderV2 emailSender ;
     private ContactMsgPresenter presenter;
     private Contact contactTest;
     private ArrayList<Contact> listSendMail ;


     private int messageCode = 1; // Error // device

     private ConnectionChek internet;
     private Handler handler;

     private Intent intentSender;
     private Intent intentReceiver;

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

         contactTest = new Contact();
         drawView();
         initProgressBar();
         presenter = new ContactMsgPresenter(this);

         listSendMail = new ArrayList<Contact>();
         listSendMail.addAll(receiveIntentView());

         // ---------

         internet = new ConnectionChek();

         if(internet.isNetworkAvailable(getApplicationContext()) == true){
           //  messageCode = 3; // Sucess

             if(listSendMail.size() > 0){

                showProgressBar();

                 String teste ="OK";
                 new sendEmailTask().execute(teste);

                // if( sendEmailView(listSendMail) ){
                     messageCode = 0;
                // }


             }else{
                 messageCode = 1;
                 presenter.loadAlertView(messageCode, getApplicationContext());
             }


         }else{
             //messageCode = 4; // Error
         }





     }

     @Override
     public void onResume() {
         super.onResume();

        // presenter.loadAlertView(messageCode, getApplicationContext());

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


     }

     @Override
     public void drawView(){

         tvArea = (TextView) findViewById(R.id.lbArea);
         tvTitle = (TextView) findViewById(R.id.DT_fd_title);
         tvMessage = (TextView) findViewById(R.id.DT_fd_fundName);
         btAction = (Button) findViewById(R.id.btAction3);
     }


     private ArrayList<Contact> receiveIntentView(){

         ArrayList<Contact> listContact = new ArrayList<Contact>();

         if(getIntent().getExtras() != null){
             Bundle bundle = getIntent().getExtras();

             if(bundle.getSerializable("contactData") != null){

                 try {

                     listContact = (ArrayList<Contact>) bundle.getSerializable("contactData");

                     //contactTest.showObjetc(TAG, getClass().getName() + " / onCreate", listContact);
                      //contactTest.showValues(TAG, getClass().getName() + " / onCreate", listContact);

                     Log.d(TAG, " / ContactMsgView ->  SUCCESS");

                 }catch (Exception e){
                     Log.d(TAG, " / ContactMsgView ->  ERROR = " + e.getMessage() );
                 }
             }
         }

         return listContact;
     }

     private Boolean sendEmailView(ArrayList<Contact> listMail) {

          emailSender = new GMailSenderV2();
          boolean sendSuccess= false;

          String FullName;
          String Email;
          String Phone;
          String EmailSave;

          String EmailCopy;
          String Subject;
          String Body;

         // FullName = listMail.get(0).getFullName() ;
         //Email = listMail.get(0).getEmail();
         //// Phone = listMail.get(0).getPhone().toString();
         // EmailSave = "" + listMail.get(0).getEmailSave();

         //Log.e(TAG, "listMail = " + listMail.get(0).getClass());

         String subject = "assunto do email";
         String body = "corpo do email do " ;


                     try {

                     emailSender.sendMail(
                             "NOVA MENSAGEM DO APP",
                             "NOVA MENSAGEM AQUI NO MEU APP",
                             "busqe.irece@gmail.com",
                             "adrianosouzai@gmail.com");

                     hideProgressBar();

                         Log.e(TAG, " / sendEmailView -> SUCCESS" );
                     } catch (MessagingException e) {
                         Log.e(TAG, " / sendEmailView -> ERROR = " + e.getMessage());
                         throw new RuntimeException(e);
                     } catch (Exception e) {
                         Log.e(TAG, " / sendEmailView - ERROR = " + e.fillInStackTrace());

                     }





       return  sendSuccess;
     }



     public class sendEmailTask extends AsyncTask<String, Void, ResponseMail> {

         protected void onPreExecute() {

             Log.e(TAG,"sendEmailTask -> INICIO");
             showProgressBar();

         }

         @Override
         protected ResponseMail doInBackground(String... strings) {

             try {

                 emailSender = new GMailSenderV2();

                 emailSender.sendMail(
                         "NOVA MSG LEGAL AQUI MEN",
                         "NOVA MENSAGEM AQUI NO MEU APP",
                         "busqe.irece@gmail.com",
                         "adrianosouzai@gmail.com");


                 Log.e(TAG, " / sendEmailView -> SUCCESS");

             } catch (MessagingException e) {
                 Log.e(TAG, " / sendEmailView -> ERROR = " + e.fillInStackTrace());
                 throw new RuntimeException(e);
             } catch (Exception e) {
                 Log.e(TAG, " / sendEmailView - ERROR = " + e.fillInStackTrace());

             }

             Log.e(TAG,"sendEmailTask -> ENVIANDO");

             return  null;
         }

         @Override
         protected void onPostExecute(ResponseMail response) {
             Log.e(TAG,"sendEmailTask -> FINAL");

             hideProgressBar();
         }

     }


     public class ResponseMail {

         private List<Contact> lista = new ArrayList<Contact>();

         public List<Contact> mLista() {
             return lista;
         }


     }




     // sistema

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
     public void showProgressBar() {
         progressBar.setVisibility(View.VISIBLE);
     }

     @Override
     public void hideProgressBar() {
         progressBar.setVisibility(View.INVISIBLE);
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


 }
