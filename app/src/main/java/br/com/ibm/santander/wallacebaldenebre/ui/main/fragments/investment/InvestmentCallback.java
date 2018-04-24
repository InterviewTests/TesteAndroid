package br.com.ibm.santander.wallacebaldenebre.ui.main.fragments.investment;

public interface InvestmentCallback<T> {
    void onSuccess(T data);

    void onFailure(int errorCode, String reason);
}
