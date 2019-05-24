package br.banco.services.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {

	//private static String TAG = SessionManager.class.getSimpleName();
	private static String TAG = "PREF";


	SharedPreferences pref;
	Editor editor;
	Context _context;
	int PRIVATE_MODE = 0;
	private static final String PREF_NAME = "contact_form_design";
	private static final String KEY_IS_LOGGED_IN = "form_save";

	public SessionManager(Context context) {

		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
		////Log.d(TAG, "Iniciando sessao usuario.");
		//Log.d(TAG, "SESSAO:" + PREF_NAME + " -- NOME: " + PRIVATE_MODE);

	}

	public void setLogin(boolean isLoggedIn) {

		//Log.d(TAG, "Alterando sessao usuario.");
		editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
		editor.commit();

	}

	public boolean getLogin(){

		//Log.d(TAG, "Checando sessao usuario.");
		return pref.getBoolean(KEY_IS_LOGGED_IN, false);

	}
	
	public boolean isLoggedIn(){

		//Log.d(TAG, "Checando sessao usuario.");
		return pref.getBoolean(KEY_IS_LOGGED_IN, false);

	}
}
