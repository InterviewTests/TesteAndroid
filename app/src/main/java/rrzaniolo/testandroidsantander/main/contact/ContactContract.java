package rrzaniolo.testandroidsantander.main.contact;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/8/2018.
 * All rights reserved.
 */

import rrzaniolo.testandroidsantander.main.contact.Custom.ContactLayout;

/**
 * The contract definition for ContactView and ContactPresenter.
 * */
public interface ContactContract {

    interface View{
        ContactLayout getForm();
        void setFormListener();
        void clearForm();

        Boolean isFromWithError();
        void showFormErrors();
        void hideFormErrors();

        void showSuccess();
        void hideSuccess();

        void showLoading();
        void hideLoading();

        void showError();
        void hideError();
    }

    interface Presenter{
       void onStart();
       void onPause();

        android.view.View.OnClickListener onSendEventClick();
       android.view.View.OnClickListener onSendNewMessageClick();
    }
}
