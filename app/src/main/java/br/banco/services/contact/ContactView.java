 /*

 @FAZER: usar Stragy Patern
 FLUXO:

 */

package br.banco.services.contact;

 import android.app.Activity;
 import android.content.Context;
 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.graphics.Color;
 import android.os.Build;
 import android.os.Bundle;
 import android.support.constraint.ConstraintLayout;
 import android.support.v7.app.AppCompatActivity;
 import android.text.Editable;
 import android.text.TextWatcher;
 import android.util.Log;
 import android.view.KeyEvent;
 import android.view.View;
 import android.view.Window;
 import android.view.WindowManager;
 import android.widget.Button;
 import android.widget.CheckBox;
 import android.widget.CompoundButton;
 import android.widget.ProgressBar;
 import android.widget.EditText;
 import android.widget.TextView;

 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;

 import br.banco.services.R;
 import br.banco.services.app.utils.CharacterCheck;
 import br.banco.services.app.utils.ValidatorEmail;
 import br.banco.services.app.utils.ValidatorName;
 import br.banco.services.app.utils.ValidatorPhone;

 import br.banco.services.contact.message.ContactMsgView;
 import br.banco.services.datasource.local.contact.FormPreferences;


 public class ContactView extends AppCompatActivity implements
                                                IContactImpl.ViewImpl,
                                                 View.OnClickListener
 {

 public String TAG = "CONTACT";
 public int option = 0;
 public Context context;
 public  FormPreferences formPreferences;

     private Contact contactTest;
 public ArrayList<String> listFormItens;
 private ArrayList<Contact> listFormContact ;

 private static IContactImpl.PresenterImpl presenter;

 public ConstraintLayout constraintLayout;


 public EditText vFullName ;
 public EditText vEmail;
 public EditText vPhone;
 public CheckBox vEmailSave;
 public Button vBtSend;
 private ProgressBar progressBar;

 private boolean isValidName = false;
 private boolean isValidEmail  = false;
 private boolean isValidPhone  = false;

 private Intent intentSendData;


 @Override
 protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_contact_form);

     this.context = getApplicationContext();
     contactTest = new Contact();
     presenter = new ContactPresenter(this);


     //drawStatusBar();
    // drawFormView();
     //drawNavigation();

    // checkPreferences();

   // presenter.loadView(option, getApplicationContext());

   // checkTyping(vFullName);
    //checkTyping(vEmail);
   // checkTyping(vPhone);

 }





     @Override
     public void onResume() {
         super.onResume();

     }



     /**
      *
      *  formatacao do form Strings ou Pref
      *
      */





     public boolean checkPreferences(){
           boolean pref = false;

           formPreferences = new FormPreferences(context);
           //String Nome = formPreferences.getSPFullName();
           SharedPreferences prefs = getSharedPreferences("contact_form_text", MODE_PRIVATE);


           Map<String,?> map = prefs.getAll();


          // Log.e(map.get())
          // List<FormPreferences> list = new ArrayList<FormPreferences>(map.g);




          // vFullName.setText("AAA");
           // vEmail.setText("BBB");
           // vPhone.setText("CCC");
           //vEmailSave.setText("DDD");
           // vEmailSave.setChecked(true);

           //vBtSend.setText("EEE");



           return  pref;

       }



     /**
      *  form design e validacao
      *
      */



    // @Override
     public void drawFormView(){

        vFullName = (EditText) findViewById(R.id.etFullName);
        vEmail = (EditText) findViewById(R.id.etEmail);
        vPhone = (EditText) findViewById(R.id.etPhone);
        vEmailSave =(CheckBox) findViewById(R.id.cbEmailAccept);
        vBtSend = (Button) findViewById(R.id.btSend);

        // int isChecked  = (vEmailSave.isChecked()) ? 1 : 0;

         vEmailSave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                 if (isChecked) {

                    // vEmailSave.setBackgroundColor(Color.RED);
                     //vEmailSave.setButtonDrawable(R.drawable.shape_button_dark);
                 }
                 else
                 {
                     //vEmailSave.setButtonDrawable(R.drawable.shape_button_light);
                   //  vEmailSave.setBackgroundColor(Color.GRAY);
                 }


             }
         });

        vBtSend.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view)
             {

                 checkFormValuesView(view);

                // contactTest.showObjetc(TAG, getClass().getName() + "drawFormView" ,contactTest);
                  //contactTest.showValues(TAG,getClass().getName() + " / sendFormView",listFormItens);

                 if(isValidName && isValidEmail && isValidPhone){

                     presenter.sendForm(contactTest);

                 }else{
                     Log.d(TAG, " / drawFormView -> drawAlert");
                 }

             }
         });
     }

     public void sendFormView2(Contact contactForm){


         ArrayList<Contact> contactData = new ArrayList<Contact>();
         contactData.add(contactForm);

         Intent intent = new Intent(this, ContactSendView.class);
         Bundle bundle = new Bundle();

         bundle.putSerializable("contactData",(Serializable)contactData);
         intent.putExtras(bundle);
         startActivity(intent);

         overridePendingTransition(R.anim.an_sair, R.anim.an_entrar);

         clearFormTextView();
         //finish();

         // contactTest.showObjetc(TAG, getClass().getName() + "drawFormView" ,contactForm);

         Log.d(TAG, " VIEW  / sendFormView2 ->  SUCCESS" );
     }

     public void checkTyping(final TextView view){

         view.addTextChangedListener(new TextWatcher() {

             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {


                 if(!isValidName) {
                     view.setError("");
                 }

                 if(!isValidPhone) {
                     view.setError("");
                 }

                 if(!isValidEmail) {
                     view.setError("");
                 }


                 // String elementId = getResources().getResourceEntryName(view.getId());
                 checkFormValuesView(view); // checkFormValuesView(elementId)

             }

             @Override
             public void afterTextChanged(Editable s) {
                 //Log.d(TAG, "afterTextChanged === " + isValidEmail);
             }
         });

     }

     public void checkFormValuesView(View view){

         ValidatorName ValidatorName = new ValidatorName();
         ValidatorEmail validatorEmail = new ValidatorEmail();
         ValidatorPhone validatorPhone = new ValidatorPhone(); // depends: isValidPhone

         // isChecked = bool

         String testName    = vFullName.getText().toString().trim();
         String testEmail   = vEmail.getText().toString().trim();
         String testPhone   = vPhone.getText().toString().trim();
         int isChecked      = (vEmailSave.isChecked()) ? 1 : 0;
        // boolean isChecked = vEmailSave.isChecked();

         isValidName = ValidatorName.isValidName(testName);
         isValidEmail = validatorEmail.isValidEmail(testEmail);
         isValidPhone = validatorPhone.isPhoneNumeric(testPhone);

         CharacterCheck characterCheck = new CharacterCheck();
         Long phoneNumber = characterCheck.getIsLong(testPhone);
         isValidPhone = (phoneNumber >= 1000000000); //10 digits

         contactTest.setFullName(testName);
         contactTest.setEmail(testEmail);
         contactTest.setPhone(phoneNumber);
         contactTest.setEmailSave(isChecked);




         //contactTest.showObjetc(TAG, getClass().getName() + " / drawFormView" , contactTest);

     }

     public void clearFormTextView(){

        // checar internet antes de limpar o form
         vFullName.setText("");
         vEmail.setText("");
         vPhone.setText("");
         vEmailSave.setChecked(false);

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


     /**
      *
      *   activity navegacao
      *
      */


     @Override
     public void onClick(View view) {

         Intent IT;
         Context context = getApplicationContext();

         switch (view.getId()) {

             case R.id.btAction1:
                 IT = new Intent(this, br.banco.services.fund.option.FundOptionView.class);
                 startActivity(IT);
                 overridePendingTransition(R.anim.an_entrar_esquerda,R.anim.an_sair_esquerda);
                 break;
                 /*
             case R.id.btAction2:
                 IT = new Intent(this, br.banco.services.contact.ContactView.class);
                 startActivity(IT);
                 overridePendingTransition(R.anim.an_entrar_esquerda,R.anim.an_sair_esquerda);
                 break;
                 */

         }

     }

     public void drawNavigation(){
        Button BTAction1 = (Button) findViewById(R.id.btAction1);
        Button BTAction2 = (Button) findViewById(R.id.btAction2);
        BTAction1.setOnClickListener(this);
        BTAction2.setOnClickListener(this);
    }


    public void drawStatusBar(){


            //make translucent statusBar on kitkat devices
            if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
                setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
            }
            if (Build.VERSION.SDK_INT >= 19) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            }
            //make fully Android Transparent Status bar
            if (Build.VERSION.SDK_INT >= 21) {
                setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }

        public static void setWindowFlag(Activity activity, final int bits, boolean on) {
            Window win = activity.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
           // win.setAttributes(winParams);
        }





 }
