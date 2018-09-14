package com.nataliafavero.santander.ui.detailFund;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.nataliafavero.santander.R;
import com.nataliafavero.santander.ui.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nataliafavero on 11/09/18.
 */

public class DetailFundFragment extends Fragment implements DetailFundContract.View {

    @BindView(R.id.fund_title)
    TextView mTitle;

    @BindView(R.id.fund_fundName)
    TextView mFundName;

    @BindView(R.id.fund_whatIs)
    TextView mWhatIs;

    @BindView(R.id.fund_definition)
    TextView mDefinition;

    @BindView(R.id.fund_riskTitle)
    TextView mRiskTitle;

    @BindView(R.id.fund_infoTitle)
    TextView mInfoTitle;

    @BindView(R.id.fund_list_moreInfo)
    ListView mListMoreInfo;

    @BindView(R.id.fund_list_info)
    ListView mInfo;

    @BindView(R.id.fund_risk_1)
    View risk1;

    @BindView(R.id.fund_risk_2)
    View risk2;

    @BindView(R.id.fund_risk_3)
    View risk3;

    @BindView(R.id.fund_risk_4)
    View risk4;

    @BindView(R.id.fund_risk_5)
    View risk5;

    private DetailFundContract.Presenter mPresenter;
    private List<View> listRisk;

    public static DetailFundFragment newInstance() {
        return new DetailFundFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_fund, container, false);
        ButterKnife.bind(this, view);
        listRisk = new ArrayList<>(Arrays.asList(risk1, risk2, risk3, risk4, risk5));
        return view;
    }

    @Override
    public void setPresenter(DetailFundContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void showFund(DetailFundModel model, List<DetailFundInfoModel> moreInfo, List<DetailFundInfoModel> info) {
        mTitle.setText(model.getTitle());
        mFundName.setText(model.getFundName());
        mWhatIs.setText(model.getWhatIs());
        mDefinition.setText(model.getDefinition());
        mRiskTitle.setText(model.getRiskTitle());
        mInfoTitle.setText(model.getInfoTitle());

        setRisk(model.getRisk());

        int sizeLine = 28; //26dp;

        InfoBaseAdapter moreInfoAdapter = new InfoBaseAdapter(moreInfo, this);
        mListMoreInfo.setAdapter(moreInfoAdapter);
        ViewGroup.LayoutParams lp = mListMoreInfo.getLayoutParams();
        lp.height = moreInfo.size() * Utils.convertDpToPixel(getContext(), sizeLine);
        mListMoreInfo.setLayoutParams(lp);

        InfoBaseAdapter infoAdapter = new InfoBaseAdapter(info, this);
        mInfo.setAdapter(infoAdapter);
        ViewGroup.LayoutParams lpInfo = mInfo.getLayoutParams();
        lpInfo.height = info.size() * Utils.convertDpToPixel(getContext(), sizeLine);
        lpInfo.resolveLayoutDirection(View.LAYOUT_DIRECTION_INHERIT);
        mInfo.setLayoutParams(lpInfo);

    }

    private void setRisk(int risk) {
        View riskView = listRisk.get(risk-1);

        ViewGroup.LayoutParams params = riskView.getLayoutParams();
        params.height = Utils.convertDpToPixel(getContext(),15);
        riskView.setLayoutParams(params);
        ViewGroup.MarginLayoutParams margins = (ViewGroup.MarginLayoutParams) riskView.getLayoutParams();
        margins.setMargins(0, Utils.convertDpToPixel(getContext(), 10), 2, 0);
        riskView.setLayoutParams(margins);
    }
}
