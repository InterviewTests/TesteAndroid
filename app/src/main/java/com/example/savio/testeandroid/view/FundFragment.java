package com.example.savio.testeandroid.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.savio.testeandroid.R;
import com.example.savio.testeandroid.adapter.InfoAdapter;
import com.example.savio.testeandroid.contract.FundContract;
import com.example.savio.testeandroid.presenter.FundPresenter;

import java.util.List;

public class FundFragment extends Fragment implements FundContract.View{

    private FundPresenter fundPresenter;

    View view;
    RecyclerView recyclerView;
    InfoAdapter infoAdapter;

    TextView title, fundName, whatis, definition, riskTitle, infoTitle;
    TextView monthFund, monthCDI, yearFund, yearCDI, twelveFUND, twelveCDI;
    RadioButton fundRisk1, fundRisk2, fundRisk3, fundRisk4, fundRisk5;

    public FundFragment() {}


//-----------------------------------LifeCycle------------------------------------------------------//

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fund, container, false);

        //instance the presenter
        fundPresenter = new FundPresenter(this);

        //call the function to get de datas
        fundPresenter.requestData();

        return view;

    }//onCreate ends

    @Override
    public void initView() {

        title = view.findViewById(R.id.fund_title);
        fundName = view.findViewById(R.id.fund_name);
        whatis = view.findViewById(R.id.fund_whatis);
        definition = view.findViewById(R.id.fund_definition);
        riskTitle = view.findViewById(R.id.fund_risk_title);
        infoTitle = view.findViewById(R.id.fund_info_title);

        fundRisk1 = view.findViewById(R.id.fund_risk1);
        fundRisk2 = view.findViewById(R.id.fund_risk2);
        fundRisk3 = view.findViewById(R.id.fund_risk3);
        fundRisk4 = view.findViewById(R.id.fund_risk4);
        fundRisk5 = view.findViewById(R.id.fund_risk5);

        monthFund = view.findViewById(R.id.fund_month_fund);
        monthCDI = view.findViewById(R.id.fund_month_cdi);
        yearFund = view.findViewById(R.id.fund_year_fund);
        yearCDI = view.findViewById(R.id.fund_year_cdi);
        twelveFUND = view.findViewById(R.id.fund_12months_fund);
        twelveCDI = view.findViewById(R.id.fund_12months_cdi);

        recyclerView = view.findViewById(R.id.fund_recycler_info);
    }

    @Override
    public void setViewData(List<Object> data) {

        //create lists for each results
        List<Object> messages = (List<Object>) data.get(0);
        List<Object> moreInfos = (List<Object>) data.get(1);
        List<Object> infos = (List<Object>) data.get(2);

        //set texts of the screen
        title.setText(messages.get(0).toString());
        fundName.setText(messages.get(1).toString());
        whatis.setText(messages.get(2).toString());
        definition.setText(messages.get(3).toString());
        riskTitle.setText(messages.get(4).toString());
        infoTitle.setText(messages.get(6).toString());

        //set height of the risk
        switch ((int) messages.get(5)){
            case 1:

                fundRisk1.setScaleY(2);
                break;
            case 2:

                fundRisk2.setScaleY(2);
                break;
            case 3:

                fundRisk3.setScaleY(2);
                break;
            case 4:

                fundRisk4.setScaleY(2);
                break;
            case 5:

                fundRisk5.setScaleY(2);
                break;
        }

        //set texts into more infos
        monthFund.setText(moreInfos.get(0).toString());
        monthCDI.setText(moreInfos.get(1).toString());
        yearFund.setText(moreInfos.get(2).toString());
        yearCDI.setText(moreInfos.get(3).toString());
        twelveFUND.setText(moreInfos.get(4).toString());
        twelveCDI.setText(moreInfos.get(5).toString());

        //setup adapter
        infoAdapter = new InfoAdapter(getContext(), infos);
        recyclerView.setAdapter(infoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
