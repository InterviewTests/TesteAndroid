package lzacheu.com.br.santanderinvestimento.contact;

import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lzacheu.com.br.santanderinvestimento.data.ContactDataSource;
import lzacheu.com.br.santanderinvestimento.data.ContactRepository;
import lzacheu.com.br.santanderinvestimento.model.contact.InputField;
import lzacheu.com.br.santanderinvestimento.widget.CustomEditText;

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

    @Override
    public boolean validFields(List<View> views) {
        boolean existError = false;
        for (View view : views){
            if (view instanceof TextInputLayout){
                CustomEditText customEditText = (CustomEditText)((TextInputLayout) view).getEditText();
                if (customEditText.isRequired() && customEditText.isShown()){
                    if (customEditText.getText().toString().isEmpty()){
                        ((TextInputLayout) view).setError("Campo é obrigatório.");
                        existError =true;
                    }else{
                        ((TextInputLayout) view).setError(null);
                    }

                    if (customEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS){
                        Pattern pattern = Patterns.EMAIL_ADDRESS;
                        Matcher matcher = pattern.matcher(customEditText.getText().toString());
                        if (!matcher.matches()){
                            ((TextInputLayout) view).setError("Formato do e-mail incorreto.");
                            existError =true;
                        }
                    }
                }
            }
        }
        return existError;
    }

}
