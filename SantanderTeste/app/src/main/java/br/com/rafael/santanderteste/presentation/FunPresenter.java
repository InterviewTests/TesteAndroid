package br.com.rafael.santanderteste.presentation;

import br.com.rafael.santanderteste.domain.entity.ScreenFund;
import br.com.rafael.santanderteste.repository.BankProvider;
import br.com.rafael.santanderteste.repository.BankRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FunPresenter implements FundContract.Presenter {

    private FundContract.View view;


    @Override
    public void setView(@NotNull FundContract.View view) {
        this.view = view;
    }

    @Override
    public void retrieveInvestimentData() {
        view.loadingInvestimentData();
        BankProvider.Companion.getFund(new BankRepository<ScreenFund>() {
            @Override
            public void onResponse(@Nullable ScreenFund reponse) {
                view.showInvestimentData(reponse);
            }

            @Override
            public void onFailure(@NotNull Throwable throwable) {
                view.showErrorToRetrieveData();
            }
        });
    }
}
