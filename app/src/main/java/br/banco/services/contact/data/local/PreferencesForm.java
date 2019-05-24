package br.banco.services.contact.data.local;


/**
 *  Prefixo SP = shared pref.
 *  Prefixo DB = database
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.Nullable;
import android.util.Log;

import br.banco.services.app.utils.ReactAplication;

public class PreferencesForm {

	//private static String TAG = PreferencesContact.class.getSimpleName();
	private static String TAG = "PREF";
	ReactAplication RX = new ReactAplication();

	SharedPreferences pref;
	Editor editor;
	Context _context;
	int PRIVATE_MODE = 0;
	private static final String PREF_NAME 		= "contact_form_design";
	private static final String KEY_FORM_SAVE 	= "form_save";

	private static final String KEY_FULL_NAME 	= "sp_full_name";
	private static final String KEY_EMAIL 		= "sp_email";
	private static final String KEY_PHONE 		= "sp_phone";
	private static final String KEY_EMAIL_SAVE 	= "sp_email_save";

	private String SPFullName;
	private String SPEmail;
	private String SPPhone;
	private int SPEmailSave;

	public PreferencesForm(Context context) {

		try {
			this._context = context;
			pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
			editor = pref.edit();
			RX.onNext("");

		} catch (Exception e) {
			//e.printStackTrace();
			RX.onError(e);
		}

	}


	public String getSPFullName() {
		SPFullName = pref.getString(KEY_FULL_NAME, null);
		return SPFullName;
	}
	@Nullable
	public void setSPFullName(String SPFullName) {
		this.SPFullName = SPFullName;
		editor.putString(KEY_FULL_NAME, SPFullName);
		editor.commit();
	}




	public String getSPEmail() {
		SPEmail = pref.getString(KEY_EMAIL, null);
		return SPEmail;
	}
	@Nullable
	public void setSPEmail(String SPEmail) {
		this.SPEmail = SPEmail;
		editor.putString(KEY_EMAIL, SPEmail);
		editor.commit();
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
		this.SPEmailSave = SPEmailSave;
		editor.putInt(KEY_EMAIL, SPEmailSave);
		editor.commit();
	}


	/**
	 *
	 * CHECAR LOGIN
	 *
	 */


	@Nullable
	public void setLogin(boolean isLoggedIn) {
		editor.putBoolean(KEY_FORM_SAVE, isLoggedIn);
		editor.commit();
	}
	public boolean getLogin(){
		//Log.d(TAG, "Checando sessao usuario.");
		return pref.getBoolean(KEY_FORM_SAVE, false);
	}

	public boolean isLoggedIn(){
		//Log.d(TAG, "Checando sessao usuario.");
		return pref.getBoolean(KEY_FORM_SAVE, false);
	}



	@Nullable
	public void clearSharedPreference(Context context) {

		pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
		editor.clear();
		//editor.commit();
		editor.apply();
	}


	/**
	 *
	 *  Testar caregamento de preferencias
	 *
	 */


	@Override
	public String toString() {
		return "PreferencesForm{" +
				"SPFullName='" + SPFullName + '\'' +
				", SPEmail='" + SPEmail + '\'' +
				", SPPhoneNum='" + SPPhone+ '\'' +
				", SPEmailSave=" + SPEmailSave +
				'}';
	}


	public String toStringTeste(){

		String DADOS_USER = "Dados de Preferncias "  ;

		DADOS_USER += "NOME: " + this.getSPFullName();
		DADOS_USER += "EMAIL: " + this.getSPEmail();
		DADOS_USER += "FONE: " + this.getSPPhone();
		DADOS_USER += "SALAR EMAIL: " + this.getSPEmailSave();

		return DADOS_USER;

	}




}
