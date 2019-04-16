package br.com.ricardo.testeandroid.presenter;

import br.com.ricardo.testeandroid.view.ContactView;

public interface ContactPresenter {

    void attachView(ContactView view);
    void detachView();
    void requestContactsField();

}
