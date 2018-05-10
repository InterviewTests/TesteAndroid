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
import com.carpinelli.testeandroid.model.DownInfo;
import com.carpinelli.testeandroid.model.Info;
import com.carpinelli.testeandroid.model.Screen;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvestFragment extends Fragment implements MvpInvest.View {

    private static final String TAG = InvestFragment.class.getSimpleName();

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


    public InvestFragment() {
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
    public void onScreenReady(Screen screen) {

        setInvestInfos(screen);

        setRisk(screen.getRisk());

        setRecyclerInfos(screen.getInfos());

        setRecyclerDownInfos(screen.getDownInfos());

    }

    @SuppressLint("SetTextI18n")
    private void setInvestInfos(Screen screen) {
        tvTitle.setText(screen.getTitle());
        textFundName.setText(screen.getFundName());
        tvWhatIs.setText(screen.getWhatIs());
        tvDefinition.setText(screen.getDefinition());
        tvRiskTitle.setText(screen.getRiskTitle());
        tvInfoTitle.setText(screen.getInfoTitle());

        // more infos

        tvFundoMes.setText(screen.getMoreInfo().getMonth().getFund() + "%");
        tvCDIMes.setText(screen.getMoreInfo().getMonth().getCDI() + "%");
        tvFundoAno.setText(screen.getMoreInfo().getYear().getFund() + "%");
        tvCDIAno.setText(screen.getMoreInfo().getYear().getFund() + "%");
        tvFundo12Meses.setText(screen.getMoreInfo().getTwelveMonths().getFund() + "%");
        tvCDI12Meses.setText(screen.getMoreInfo().getTwelveMonths().getFund() + "%");

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


    public void setRecyclerInfos(List<Info> recyclerInfos) {

        recyclerViewInfos.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerViewInfos.setAdapter(new InfoAdapter(getContext(), recyclerInfos));

    }

    public void setRecyclerDownInfos(List<DownInfo> recyclerInfos) {

        recyclerViewDownInfos.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerViewDownInfos.setAdapter(new DownInfoAdapter(getContext(), recyclerInfos));

    }


}
