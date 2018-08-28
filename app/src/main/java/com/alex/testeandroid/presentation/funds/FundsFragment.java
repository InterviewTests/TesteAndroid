package com.alex.testeandroid.presentation.funds;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alex.testeandroid.R;
import com.alex.testeandroid.data.entities.funds.Funds;
import com.alex.testeandroid.data.entities.funds.Info;
import com.alex.testeandroid.presentation.helpers.DimenHelper;
import com.alex.testeandroid.presentation.helpers.FormatHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alex on 27/08/18.
 */
public class FundsFragment extends Fragment implements FundsView {

    //region FIELDS
    @BindView(R.id.fragment_funds_tvw_title)
    TextView tvwTitle;
    @BindView(R.id.fragment_funds_tvw_name)
    TextView tvwName;
    @BindView(R.id.fragment_funds_cons_vw_separator_content)
    View vwSeparatorContent;
    @BindView(R.id.fragment_funds_tvw_what_is)
    TextView tvwWhatIs;
    @BindView(R.id.fragment_funds_tvw_what_is_definition)
    TextView tvwWhatIsDefinition;
    @BindView(R.id.fragment_funds_tvw_risk_title)
    TextView tvwRiskTitle;
    @BindView(R.id.fragment_funds_cons_scale_risk)
    ConstraintLayout consScale;
    @BindView(R.id.fragment_funds_img_scale_risk_1)
    ImageView imgRisk1;
    @BindView(R.id.fragment_funds_img_scale_risk_2)
    ImageView imgRisk2;
    @BindView(R.id.fragment_funds_img_scale_risk_3)
    ImageView imgRisk3;
    @BindView(R.id.fragment_funds_img_scale_risk_4)
    ImageView imgRisk4;
    @BindView(R.id.fragment_funds_img_scale_risk_5)
    ImageView imgRisk5;
    @BindView(R.id.fragment_funds_vw_scale_risk_1)
    View vwRisk1;
    @BindView(R.id.fragment_funds_vw_scale_risk_2)
    View vwRisk2;
    @BindView(R.id.fragment_funds_vw_scale_risk_3)
    View vwRisk3;
    @BindView(R.id.fragment_funds_vw_scale_risk_4)
    View vwRisk4;
    @BindView(R.id.fragment_funds_vw_scale_risk_5)
    View vwRisk5;
    @BindView(R.id.fragment_funds_tvw_more_information)
    TextView tvwMoreInformation;
    @BindView(R.id.fragment_funds_cons_more_information)
    ConstraintLayout consMoreInformation;
    @BindView(R.id.fragment_funds_cons_in_month)
    ConstraintLayout consInMonth;
    @BindView(R.id.fragment_funds_cons_in_year)
    ConstraintLayout consInYear;
    @BindView(R.id.fragment_funds_cons_in_12_months)
    ConstraintLayout consIn12Months;
    @BindView(R.id.fragment_funds_tvw_in_month_fund)
    TextView tvwInMonthFund;
    @BindView(R.id.fragment_funds_tvw_in_month_CD)
    TextView tvwInMonthCD;
    @BindView(R.id.fragment_funds_tvw_in_year_fund)
    TextView tvwInYearFund;
    @BindView(R.id.fragment_funds_tvw_in_year_CD)
    TextView tvwInYearCD;
    @BindView(R.id.fragment_funds_tvw_in_12_months_fund)
    TextView tvwIn12MonthsFund;
    @BindView(R.id.fragment_funds_tvw_in_12_months_CD)
    TextView tvwIn12MonthsCD;
    @BindView(R.id.fragment_funds_cons_info)
    ConstraintLayout consInfo;
    @BindView(R.id.fragment_funds_cons_down_info)
    ConstraintLayout consDownInfo;
    @BindView(R.id.fragment_funds_btn_invest)
    Button btnInvest;
    @BindView(R.id.fragment_funds_pgb_loading)
    ProgressBar pgbLoading;

    private FundsPresenter presenter;
    //endregion

    public static FundsFragment newInstance() {
        return new FundsFragment();
    }

