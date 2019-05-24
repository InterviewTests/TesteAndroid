package br.banco.services.contact.data.local;

/**
 *  Prefixo SP = shared pref.
 *  Prefixo DB = database
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesContact {

	//private static String TAG = PreferencesContact.class.getSimpleName();
	private static String TAG = "PREF";


	SharedPreferences pref;
	Editor editor;
	Context _context;
	int PRIVATE_MODE = 0;
	private static final String PREF_NAME = "contact_form_design";
	private static final String KEY_FORM_SAVE = "form_save";

	private static final String KEY_FULL_NAME 	= "sp_full_name";
	private static final String KEY_EMAIL 		= "sp_email";
	private static final String KEY_PHONE 		= "sp_phone";
	private static final String KEY_EMAIL_SAVE 	= "sp_email_save";

	private String SPFullName;
	private String SPEmail;
	private Long SPPhone;
	private int SPEmailSave;


	public String getSPFullName() {
		return SPFullName;
	}

	public void setSPFullName(String SPFullName) {
		this.SPFullName = SPFullName;
		editor.putString(KEY_FULL_NAME, SPFullName);
		editor.commit();
	}

	public String getSPEmail() {
		return SPEmail;
	}

	public void setSPEmail(String SPEmail) {
		this.SPEmail = SPEmail;
		editor.putString(KEY_EMAIL, SPEmail);
		editor.commit();
	}

	public Long getSPPhone() {
		return SPPhone;
	}

	public void setSPPhone(Long SPPhone) {
		this.SPPhone = SPPhone;
		editor.putLong(KEY_EMAIL, SPPhone);
		editor.commit();
	}

	public int getSPEmailSave() {
		return SPEmailSave;
	}

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


	public PreferencesContact(Context context) {

		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
		////Log.d(TAG, "Iniciando sessao usuario.");
		//Log.d(TAG, "SESSAO:" + PREF_NAME + " -- NOME: " + PRIVATE_MODE);

	}

	public void setLogin(boolean isLoggedIn) {

		//Log.d(TAG, "Alterando sessao usuario.");
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
}
