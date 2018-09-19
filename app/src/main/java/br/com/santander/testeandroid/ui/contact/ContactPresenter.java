package br.com.santander.testeandroid.ui.contact;

public class ContactPresenter {
    private ContactView view;

    public ContactPresenter(ContactView view) {
        this.view = view;
    }

    public void loadScreenInfo() {
        view.prepareToolbar();
    }
}
