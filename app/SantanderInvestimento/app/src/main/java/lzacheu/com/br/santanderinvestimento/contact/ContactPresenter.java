package lzacheu.com.br.santanderinvestimento.contact;

import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.util.Log;
import android.view.View;

import java.util.List;

import lzacheu.com.br.santanderinvestimento.data.ContactDataSource;
import lzacheu.com.br.santanderinvestimento.data.ContactRepository;
import lzacheu.com.br.santanderinvestimento.model.contact.InputField;
import lzacheu.com.br.santanderinvestimento.util.ValidatorUtils;
import lzacheu.com.br.santanderinvestimento.widget.CustomEditText;


/**
 * Created by luiszacheu on 6/16/18.
 */

public class ContactPresenter implements ContactContract.Presenter {

    private static final String LOG_TAG = ContactPresenter.class.getSimpleName();
    private ContactContract.View contactView;
    private ContactRepository contactRepository;

    public ContactPresenter(ContactRepository contactRepository, ContactContract.View view) {
        this.contactView = view;
        this.contactRepository = contactRepository;
        view.setPresenter(this);

    }

    @Override
    public void start() {
        contactRepository = new ContactRepository();
    }

    @Override
    public void loadFields() {
        contactRepository.getCells(new ContactDataSource.LoadCellsCallback() {
            @Override
            public void onCellsLoaded(List<InputField> cells) {
                for (InputField inputField : cells) {
                    Log.e(LOG_TAG, "onResponse: " + inputField.toString());
                }
                contactView.renderForm(cells);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void sendMessage() {
        contactView.hideForm();
        contactView.showSendMessageView();
    }

    @Override
    public boolean validFields(List<View> views) {
        boolean existError = false;
        for (View view : views) {
            if (view instanceof TextInputLayout) {
                CustomEditText customEditText = (CustomEditText) ((TextInputLayout) view).getEditText();
                if (customEditText.isRequired() && customEditText.isShown()) {
                    if (ValidatorUtils.validationText(customEditText.getText().toString())) {
                        contactView.hideErrosMessage(((TextInputLayout) view));
                    } else {
                        contactView.showErrorsMessage(((TextInputLayout) view));
                        existError = true;
                    }

                    if (customEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) {
                        if (ValidatorUtils.validationEmail(customEditText.getText().toString())) {
                            contactView.hideErrosMessage((TextInputLayout) view);
                        } else {
                            contactView.showErrorsMessage((TextInputLayout) view);
                        }
                    }
                }
            }
        }
        return existError;
    }

}
