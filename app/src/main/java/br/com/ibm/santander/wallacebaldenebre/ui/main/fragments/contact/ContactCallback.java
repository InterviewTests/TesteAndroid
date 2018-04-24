package br.com.ibm.santander.wallacebaldenebre.ui.main.fragments.contact;

public interface ContactCallback<T> {
    void onSuccess(T data);

    void onFailure(int errorCode, String reason);
}
