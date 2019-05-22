package br.banco.services.fund.domain.national;

import android.os.Parcelable;

import java.util.ArrayList;

import br.banco.services.fund.domain.FundTemplate;

public  class Fund   {



    public static final int MOREINFO_TYPE = 0;

    private String Title;
    private String FundName;
    private String WhatIs;
    private String Definition;
    private String RiskTitle;
    private int Risk;
    private String InfoTitle;

    // Arrays

    private ArrayList MoreInfo;
    private ArrayList Info;
    private ArrayList DownInfo;

    public Fund() {

    }

    // String


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getFundName() {
        return FundName;
    }

    public void setFundName(String fundName) {
        FundName = fundName;
    }

    public String getWhatIs() {
        return WhatIs;
    }

    public void setWhatIs(String whatIs) {
        WhatIs = whatIs;
    }

    public String getDefinition() {
        return Definition;
    }

    public void setDefinition(String definition) {
        Definition = definition;
    }

    public String getRiskTitle() {
        return RiskTitle;
    }

    public void setRiskTitle(String riskTitle) {
        RiskTitle = riskTitle;
    }

    public int getRisk() {
        return Risk;
    }

    public void setRisk(int risk) {
        Risk = risk;
    }

    public String getInfoTitle() {
        return InfoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        InfoTitle = infoTitle;
    }

    //Arrays

    public ArrayList getMoreInfo() {
        return MoreInfo;
    }

    public void setMoreInfo(ArrayList moreInfo) {
        MoreInfo = moreInfo;
    }

    public ArrayList getInfo() {
        return Info;
    }

    public void setInfo(ArrayList info) {
        Info = info;
    }

    public ArrayList getDownInfo() {
        return DownInfo;
    }

    public void setDownInfo(ArrayList downInfo) {
        DownInfo = downInfo;
    }


    // risk

    public String[][][] getRiskList (int risk, FundTemplate model){

        String[][][] listGet ={};

        /*
        String listSet[][][] = new String[][][]
                {
                    {
                        { listGet[risk][0][0],listGet[risk][0][1] },
                        { listGet[risk][1][0],listGet[risk][1][1] },
                        { listGet[risk][2][0],listGet[risk][2][1] }
                    }
                };
*/
        return listGet;

    }


    public void setFundTemplate() {

        //FromType fund = new FromType();

        this.Title = "@Fundos de investimento" ;
        this.FundName = "@Vinci Valorem FI Multimercado" ;;
        this.WhatIs = "@O que é?" ;;
        this.Definition = "@O Fundo tem por objetivo proporcionar aos seus cotistas rentabilidade no longo prazo através de investimentos." ;;
        this.RiskTitle =  "@Grau de risco do investimento" ;;
        this.Risk = 4 ;
        this.InfoTitle = "@Mais informações sobre o investimento" ;;
        // Arrays

        this.MoreInfo = null ;
        this.Info = null ;
        this.DownInfo = null ;
    }



}
