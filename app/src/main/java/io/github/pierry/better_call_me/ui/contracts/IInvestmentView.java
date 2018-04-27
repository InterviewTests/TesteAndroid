package io.github.pierry.better_call_me.ui.contracts;

import io.github.pierry.better_call_me.domain.viewmodels.Data;
import java.util.List;

public interface IInvestmentView {

  void showLoader();

  void setTitle(String text);

  void setSupertitle(String text);

  void setFundName(String text);

  void setDefinition(String text);

  void setRiskTitle(String text);

  void setInfoTitle(String text);

  void setMonthCdi(String text);

  void setYearFund(String text);

  void setYearCdi(String text);

  void setMonthFund(String text);

  void setTwelveMonthsFund(String text);

  void setTwelveMonthsCdi(String text);

  void adapter(List<Data> list);

  void hideLoader();

}
