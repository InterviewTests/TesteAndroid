package br.banco.services.datasource.local.contact;


/**
 *
 *  gerencia os campos dos forms
 *  cria / apaga / retorna /checa se foi criada
 *
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.banco.services.app.utils.ReactAplication;
import br.banco.services.contact.data.IContact;

public class FormPreferences implements IContact.IFileTask {

    //private static String TAG = PreferencesContact.class.getSimpleName();
    private static String TAG = "PREF";
    ReactAplication RX = new ReactAplication();

    private SharedPreferences pref;
    private Editor editor;
    private Context context;

    private HashMap<String, String> mapForm;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME 		= "contact_form_text";
    private static final String PREF_FOLDER 	= "shared_prfs";
    private static final String KEY_START 		= "sp_start";

    private static final String KEY_FULL_NAME 	= "sp_full_name";
    private static final String KEY_EMAIL 		= "sp_email";
    private static final String KEY_PHONE 		= "sp_phone";
    private static final String KEY_EMAIL_SAVE 	= "sp_email_save";

    private boolean SPStart;
    private String SPFullName;
    private String SPEmail;
    private String SPPhone;
    private int SPEmailSave;


    /**
     *
     *  cria / confere se a pref foi iniciada
     *
     */

    public FormPreferences(Context c) {
        this.context = c;
        onSave(KEY_START, PREF_FOLDER, PREF_NAME, context);


    }



    /**
     * checar se pref foi criada
     *
     */


    public boolean isSPStart() {
        SPStart = pref.getBoolean(KEY_START, false);
        return SPStart;
    }

    public void setSPStart(boolean SPStart) {
        editor.putBoolean(KEY_START, SPStart);
        editor.commit();
        this.SPStart = SPStart;

    }


    /**
     * campos da pref
     *
     */



    public String getSPFullName() {
        SPFullName = pref.getString(KEY_FULL_NAME, null);
        return SPFullName;
    }
    @Nullable
    public void setSPFullName(String SPFullName) {
        editor.putString(KEY_FULL_NAME, SPFullName);
        editor.commit();
        this.SPFullName = SPFullName;
    }




    public String getSPEmail() {
        SPEmail = pref.getString(KEY_EMAIL, null);
        return SPEmail;
    }
    @Nullable
    public void setSPEmail(String SPEmail) {
        editor.putString(KEY_EMAIL, SPEmail);
        editor.commit();
        this.SPEmail = SPEmail;
    }



    public String getSPPhone() {
        SPPhone = pref.getString(KEY_PHONE, null);
        return SPPhone;
    }
    @Nullable
    public void setSPPhone(String SPPhone) {
        editor.putString(KEY_PHONE, SPPhone);
        editor.commit();
        this.SPPhone = SPPhone;
    }




    public int getSPEmailSave() {
        SPEmailSave = pref.getInt(KEY_EMAIL_SAVE, 0);
        return SPEmailSave;
    }
    @Nullable
    public void setSPEmailSave(int SPEmailSave) {
        editor.putInt(KEY_EMAIL_SAVE, SPEmailSave);
        editor.commit();
        this.SPEmailSave = SPEmailSave;
    }


    /**
     * onLoad / onSave / onRead / onClear
     *
     */


    public boolean onEdit(HashMap<String, String> saveMap,Context c){
        boolean save = false;

        Log.d(TAG, "CONTEXTO = " + (c!=null));

        if(c != null) {
            pref = c.getSharedPreferences(PREF_NAME, 0); //, Context.MODE_PRIVATE
            editor = pref.edit();




            mapForm = saveMap;

                try {


                    for (String map : mapForm.keySet()) {
                        editor.putString(map, mapForm.get(map));
                        editor.apply();
                       // Log.e("LOADR", "KEY: " + map + " - VAL: " + mapForm.get(map));
                    }

                } catch (Exception e) {
                    Log.d(TAG, "Erro ao salvar arquivo!" + (e));
                }


        }

    return  save;
    }


    @Nullable
    public  String onLoad(Context c, String folder) {
        String load = null;

        // SPStart = pref.getBoolean(KEY_START, false);
        //return (pref.contains(KEY_START));
        //RX.onNext("" + (SPStart) );
        return load;
    }

    @Nullable
    public boolean onSave(String contentStr, String localDir, String fileName, Context c){
        boolean save = false;

        try {
            pref = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            editor = pref.edit();

            if(!isSPStart()){

                setSPStart(true);

                RX.onNext("Criando pref..." );

            }else{
                RX.onNext("Preferences ja foi criada." );
            }

        } catch (Exception e) {
            RX.onError(e);
        }
        return  save;
    }

    @Nullable
    public String onRead(String localDir, String fileName,  Context c){
        String read = null;

        read = "DADOS{" +
                "SPFullName='" + getSPFullName() + '\'' +
                ", SPEmail='" + getSPEmail() + '\'' +
                ", SPPhoneNum='" + getSPPhone()+ '\'' +
                ", SPEmailSave=" + getSPEmailSave() +
                '}';


        return  read;
    }

    @Nullable
    public boolean onClear(String dirName, String fileName, Context c){

        boolean clear = false;

        try {
            pref = c.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = pref.edit();
            editor.clear();
            //editor.commit();
            //RX.onNext("deleteSP->" + isSPStart());
            editor.apply();
        } catch (Exception e) {
            RX.onError(e);
        }

        return  clear;
    }


    public String loadData(Context a) {
        // this.b = a;
        return "";
    }



    /**
     *
     *  TESTE CASE
     *
     */



    /**
     *
     *  Exibir os dados de carregamento de prefs
     *
     */


    @Override
    public String toString() {
        return "DADOS{" +
                "SPFullName='" + SPFullName + '\'' +
                ", SPEmail='" + SPEmail + '\'' +
                ", SPPhoneNum='" + SPPhone+ '\'' +
                ", SPEmailSave=" + SPEmailSave +
                '}';
    }


    public String toStringSeters(){

        String DADOS_USER = "Dados de Preferncias "  ;

        DADOS_USER += "getSPFullName: " + this.getSPFullName();
        DADOS_USER += "getSPEmail: " + this.getSPEmail();
        DADOS_USER += "getSPPhone: " + this.getSPPhone();
        DADOS_USER += "getSPEmailSaveL: " + this.getSPEmailSave();

        return DADOS_USER;

    }



    public void testCaseKeysValue(Map saveMap){


        for(int i=0;i<30;i++) {
            saveMap.put("chave_"+i, "valor_" + i);
        }

    }





}
