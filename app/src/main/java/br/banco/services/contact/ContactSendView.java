 /*

  INTENET > INTENT > BUNDLE > EMAIL

 */

package br.banco.services.contact;

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
//import br.banco.services.contact.message.ContactMsgView;
import br.banco.services.contact.message.GMailSenderV2;


 public class ContactSendView extends AppCompatActivity implements  ContactSendPresenter.View {

     public String TAG = "CONTACT";
     private ContactSendPresenter presenter;
     public GMailSenderV2 emailSender ;

     private int messageCode = 0;
     private ConnectionChek internet;
     private Handler handler;

     private Contact contactTest;
     private ArrayList<Contact> listSendMail ;


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

         presenter = new ContactSendPresenter(this);

         drawView();
         initProgressBar();
         showProgressBar();


         if(checkInternet()){

             presenter = new ContactSendPresenter(this);
             presenter.loadAlertView(messageCode, getApplicationContext());

             String keyName = "contactData"; // reutilizavel
             listSendMail = new ArrayList<Contact>();
             //listSendMail.addAll( receiveIntentView(keyName) !=null );
             listSendMail = ( receiveIntentView(keyName) );

             if(listSendMail != null && listSendMail.size() == 1  ){

                 messageCode = 5; // not send
                 presenter.loadAlertView(messageCode, getApplicationContext());

                 String TESTE = "testando";

                new sendEmailTask().execute(listSendMail);

                 Log.d(TAG, " VIEW / "+getClass().getName()+" / listSendMail -> SUCCESS");

             }else{

                 Log.d(TAG, "VIEW / "+getClass().getName()+" / listSendMail -> ERROR");
             }

         }else{

             messageCode = 2; // not connected
             presenter.loadAlertView(messageCode, getApplicationContext());

             Log.d(TAG, "VIEW "+getClass().getName()+" / onCreate -> ERROR " );
         }

         //Log.d(TAG, " / onCreate -> getIntent -> messageCode = " + messageCode) ;

     }



    @Override
    public void onResume() {
        super.onResume();
        if(messageCode == 5) {
            // caso mensagem enviada voltar ao Splash
            // presenter.loadAlertView(messageCode, getApplicationContext());
            Log.d(TAG, "VIEW / "+getClass().getName()+" / onResume -> EXIT " );
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


     private ArrayList<Contact> receiveIntentView(String keyName){

         ArrayList<Contact> listContact = new ArrayList<Contact>();
         listContact = null;

         if(getIntent().getExtras() != null){
             Bundle bundle = getIntent().getExtras();

             if(bundle.getSerializable("contactData") != null){

                 try {

                     listContact = (ArrayList<Contact>) bundle.getSerializable(keyName);

                     //contactTest.showObjetc(TAG, getClass().getName() + " / onCreate", listContact);
                     //contactTest.showValues(TAG, getClass().getName() + " / onCreate", listContact);

                     Log.d(TAG, " VIEW / receiveIntentView ->  SUCCESS");

                 }catch (Exception e){
                     Log.d(TAG, "VIEW / receiveIntentView ->  ERROR = " + e.getMessage() );
                 }
             }
         }

         //listContact = null;
         return listContact;
     }

     public class sendEmailTask extends AsyncTask<ArrayList<Contact>, Void, ResponseMail> {

         protected void onPreExecute() {

             Log.e(TAG,"VIEW / sendEmailTask -> INICIO");
             showProgressBar();

         }

         @Override
         protected ResponseMail doInBackground(ArrayList<Contact>... contactList) {

             try {

                 emailSender = new GMailSenderV2();
                 ArrayList<Contact> resultList = new ArrayList<Contact>();

                 // carregar da string XML
                 //getResources().getString(R.string.adress_email_title);

                 String emailTitle = "TITULO DA MENSAGEM";
                 String emailBody ="CORPO DA MENSAGEM";
                 String emailTo ="busqe.irece@gmail.com";
                 String emailCopy ="adrianosouzai@gmail.com";

                 if(contactList.length > 0) {

                      resultList = contactList[0];

                     if(resultList.get(0) !=null ) {

                         String emailSave = "";
                         emailSave = (resultList.get(0).getEmailSave() == 1) ? "SIM" : "NAO";

                         emailBody = "CORPO DA MENSAGEM: \n\n" +
                                 "NOME: " + resultList.get(0).getFullName()+ "\n" +
                                 "EMAIL: " + resultList.get(0).getEmail() + "\n" +
                                 "PHONE: " + resultList.get(0).getPhone() + "\n" +
                                 "SALVAR EMAIL: " + emailSave + "\n" +
                                 "";
                     }else{
                         emailBody ="Mensagem com vazia!";
                     }

                 }else{
                      emailBody ="Mensagem com erros!";
                 }

                 Log.e(TAG, "VIEW / emailBody = " + emailBody );

                 // converer classe em boolean
                 emailSender.sendMail(emailTitle, emailBody,emailTo,emailCopy);


             } catch (MessagingException e) {
                 Log.e(TAG, "VIEW / sendEmailView -> ERROR = " + e.fillInStackTrace());
                 throw new RuntimeException(e);

             } catch (Exception e) {
                 Log.e(TAG, "VIEW / sendEmailView - ERROR = " + e.fillInStackTrace());

             }

             return  null;
         }

         @Override
         protected void onPostExecute(ResponseMail response) {

             messageCode = 1; // not send
             presenter.loadAlertContactView(messageCode, getApplicationContext());

             Log.e(TAG,"VIEW / sendEmailTask -> FINAL");
             hideProgressBar();
         }

     }

     public class ResponseMail {

         private List<Contact> lista = new ArrayList<Contact>();

         public List<Contact> mLista() {
             return lista;
         }


     }






     @Override
     public void updateAlertView(String[] alertValues) {


         tvArea.setText(alertValues[0]);
         tvTitle.setText(alertValues[1]);
         tvMessage.setText(alertValues[2]);
         btAction.setText(alertValues[3]);

        // String buttonStyle = alertValues[4];

            switch (messageCode){

                case 0:
                    showProgressBar(); // LOAD
                    break;
                case 1:
                    hideProgressBar(); //
                    break;
                case 2:
                    hideProgressBar(); //
                break;
                case 3:
                    hideProgressBar(); //
                    break;
                case 4:
                    hideProgressBar(); //
                    break;
                default:
                    hideProgressBar(); // ERROR
                    break;

            }

         if(alertValues[4].equals("elementInvible")){
             btAction.setBackgroundResource( R.drawable.shape_circle_light);
             btAction.setTextColor(Color.parseColor("#333333"));
         }


         Log.d(TAG," VIEW / updateAlertView -> SUCESS -> alertValues");
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

     @Override
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
