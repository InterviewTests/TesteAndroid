package br.com.ricardo.testeandroid.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.ricardo.testeandroid.R;
import br.com.ricardo.testeandroid.model.DownInfo;
import br.com.ricardo.testeandroid.model.Info;
import br.com.ricardo.testeandroid.model.InvestmentInteractor;
import br.com.ricardo.testeandroid.model.InvestmentInteractorImpl;
import br.com.ricardo.testeandroid.model.Screen;
import br.com.ricardo.testeandroid.presenter.InvestmentPresenter;
import br.com.ricardo.testeandroid.presenter.InvestmentPresenterImpl;


public class InvestmentFragment extends Fragment implements InvestmentView{

    private TextView textInvestmentTitle;
    private TextView textInvestmentFoundName;
    private TextView textInvestmentWhatsIs;
    private TextView textInvestmentDefinition;
    private TextView textInvestmentRiskDegree;
    private LinearLayout linearInvestmentRisk1;
    private LinearLayout linearInvestmentRisk2;
    private LinearLayout linearInvestmentRisk3;
    private LinearLayout linearInvestmentRisk4;
    private LinearLayout linearInvestmentRisk5;
    private int riskDegree;
    private TextView textInvestmentMoreInfoTitle;
    private TextView textInvestmentMoreInfoTitleFund;
    private TextView textInvestmentMoreInfoTitleCdi;
    private TextView textInvestmentMoreInfoTitleMonth;
    private TextView textInvestmentMoreInfoTitleYear;
    private TextView textInvestmentMoreInfoTitleTwelve;
    private TextView textInvestmentMoreInfoTitleFundMonth;
    private TextView textInvestmentMoreInfoTitleFundYear;
    private TextView textInvestmentMoreInfoTitleFundTwelve;
    private TextView textInvestmentMoreInfoTitleCdiMonth;
    private TextView textInvestmentMoreInfoTitleCdiYear;
    private TextView textInvestmentMoreInfoTitleCdiTwelve;
    private TextView textInvestmentInfo1;
    private TextView textInvestmentInfo2;
    private TextView textInvestmentInfo3;
    private TextView textInvestmentInfo4;
    private TextView textInvestmentInfo5;
    private TextView textInvestmentInfo6;
    private TextView textInvestmentInfo7;
    private TextView textInvestmentValueInfo1;
    private TextView textInvestmentValueInfo2;
    private TextView textInvestmentValueInfo3;
    private TextView textInvestmentValueInfo4;
    private TextView textInvestmentValueInfo5;
    private TextView textInvestmentValueInfo6;
    private TextView textInvestmentValueInfo7;
    private TextView textInvestmentDownInfo1;
    private TextView textInvestmentDownInfo2;
    private TextView textInvestmentDownInfo3;
    private TextView textInvestmentDownInfo4;
    private TextView textInvestmentDownInfo5;
    private TextView textInvestmentValueDownInfo1;
    private TextView textInvestmentValueDownInfo2;
    private TextView textInvestmentValueDownInfo3;
    private TextView textInvestmentValueDownInfo4;
    private TextView textInvestmentValueDownInfo5;
    private LinearLayout linearInvestmentContainer;
    private ProgressBar progressBarInvestment;

    private Typeface font;

    //Model
    private InvestmentInteractor investmentInteractor;

    //Presenter
    private InvestmentPresenter investmentPresenter;

    private List<Screen> screenList;
    private List<Info> infoList;
    private List<DownInfo> downInfoList;
    private String arrayInfoName[] = new String[7];
    private String arrayInfoData[] = new String[7];
    private String arrayDownInfoName[] = new String[7];
    private String arrayDownInfoData[] = new String[7];


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View investmentView = inflater.inflate(R.layout.fragment_investment, container, false);

        investmentInteractor = new InvestmentInteractorImpl();
        investmentPresenter = new InvestmentPresenterImpl(investmentInteractor);
        screenList = new ArrayList<>();
        infoList = new ArrayList<>();
        downInfoList = new ArrayList<>();

