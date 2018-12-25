package com.seletiva.santander.investment.ui.investments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.datasources.funds.FundsDatasource;
import com.seletiva.santander.investment.ui.investments.adapters.InvestmentsAdapter;
import com.seletiva.santander.investment.ui.investments.domain.MoreInfo;
import com.seletiva.santander.investment.ui.tabs.domain.TabClickEvent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@EFragment(R.layout.fragment_investment)
public class InvestmentsFragment extends Fragment implements Investments.View {
    @ViewById
    View progress;

    @ViewById
    LinearLayout investmentLayout;

    //region Header
    @ViewById
    TextView title, fundName, whatIs, description;
    //endregion

    //region Risk
    @ViewById
    TextView riskTitle;

    @ViewById
    ImageView risk1, risk2, risk3, risk4, risk5;
    private ImageView[] riskViewLevelElements;
    //endregion

    //region Description
    @ViewById
    TextView periodsTitle, monthFund, monthCdi;

    @ViewById
    TextView twelveFund, twelveCdi;

    @ViewById
    TextView yearFund, yearCdi;
    //endregion

    //region List
    @ViewById
    RecyclerView details;
    //endregion

    private InvestmentsPresenter presenter;
    private FundsDatasource datasource;

    public InvestmentsFragment() {}

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getContext() != null) {
            if (isVisibleToUser) {
                EventBus.getDefault().post(new TabClickEvent(getString(R.string.tab_investments_title)));
            }
        }
    }

    @AfterViews
    public void init() {
        configureUI();
        configureData();
    }

    private void configureUI() {
        progress.setVisibility(View.VISIBLE);
        investmentLayout.setVisibility(View.GONE);
        riskViewLevelElements = new ImageView[]{risk1, risk2, risk3, risk4, risk5};
    }

    private void configureData() {
        datasource = new FundsDatasource();
        presenter = new InvestmentsPresenter(this, datasource, Schedulers.newThread(),
                AndroidSchedulers.mainThread());
        presenter.loadFunds();
    }

    @Override
    public void showProgressDialog() {
        progress.setVisibility(View.VISIBLE);
        investmentLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressDialog() {
        progress.setVisibility(View.GONE);
        investmentLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public void configureHeader(String title, String fundName, String whatIs, String definition) {
        this.title.setText(title);
        this.fundName.setText(fundName);
        this.whatIs.setText(whatIs);
        this.description.setText(definition);
    }

    @Override
    public void configureRiskBar(String riskTitle, int risk) {
        final int maxRiskLevel = 5;
        final int adjustedRiskIndex = risk - 1;
        this.riskTitle.setText(riskTitle);

        if (adjustedRiskIndex >= 0 && adjustedRiskIndex < maxRiskLevel) {
            riskViewLevelElements[adjustedRiskIndex].setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void configureInfo(String infoTitle, MoreInfo moreInfo) {
        periodsTitle.setText(infoTitle);

        monthFund.setText(moreInfo.getMonth().getFund());
        monthCdi.setText(moreInfo.getMonth().getCDI());

        yearFund.setText(moreInfo.getYear().getFund());
        yearCdi.setText(moreInfo.getYear().getCDI());

        twelveFund.setText(moreInfo.getTwelveMonths().getFund());
        twelveCdi.setText(moreInfo.getTwelveMonths().getFund());
    }

    @Override
    public void configureInvestmentsList(InvestmentsAdapter adapter) {
        details.setLayoutManager(new LinearLayoutManager(getContext()));
        details.setAdapter(adapter);
        details.setNestedScrollingEnabled(false);
    }
}
