package br.com.santander.testeandroid.ui.Funds;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.santander.testeandroid.R;
import br.com.santander.testeandroid.ui.Funds.domain.Models.FundsScreen;
import br.com.santander.testeandroid.ui.Funds.domain.Models.InfoDetail;
import br.com.santander.testeandroid.ui.Funds.domain.Models.MoreInfoFundsDetail;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FundsFragment extends Fragment implements FundsView {
    private View view;
    private AppCompatActivity activity;
    private FundsPresenter presenter;
    private FundsScreen screen;

    @BindView(R.id.sv_container_fund)
    ScrollView containerFund;
    @BindView(R.id.rl_container_progress_bar)
    RelativeLayout containerProgressBar;
    @BindView(R.id.ll_container_error)
    LinearLayout containerError;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_fund_name)
    TextView tvFundName;
    @BindView(R.id.tv_what_is)
    TextView tvWhatIs;
    @BindView(R.id.tv_definition)
    TextView tvDefinition;
    @BindView(R.id.tv_risk_title)
    TextView tvRiskTitle;
    @BindView(R.id.iv_risk_lvl_1)
    ImageView indicatorRiskLvl01;
    @BindView(R.id.v_risk_bar_1)
    View barRiskLvl01;
    @BindView(R.id.iv_risk_lvl_2)
    ImageView indicatorRiskLvl02;
    @BindView(R.id.v_risk_bar_2)
    View barRiskLvl02;
    @BindView(R.id.iv_risk_lvl_3)
    ImageView indicatorRiskLvl03;
    @BindView(R.id.v_risk_bar_3)
    View barRiskLvl03;
    @BindView(R.id.iv_risk_lvl_4)
    ImageView indicatorRiskLvl04;
    @BindView(R.id.v_risk_bar_4)
    View barRiskLvl04;
    @BindView(R.id.iv_risk_lvl_5)
    ImageView indicatorRiskLvl05;
    @BindView(R.id.v_risk_bar_5)
    View barRiskLvl05;
    @BindView(R.id.tv_info_title)
    TextView tvInfoTitle;
    @BindView(R.id.tl_more_info)
    TableLayout moreInfoTable;
    @BindView(R.id.tl_general_information)
    TableLayout generalInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_funds, null);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        activity = ((AppCompatActivity) getActivity());
        presenter = new FundsPresenter(this);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.funds, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && screen == null) {
            presenter.loadScreenInfo();
        }
    }

    @Override
    public void prepareToolbar() {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbarTitle = view.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.funds);
    }

    @Override
    public void loadInformationSuccess(FundsScreen screen) {
        this.screen = screen;
        tvTitle.setText(screen.getTitle());
        tvFundName.setText(screen.getFundName());
        tvWhatIs.setText(screen.getWhatIs());
        tvDefinition.setText(screen.getDefinition());
        tvRiskTitle.setText(screen.getRiskTitle());
        prepareRiskBar();
        tvInfoTitle.setText(screen.getInfoTitle());
        prepareMoreInfoTable();
        prepareGeneralInfo();
        prepareDownloadInfo();
        hideProgressBar();
    }

    @Override
    public void prepareRiskBar() {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (10 * scale + 0.5f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, pixels);
        switch (screen.getRisk()) {
            case 1:
                barRiskLvl01.setLayoutParams(layoutParams);
                indicatorRiskLvl01.setVisibility(View.VISIBLE);
                break;
            case 2:
                barRiskLvl02.setLayoutParams(layoutParams);
                indicatorRiskLvl02.setVisibility(View.VISIBLE);
                break;
            case 3:
                barRiskLvl03.setLayoutParams(layoutParams);
                indicatorRiskLvl03.setVisibility(View.VISIBLE);
                break;
            case 4:
                barRiskLvl04.setLayoutParams(layoutParams);
                indicatorRiskLvl04.setVisibility(View.VISIBLE);
                break;
            case 5:
                barRiskLvl05.setLayoutParams(layoutParams);
                indicatorRiskLvl05.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void prepareMoreInfoTable() {
        List<MoreInfoFundsDetail> list = new ArrayList<>();
        list.add(screen.getMoreInfo().getMonth());
        list.add(screen.getMoreInfo().getYear());
        list.add(screen.getMoreInfo().getTwelveMonths());

        for(int i = 0; i < list.size(); i++) {
            MoreInfoFundsDetail detail = list.get(i);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.tablerow_more_info, null);

            TextView lbl = view.findViewById(R.id.tv_lbl_more_info);
            TextView fund = view.findViewById(R.id.tv_fund_value);
            TextView cdi = view.findViewById(R.id.tv_cdi_value);

            switch (i) {
                case 0:
                    lbl.setText(R.string.month);
                    break;
                case 1:
                    lbl.setText(R.string.year);
                    break;
                case 2:
                    lbl.setText(R.string.twelve_months);
                    break;
            }

            fund.setText(detail.getFundPercentage());
            cdi.setText(detail.getCdiPercentage());
            moreInfoTable.addView(view);
        }
    }

    @Override
    public void prepareGeneralInfo() {
        for(InfoDetail info : screen.getInfo()) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.tablerow_general_info, null);

            TextView lbl = view.findViewById(R.id.tv_general_info_lbl);
            TextView value = view.findViewById(R.id.tv_general_info_value);

            lbl.setText(info.getName());
            value.setText(info.getData());

            generalInfo.addView(view);
        }
    }

    @Override
    public void prepareDownloadInfo() {
        for(InfoDetail info : screen.getDownloadInfo()) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.tablerow_general_info, null);

            TextView lbl = view.findViewById(R.id.tv_general_info_lbl);
            TextView value = view.findViewById(R.id.tv_general_info_value);
            LinearLayout button = view.findViewById(R.id.ll_download_button);

            lbl.setText(info.getName());
            value.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);

            generalInfo.addView(view);
        }
    }

    @Override
    public void loadInfomationFailed() {
        containerFund.setVisibility(View.GONE);
        containerProgressBar.setVisibility(View.GONE);
        containerError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressBar() {
        containerError.setVisibility(View.GONE);
        containerFund.setVisibility(View.GONE);
        containerProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        containerError.setVisibility(View.GONE);
        containerProgressBar.setVisibility(View.GONE);
        containerFund.setVisibility(View.VISIBLE);
    }
}
