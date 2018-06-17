package lzacheu.com.br.santanderinvestimento.contact;

import lzacheu.com.br.santanderinvestimento.base.BasePresenter;
import lzacheu.com.br.santanderinvestimento.base.BaseView;

/**
 * Created by luiszacheu on 6/16/18.
 */

public interface ContactContract {

    interface View extends BaseView<Presenter>{
        void showLoading(boolean isVisible);

        void showForm();

        void hideForm();

        void sendForm();

        void showSendMessageView();
    }

    interface Presenter extends BasePresenter {

        void renderForm();

        void sendMessage();
    }
}
