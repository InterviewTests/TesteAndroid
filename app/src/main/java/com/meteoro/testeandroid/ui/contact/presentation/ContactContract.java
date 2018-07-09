package com.meteoro.testeandroid.ui.contact.presentation;

public interface ContactContract {
    interface View {
        void showLoading();
    }

    interface Presenter {
        void initializeContents();
    }
}
