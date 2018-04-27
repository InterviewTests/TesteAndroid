package io.github.pierry.better_call_me.ui.presenters;

import android.content.Context;
import io.github.pierry.better_call_me.App;
import io.github.pierry.better_call_me.common.support.RxBus;
import io.github.pierry.better_call_me.domain.Fund;
import io.github.pierry.better_call_me.domain.viewmodels.Data;
import io.github.pierry.better_call_me.interactors.contracts.IInvestmentInteractor;
import io.github.pierry.better_call_me.ui.contracts.IInvestmentView;
import io.github.pierry.better_call_me.ui.presenters.contracts.IInvestmentPresenter;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;
import javax.inject.Inject;

public class InvestmentPresenter implements IInvestmentPresenter {

  @Inject IInvestmentInteractor interactor;
  @Inject CompositeDisposable compositeDisposable;
  @Inject RxBus rxBus;

  private IInvestmentView investmentView;

  public InvestmentPresenter(Context context) {
    App.getControllerComponent(context).inject(this);
  }

  @Override public void inject(IInvestmentView investmentView) {
    this.investmentView = investmentView;
  }

  @Override public void sync() {
    investmentView.showLoader();
    interactor.fetchFund();
  }

  @Override public void onStart() {
    compositeDisposable.add(rxBus.asFlowable().subscribe(event -> {
      if (event instanceof Fund) {
        investmentView.hideLoader();
        fill((Fund) event);
      }
    }));
  }

  private void fill(Fund fund) {
    investmentView.setSupertitle(fund.getTitle());
    investmentView.setFundName(fund.getFundName());
    investmentView.setTitle(fund.getWhatIs());
    investmentView.setDefinition(fund.getDefinition());
    investmentView.setRiskTitle(fund.getRiskTitle());
    investmentView.setInfoTitle(fund.getInfoTitle());
    investmentView.setMonthFund(fund.getMoreInfo().getMonth().getFund());
    investmentView.setMonthCdi(fund.getMoreInfo().getMonth().getCdi());
    investmentView.setYearFund(fund.getMoreInfo().getYear().getFund());
    investmentView.setYearCdi(fund.getMoreInfo().getYear().getCdi());
    investmentView.setTwelveMonthsFund(fund.getMoreInfo().getTwelveMonths().getFund());
    investmentView.setTwelveMonthsCdi(fund.getMoreInfo().getTwelveMonths().getCdi());
    List<Data> list = fund.getInfo();
    list.addAll(fund.getDownInfo());
    investmentView.adapter(list);
  }

}