        linearInvestmentContainer = (LinearLayout) investmentView.findViewById(R.id.investment_linear_container);
        progressBarInvestment = (ProgressBar) investmentView.findViewById(R.id.investment_progress_bar);
        textInvestmentTitle = (TextView) investmentView.findViewById(R.id.investment_title);
        textInvestmentFoundName = (TextView) investmentView.findViewById(R.id.investment_found_name);
        textInvestmentWhatsIs = (TextView) investmentView.findViewById(R.id.investment_what_is);
        textInvestmentDefinition = (TextView) investmentView.findViewById(R.id.investment_definition);
        textInvestmentRiskDegree = (TextView) investmentView.findViewById(R.id.investment_risk_degree);
        linearInvestmentRisk1 = (LinearLayout) investmentView.findViewById(R.id.investment_linear_arrow_risk1);
        linearInvestmentRisk2 = (LinearLayout) investmentView.findViewById(R.id.investment_linear_arrow_risk2);
        linearInvestmentRisk3 = (LinearLayout) investmentView.findViewById(R.id.investment_linear_arrow_risk3);
        linearInvestmentRisk4 = (LinearLayout) investmentView.findViewById(R.id.investment_linear_arrow_risk4);
        linearInvestmentRisk5 = (LinearLayout) investmentView.findViewById(R.id.investment_linear_arrow_risk5);
        textInvestmentMoreInfoTitle = (TextView) investmentView.findViewById(R.id.investment_more_info_title);
        textInvestmentMoreInfoTitleFund = (TextView) investmentView.findViewById(R.id.investment_more_info_title_fund);
        textInvestmentMoreInfoTitleCdi = (TextView) investmentView.findViewById(R.id.investment_more_info_title_cdi);
        textInvestmentMoreInfoTitleMonth = (TextView) investmentView.findViewById(R.id.investment_more_info_title_month);
        textInvestmentMoreInfoTitleYear = (TextView) investmentView.findViewById(R.id.investment_more_info_title_year);
        textInvestmentMoreInfoTitleTwelve = (TextView) investmentView.findViewById(R.id.investment_more_info_title_twelve);
        textInvestmentMoreInfoTitleFundMonth = (TextView) investmentView.findViewById(R.id.investment_more_info_fund_month);
        textInvestmentMoreInfoTitleFundYear = (TextView) investmentView.findViewById(R.id.investment_more_info_fund_year);
        textInvestmentMoreInfoTitleFundTwelve = (TextView) investmentView.findViewById(R.id.investment_more_info_fund_twelve);
        textInvestmentMoreInfoTitleCdiMonth = (TextView) investmentView.findViewById(R.id.investment_more_info_cdi_month);
        textInvestmentMoreInfoTitleCdiYear = (TextView) investmentView.findViewById(R.id.investment_more_info_cdi_year);
        textInvestmentMoreInfoTitleCdiTwelve = (TextView) investmentView.findViewById(R.id.investment_more_info_cdi_twelve);
        textInvestmentInfo1 = (TextView) investmentView.findViewById(R.id.investment_title_info1);
        textInvestmentInfo2 = (TextView) investmentView.findViewById(R.id.investment_title_info2);
        textInvestmentInfo3 = (TextView) investmentView.findViewById(R.id.investment_title_info3);
        textInvestmentInfo4 = (TextView) investmentView.findViewById(R.id.investment_title_info4);
        textInvestmentInfo5 = (TextView) investmentView.findViewById(R.id.investment_title_info5);
        textInvestmentInfo6 = (TextView) investmentView.findViewById(R.id.investment_title_info6);
        textInvestmentInfo7 = (TextView) investmentView.findViewById(R.id.investment_title_info7);
        textInvestmentValueInfo1 = (TextView) investmentView.findViewById(R.id.investment_value_info1);
        textInvestmentValueInfo2 = (TextView) investmentView.findViewById(R.id.investment_value_info2);
        textInvestmentValueInfo3 = (TextView) investmentView.findViewById(R.id.investment_value_info3);
        textInvestmentValueInfo4 = (TextView) investmentView.findViewById(R.id.investment_value_info4);
        textInvestmentValueInfo5 = (TextView) investmentView.findViewById(R.id.investment_value_info5);
        textInvestmentValueInfo6 = (TextView) investmentView.findViewById(R.id.investment_value_info6);
        textInvestmentValueInfo7 = (TextView) investmentView.findViewById(R.id.investment_value_info7);
        textInvestmentDownInfo1 = (TextView) investmentView.findViewById(R.id.investment_title_downinfo1);
        textInvestmentDownInfo2 = (TextView) investmentView.findViewById(R.id.investment_title_downinfo2);
        textInvestmentDownInfo3 = (TextView) investmentView.findViewById(R.id.investment_title_downinfo3);
        textInvestmentDownInfo4 = (TextView) investmentView.findViewById(R.id.investment_title_downinfo4);
        textInvestmentDownInfo5 = (TextView) investmentView.findViewById(R.id.investment_title_downinfo5);
        textInvestmentValueDownInfo1 = (TextView) investmentView.findViewById(R.id.investment_value_downinfo1);
        textInvestmentValueDownInfo2 = (TextView) investmentView.findViewById(R.id.investment_value_downinfo2);
        textInvestmentValueDownInfo3 = (TextView) investmentView.findViewById(R.id.investment_value_downinfo3);
        textInvestmentValueDownInfo4 = (TextView) investmentView.findViewById(R.id.investment_value_downinfo4);
        textInvestmentValueDownInfo5 = (TextView) investmentView.findViewById(R.id.investment_value_downinfo5);

