package com.meteoro.testeandroid.ui.investiment.presentation;

import com.meteoro.testeandroid.ui.investiment.domain.model.ScreenViewModel;

public interface InvestimentContract {
    interface View {
        void showLoading();

        void showViewModel(ScreenViewModel viewModel);
    }

    interface Presenter {
        void initializeContents();
    }
}
