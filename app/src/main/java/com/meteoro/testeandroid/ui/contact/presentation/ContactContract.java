package com.meteoro.testeandroid.ui.contact.presentation;

import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;

public interface ContactContract {
    interface View {
        void showLoading();

        void showViewModel(CellsViewModel viewModel);

        void showError();
    }

    interface Presenter {
        void initializeContents();

        void validateFields(CellsViewModel viewModel);
    }
}