        linearInvestmentContainer.setVisibility(View.GONE);
        progressBarInvestment.setVisibility(View.VISIBLE);

        font = Typeface.createFromAsset(getActivity().getAssets(), "font/DINPro-Medium.otf");
        textInvestmentTitle.setTypeface(font);
        textInvestmentFoundName.setTypeface(font);
        textInvestmentWhatsIs.setTypeface(font);
        textInvestmentDefinition.setTypeface(font);
        textInvestmentRiskDegree.setTypeface(font);
        textInvestmentMoreInfoTitle.setTypeface(font);
        textInvestmentMoreInfoTitleFund.setTypeface(font);
        textInvestmentMoreInfoTitleCdi.setTypeface(font);
        textInvestmentMoreInfoTitleMonth.setTypeface(font);
        textInvestmentMoreInfoTitleYear.setTypeface(font);
        textInvestmentMoreInfoTitleTwelve.setTypeface(font);
        textInvestmentMoreInfoTitleFundMonth.setTypeface(font);
        textInvestmentMoreInfoTitleFundYear.setTypeface(font);
        textInvestmentMoreInfoTitleFundTwelve.setTypeface(font);
        textInvestmentMoreInfoTitleCdiMonth.setTypeface(font);
        textInvestmentMoreInfoTitleCdiYear.setTypeface(font);
        textInvestmentMoreInfoTitleCdiTwelve.setTypeface(font);
        textInvestmentInfo1.setTypeface(font);
        textInvestmentInfo2.setTypeface(font);
        textInvestmentInfo3.setTypeface(font);
        textInvestmentInfo4.setTypeface(font);
        textInvestmentInfo5.setTypeface(font);
        textInvestmentInfo6.setTypeface(font);
        textInvestmentInfo7.setTypeface(font);
        textInvestmentValueInfo1.setTypeface(font);
        textInvestmentValueInfo2.setTypeface(font);
        textInvestmentValueInfo3.setTypeface(font);
        textInvestmentValueInfo4.setTypeface(font);
        textInvestmentValueInfo5.setTypeface(font);
        textInvestmentValueInfo6.setTypeface(font);
        textInvestmentValueInfo7.setTypeface(font);
        textInvestmentDownInfo1.setTypeface(font);
        textInvestmentDownInfo2.setTypeface(font);
        textInvestmentDownInfo3.setTypeface(font);
        textInvestmentDownInfo4.setTypeface(font);
        textInvestmentDownInfo5.setTypeface(font);
        textInvestmentValueDownInfo1.setTypeface(font);
        textInvestmentValueDownInfo2.setTypeface(font);
        textInvestmentValueDownInfo3.setTypeface(font);
        textInvestmentValueDownInfo4.setTypeface(font);
        textInvestmentValueDownInfo5.setTypeface(font);

