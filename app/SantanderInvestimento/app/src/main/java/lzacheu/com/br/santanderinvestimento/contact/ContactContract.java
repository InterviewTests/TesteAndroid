package lzacheu.com.br.santanderinvestimento.contact;

import android.view.View;

import java.util.List;

import lzacheu.com.br.santanderinvestimento.base.BasePresenter;
import lzacheu.com.br.santanderinvestimento.base.BaseView;
import lzacheu.com.br.santanderinvestimento.model.contact.InputField;

/**
 * Created by luiszacheu on 6/16/18.
 */

public interface ContactContract {

    interface View extends BaseView<Presenter>{
        void showLoading(boolean isVisible);

        void renderForm(List<InputField> inputFieldList);

        void hideForm();

        void sendForm();

        void showSendMessageView();
    }

    interface Presenter extends BasePresenter {

        void getFields();

        void sendMessage();

        boolean validFields(List<android.view.View> views);
    }
}
