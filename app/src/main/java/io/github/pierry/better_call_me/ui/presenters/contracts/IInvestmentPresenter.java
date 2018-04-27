package io.github.pierry.better_call_me.ui.presenters.contracts;

import io.github.pierry.better_call_me.ui.contracts.IInvestmentView;

public interface IInvestmentPresenter {

  void inject(IInvestmentView investmentView);

  void sync();

  void onStart();
}