        return investmentView;

    }


    @Override
    public void addTextInfos(Screen item) {

        screenList.add(item);

        linearInvestmentContainer.setVisibility(View.VISIBLE);
        progressBarInvestment.setVisibility(View.GONE);

        for(int i = 0; i < screenList.size(); i++){

            textInvestmentTitle.setText(screenList.get(i).getTitle());
            textInvestmentFoundName.setText(screenList.get(i).getFundName());
            textInvestmentWhatsIs.setText(screenList.get(i).getWhatIs());
            textInvestmentDefinition.setText(screenList.get(i).getDefinition());
            textInvestmentRiskDegree.setText(screenList.get(i).getRiskTitle());

            riskDegree =  screenList.get(i).getRisk();

            switch (riskDegree){

                case 1:
                    linearInvestmentRisk1.setBackground(getResources().getDrawable(R.drawable.risk_selected1));
                    break;
                case 2:
                    linearInvestmentRisk2.setBackground(getResources().getDrawable(R.drawable.risk_selected2));
                    break;
                case 3:
                    linearInvestmentRisk3.setBackground(getResources().getDrawable(R.drawable.risk_selected3));
                    break;
                case 4:

                    linearInvestmentRisk4.setBackground(getResources().getDrawable(R.drawable.risk_selected4));
                    break;
                case 5:
                    linearInvestmentRisk5.setBackground(getResources().getDrawable(R.drawable.risk_selected5));
                    break;
            }

            textInvestmentMoreInfoTitle.setText(screenList.get(i).getInfoTitle());

            textInvestmentMoreInfoTitleFundMonth.setText(String.valueOf(screenList.get(i).getMoreInfo().getMonth().getFund()) + "%");
            textInvestmentMoreInfoTitleCdiMonth.setText(String.valueOf(screenList.get(i).getMoreInfo().getMonth().getCdi()) + "%");
            textInvestmentMoreInfoTitleFundYear.setText(String.valueOf(screenList.get(i).getMoreInfo().getYear().getFund()) + "%");
            textInvestmentMoreInfoTitleCdiYear.setText(String.valueOf(screenList.get(i).getMoreInfo().getYear().getCdi()) + "%");
            textInvestmentMoreInfoTitleFundTwelve.setText(String.valueOf(screenList.get(i).getMoreInfo().getTwelve().getFund()) + "%");
            textInvestmentMoreInfoTitleCdiTwelve.setText(String.valueOf(screenList.get(i).getMoreInfo().getTwelve().getCdi()) + "%");

            //Percorrendo o atributo Info e preenchendo a lista infoList
            for(Info info : item.getInfo()){
                infoList.add(new Info(info.getName(), info.getData()));
            }

            //Percorrendo o atributo DownInfo e preenchendo a lista downInfoList
            for(DownInfo down : item.getDownInfo()){
                downInfoList.add(new DownInfo(down.getName(), down.getData()));
            }


            for(int x = 0; x < infoList.size(); x++){
                arrayInfoName[x] = infoList.get(x).getName();
                arrayInfoData[x] = infoList.get(x).getData();
            }

            textInvestmentInfo1.setText(arrayInfoName[0]);
            textInvestmentValueInfo1.setText(arrayInfoData[0]);
            textInvestmentInfo2.setText(arrayInfoName[1]);
            textInvestmentValueInfo2.setText(arrayInfoData[1]);
            textInvestmentInfo3.setText(arrayInfoName[2]);
            textInvestmentValueInfo3.setText(arrayInfoData[2]);
            textInvestmentInfo4.setText(arrayInfoName[3]);
            textInvestmentValueInfo4.setText(arrayInfoData[3]);
            textInvestmentInfo5.setText(arrayInfoName[4]);
            textInvestmentValueInfo5.setText(arrayInfoData[4]);
            textInvestmentInfo6.setText(arrayInfoName[5]);
            textInvestmentValueInfo6.setText(arrayInfoData[5]);
            textInvestmentInfo7.setText(arrayInfoName[6]);
            textInvestmentValueInfo7.setText(arrayInfoData[6]);


            for(int y = 0; y < downInfoList.size(); y++){
                arrayDownInfoName[y] = downInfoList.get(y).getName();
                arrayDownInfoData[y] = downInfoList.get(y).getData();
            }


            textInvestmentDownInfo1.setText(arrayDownInfoName[0]);
            textInvestmentDownInfo2.setText(arrayDownInfoName[1]);
            textInvestmentDownInfo3.setText(arrayDownInfoName[2]);
            textInvestmentDownInfo4.setText(arrayDownInfoName[3]);
            textInvestmentDownInfo5.setText(arrayDownInfoName[4]);

            if(arrayDownInfoData.equals(null)){
                textInvestmentValueDownInfo1.setText(arrayDownInfoData[0]);
                textInvestmentValueDownInfo2.setText(arrayDownInfoData[1]);
                textInvestmentValueDownInfo3.setText(arrayDownInfoData[2]);
                textInvestmentValueDownInfo4.setText(arrayDownInfoData[3]);
                textInvestmentValueDownInfo5.setText(arrayDownInfoData[4]);
            } else {
                textInvestmentValueDownInfo1.setText("Baixar");
                textInvestmentValueDownInfo2.setText("Baixar");
                textInvestmentValueDownInfo3.setText("Baixar");
                textInvestmentValueDownInfo4.setText("Baixar");
                textInvestmentValueDownInfo5.setText("Baixar");

            }

        }

    }

    @Override
    public void onStart() {
        super.onStart();

        investmentPresenter.attachView(this);
        investmentPresenter.requestInvestmentInfos();

    }

    @Override
    public void onStop() {

        investmentPresenter.detachView();
        super.onStop();
    }
}