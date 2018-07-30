package com.carpinelli.testeandroid.ui.invest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.carpinelli.testeandroid.R;
import com.carpinelli.testeandroid.model.invest.DownInfo;
import com.carpinelli.testeandroid.model.invest.Info;
import com.carpinelli.testeandroid.model.invest.Investment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvestmentFragment extends Fragment implements MvpInvest.View {

    private static final String TAG = InvestmentFragment.class.getSimpleName();

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.textFundName)
    TextView textFundName;
    @BindView(R.id.tvWhatIs)
    TextView tvWhatIs;
    @BindView(R.id.tvDefinition)
    TextView tvDefinition;
    @BindView(R.id.tvRiskTitle)
    TextView tvRiskTitle;
    @BindView(R.id.tvInfoTitle)
    TextView tvInfoTitle;

    @BindView(R.id.imgRisk1)
    ImageView imgRisk1;
    @BindView(R.id.imgRisk2)
    ImageView imgRisk2;
    @BindView(R.id.imgRisk3)
    ImageView imgRisk3;
    @BindView(R.id.imgRisk4)
    ImageView imgRisk4;
    @BindView(R.id.imgRisk5)
    ImageView imgRisk5;

    @BindView(R.id.tvFundoMes)
    TextView tvFundoMes;
    @BindView(R.id.tvCDIMes)
    TextView tvCDIMes;
    @BindView(R.id.tvFundoAno)
    TextView tvFundoAno;
    @BindView(R.id.tvFundo12Meses)
    TextView tvFundo12Meses;
    @BindView(R.id.tvCDIAno)
    TextView tvCDIAno;
    @BindView(R.id.tvCDI12Meses)
    TextView tvCDI12Meses;

    @BindView(R.id.recyclerViewInfos)
    RecyclerView recyclerViewInfos;

    @BindView(R.id.recyclerViewDownInfos)
    RecyclerView recyclerViewDownInfos;

    private InvestPresenter investPresenter;


    public InvestmentFragment() {
        investPresenter = new InvestPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invest, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (investPresenter != null) {
            investPresenter.onStart();
        }

    }

    @Override
    public void onInvestmentReady(Investment investment) {

        setInvestmentInfo(investment);

        setRisk(investment.getRisk());

        setRecyclerInfo(investment.getInfos());

        setRecyclerDownInfo(investment.getDownInfos());

    }

    @SuppressLint("SetTextI18n")
    private void setInvestmentInfo(Investment investment) {
        tvTitle.setText(investment.getTitle());
        textFundName.setText(investment.getFundName());
        tvWhatIs.setText(investment.getWhatIs());
        tvDefinition.setText(investment.getDefinition());
        tvRiskTitle.setText(investment.getRiskTitle());
        tvInfoTitle.setText(investment.getInfoTitle());

        // more infos

        tvFundoMes.setText(investment.getMoreInfo().getMonth().getFund() + "%");
        tvCDIMes.setText(investment.getMoreInfo().getMonth().getCDI() + "%");
        tvFundoAno.setText(investment.getMoreInfo().getYear().getFund() + "%");
        tvCDIAno.setText(investment.getMoreInfo().getYear().getFund() + "%");
        tvFundo12Meses.setText(investment.getMoreInfo().getTwelveMonths().getFund() + "%");
        tvCDI12Meses.setText(investment.getMoreInfo().getTwelveMonths().getFund() + "%");

    }

    private void setRisk(Integer risk) {

        switch (risk) {
            case 1:
                imgRisk1.setVisibility(View.VISIBLE);
                break;
            case 2:
                imgRisk2.setVisibility(View.VISIBLE);
                break;
            case 3:
                imgRisk3.setVisibility(View.VISIBLE);
                break;
            case 4:
                imgRisk4.setVisibility(View.VISIBLE);
                break;
            case 5:
                imgRisk5.setVisibility(View.VISIBLE);
                break;
        }

    }


    public void setRecyclerInfo(List<Info> recyclerInfos) {

        recyclerViewInfos.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerViewInfos.setAdapter(new InfoAdapter(getContext(), recyclerInfos));

    }

    public void setRecyclerDownInfo(List<DownInfo> recyclerInfos) {

        recyclerViewDownInfos.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerViewDownInfos.setAdapter(new DownInfoAdapter(getContext(), recyclerInfos));

    }


}