    //region LIFECYCLE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_funds, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new FundsPresenter(this);
        presenter.getFunds();
    }

    @Override
    public void onDestroyView() {
        if (presenter != null) presenter.detachView();
        super.onDestroyView();
    }
    //endregion

    //region METHODS
    //region OVERRIDES METHODS
    @Override
    public void showProgress(boolean show) {
        pgbLoading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessageErrorRequest() {
        Toast.makeText(getContext(), "falha ao montar formulário", Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadData(Funds funds) {
        tvwTitle.setText(funds.getTitle());
        tvwName.setText(funds.getFundName());
        tvwWhatIs.setText(funds.getWhatIs());
        tvwWhatIsDefinition.setText(funds.getDefinition());
        tvwRiskTitle.setText(funds.getRiskTitle());

        vwSeparatorContent.setVisibility(View.VISIBLE);

        selectRisk(funds.getRisk());

        tvwMoreInformation.setText(funds.getInfoTitle());

        FormatHelper formatHelper = new FormatHelper();

        if (funds.getMoreInfo().getMonth() != null) {
            consInMonth.setVisibility(View.VISIBLE);
            tvwInMonthFund.setText(formatHelper.formatPercent(funds.getMoreInfo().getMonth().getFund()));
            tvwInMonthCD.setText(formatHelper.formatPercent(funds.getMoreInfo().getMonth().getCDI()));
        }

        if (funds.getMoreInfo().getYear() != null) {
            consInYear.setVisibility(View.VISIBLE);
            tvwInYearFund.setText(formatHelper.formatPercent(funds.getMoreInfo().getYear().getFund()));
            tvwInYearCD.setText(formatHelper.formatPercent(funds.getMoreInfo().getYear().getCDI()));
        }

        if (funds.getMoreInfo().getMonths12() != null) {
            consIn12Months.setVisibility(View.VISIBLE);
            tvwIn12MonthsFund.setText(formatHelper.formatPercent(funds.getMoreInfo().getMonths12().getFund()));
            tvwIn12MonthsCD.setText(formatHelper.formatPercent(funds.getMoreInfo().getMonths12().getCDI()));
        }

        consMoreInformation.setVisibility(View.VISIBLE);
        buildInfo(funds.getInfo());
        buildDownInfo(funds.getDownInfo());

        btnInvest.setVisibility(View.VISIBLE);
    }
    //endregion

    //region PRIVATE METHODS
    private void selectRisk(int risk) {
        int height = new DimenHelper().toPx(getResources(), 14);
        switch (risk) {
            case 1:
                imgRisk1.setVisibility(View.VISIBLE);
                vwRisk1.getLayoutParams().height = height;
                vwRisk1.requestLayout();
                break;
            case 2:
                imgRisk2.setVisibility(View.VISIBLE);
                vwRisk2.getLayoutParams().height = height;
                vwRisk2.requestLayout();
                break;
            case 3:
                imgRisk3.setVisibility(View.VISIBLE);
                vwRisk3.getLayoutParams().height = height;
                vwRisk3.requestLayout();
                break;
            case 4:
                imgRisk4.setVisibility(View.VISIBLE);
                vwRisk4.getLayoutParams().height = height;
                vwRisk4.requestLayout();
                break;
            default:
                imgRisk5.setVisibility(View.VISIBLE);
                vwRisk5.getLayoutParams().height = height;
                vwRisk5.requestLayout();
                break;
        }
        consScale.setVisibility(View.VISIBLE);
    }

    private void buildInfo(List<Info> infos) {
        if (infos != null && infos.size() > 0) {
            int id = 1;
            for (Info info : infos) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_funds_info_item, null);
                view.setId(id);
                TextView tvwName = view.findViewById(R.id.fragment_funds_info_item_name);
                tvwName.setText(info.getName());
                TextView tvwData = view.findViewById(R.id.fragment_funds_info_item_data);
                tvwData.setText(info.getData());
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                if (id > 1) {
                    params.topToBottom = id - 1;
                }
                consInfo.addView(view, params);
                id++;

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(consInfo);
                constraintSet.connect(view.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                constraintSet.connect(view.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                constraintSet.applyTo(consInfo);
            }
            consInfo.setVisibility(View.VISIBLE);
        }
    }

    private void buildDownInfo(List<Info> downInfos) {
        if (downInfos != null && downInfos.size() > 0) {
            int id = 1;
            for (final Info info : downInfos) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_funds_down_info_item, null);
                view.setId(id);
                TextView tvwName = view.findViewById(R.id.fragment_funds_down_info_item_tvw_name);
                tvwName.setText(info.getName());
                TextView tvwDownload = view.findViewById(R.id.fragment_funds_down_info_item_tvw_download);
                tvwDownload.setTag(info);
                tvwDownload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Info info1 = (Info) v.getTag();
                        Toast.makeText(getContext(), info1.getName() + "... baixando", Toast.LENGTH_SHORT).show();
                    }
                });
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                if (id > 1) {
                    params.topToBottom = id - 1;
                }
                consDownInfo.addView(view, params);
                id++;

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(consDownInfo);
                constraintSet.connect(view.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                constraintSet.connect(view.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                constraintSet.applyTo(consDownInfo);
            }
            consDownInfo.setVisibility(View.VISIBLE);
        }
    }
    //endregion
    //endregion
}
