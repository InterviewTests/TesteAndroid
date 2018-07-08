package com.meteoro.testeandroid.ui.investiment.presentation;

public interface InvestimentContract {
    interface View {
        void showLoading();
    }

    interface Presenter {
        void initializeContents();
    }
}
