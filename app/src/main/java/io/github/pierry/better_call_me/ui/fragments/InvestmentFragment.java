package io.github.pierry.better_call_me.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.github.pierry.better_call_me.App;
import io.github.pierry.better_call_me.R;
import io.github.pierry.better_call_me.domain.viewmodels.Data;
import io.github.pierry.better_call_me.ui.adapters.InfoAdapter;
import io.github.pierry.better_call_me.ui.contracts.IInvestmentView;
import io.github.pierry.better_call_me.ui.presenters.contracts.IInvestmentPresenter;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;

public class InvestmentFragment extends Fragment implements IInvestmentView {

  @BindView(R.id.supertitle) TextView supertitle;
  @BindView(R.id.fundName) TextView fundName;
  @BindView(R.id.title) TextView title;
  @BindView(R.id.definition) TextView definition;
  @BindView(R.id.risktTitle) TextView riskTitle;
  @BindView(R.id.infoTitle) TextView infoTitle;
  @BindView(R.id.monthFund) TextView monthFund;
  @BindView(R.id.monthCdi) TextView monthCdi;
  @BindView(R.id.yearFund) TextView yearFund;
  @BindView(R.id.yearCdi) TextView yearCdi;
  @BindView(R.id.twelveMonthsFund) TextView twelveMonthsFund;
  @BindView(R.id.twelveMonthsCdi) TextView twelveMonthCdi;
  @BindView(R.id.recyclerView) RecyclerView recyclerView;
  @BindView(R.id.progress) ProgressBar progress;

  @Inject IInvestmentPresenter presenter;
  @Inject InfoAdapter infoAdapter;

  private Unbinder unbind;

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View inflate = inflater.inflate(R.layout.investment_fragment, container, false);
    unbind = ButterKnife.bind(this, inflate);
    App.getControllerComponent(Objects.requireNonNull(getActivity())).inject(this);
    configRecyclerView();
    presenter.inject(this);
    presenter.sync();
    return inflate;
  }

  @Override public void onStart() {
    super.onStart();
    presenter.onStart();
  }

  @Override public void showLoader() {
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> progress.setVisibility(View.VISIBLE));
  }

  @Override public void setSupertitle(String text) {
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> supertitle.setText(text));
  }

  @Override public void setFundName(String text) {
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> fundName.setText(text));
  }

  @Override public void setTitle(String text) {
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> title.setText(text));
  }

  @Override public void setDefinition(String text) {
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> definition.setText(text));
  }

  @Override public void setRiskTitle(String text) {
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> riskTitle.setText(text));
  }

  @Override public void setInfoTitle(String text) {
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> infoTitle.setText(text));
  }

  @Override public void setMonthFund(String text) {
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> monthFund.setText(text));
  }

  @Override public void setMonthCdi(String text) {
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> monthCdi.setText(text));
  }

  @Override public void setYearFund(String text) {
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> yearFund.setText(text));
  }

  @Override public void setYearCdi(String text) {
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> yearCdi.setText(text));
  }

  @Override public void setTwelveMonthsFund(String text) {
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> twelveMonthsFund.setText(text));
  }

  @Override public void setTwelveMonthsCdi(String text) {
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> twelveMonthCdi.setText(text));
  }

  public void configRecyclerView() {
    LinearLayoutManager linearManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(linearManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
  }

  @Override public void adapter(List<Data> list) {
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
      infoAdapter.addItems(list);
      recyclerView.setAdapter(infoAdapter);
    });
  }

  @Override public void hideLoader() {
    Objects.requireNonNull(getActivity()).runOnUiThread(() -> progress.setVisibility(View.GONE));
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbind.unbind();
  }
}
