package br.com.ricardo.testeandroid;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class InvestmentFragment extends Fragment {

    private TextView textInvestmentTitle;
    private TextView textInvestmentFoundName;
    private TextView textInvestmentWhatsIs;
    private TextView textInvestmentDefinition;
    private TextView textInvestmentRiskDegree;
    private TextView textInvestmentMoreInfoTitle;

    private Typeface font;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View investmentView = inflater.inflate(R.layout.fragment_investment, container, false);

        textInvestmentTitle = (TextView) investmentView.findViewById(R.id.investment_title);
        textInvestmentFoundName = (TextView) investmentView.findViewById(R.id.investment_found_name);
        textInvestmentWhatsIs = (TextView) investmentView.findViewById(R.id.investment_what_is);
        textInvestmentDefinition = (TextView) investmentView.findViewById(R.id.investment_definition);
        textInvestmentRiskDegree = (TextView) investmentView.findViewById(R.id.investment_risk_degree);
        textInvestmentMoreInfoTitle = (TextView) investmentView.findViewById(R.id.investment_more_info_title);

        font = Typeface.createFromAsset(getActivity().getAssets(), "font/DINPro-Medium.otf");
        textInvestmentTitle.setTypeface(font);
        textInvestmentFoundName.setTypeface(font);
        textInvestmentWhatsIs.setTypeface(font);
        textInvestmentDefinition.setTypeface(font);
        textInvestmentRiskDegree.setTypeface(font);
        textInvestmentMoreInfoTitle.setTypeface(font);

        return investmentView;

    }
}