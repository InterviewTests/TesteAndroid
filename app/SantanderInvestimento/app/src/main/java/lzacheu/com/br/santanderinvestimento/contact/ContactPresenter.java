package lzacheu.com.br.santanderinvestimento.contact;

import android.util.Log;

import java.util.List;

import lzacheu.com.br.santanderinvestimento.data.ContactDataSource;
import lzacheu.com.br.santanderinvestimento.data.ContactRepository;
import lzacheu.com.br.santanderinvestimento.model.contact.InputField;

/**
 * Created by luiszacheu on 6/16/18.
 */

public class ContactPresenter implements ContactContract.Presenter {

    private static final String LOG_TAG = ContactPresenter.class.getSimpleName();
    private ContactContract.View view;
    private ContactRepository contactRepository;

    public ContactPresenter(ContactContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        contactRepository = new ContactRepository();
    }

    @Override
    public void getFields() {
        contactRepository.getCells(new ContactDataSource.LoadCellsCallback() {
            @Override
            public void onCellsLoaded(List<InputField> cells) {
                for (InputField inputField : cells){
                    Log.e(LOG_TAG, "onResponse: " + inputField.toString());
                }
                view.renderForm(cells);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void sendMessage() {
        view.hideForm();
        view.showSendMessageView();
    }

}
