package br.banco.services.fund.option;

import android.util.Log;

import java.util.ArrayList;

public class FundOption {

    // SIMULACAO;

    private String genre;
    private String year;


    // SIMPLES;

    private String Title;
    private String FundName;
    private String WhatIs;
    private String Definition;
    private String RiskTitle;
    private int Risk;
    private String InfoTitle;

    // ARRAYS

    private ArrayList MoreInfo;
    private ArrayList Info;
    private ArrayList DownInfo;


    public FundOption() {


    }

    public FundOption(String title, String genre, String year) {

        this.Title = title;
        this.genre = genre;
        this.year = year;
    }

    // strings

    public String getTitle() {
        return Title;
    }

    public void setTitle(String name) {
        this.Title = name;
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


    /*------------------*/



    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    // tests

    public void showArray(String TAG, String FUNCTION, ArrayList listValues){

        Log.i(TAG, "@TAG: " + TAG + " / " + FUNCTION);

        if(listValues.size() == 9 || TAG != null) {

            Log.i(TAG, "Title = " + listValues.get(0));
            Log.i(TAG, "FundName = " + listValues.get(1));
            Log.i(TAG, "WhatIs = " + listValues.get(2));
            Log.i(TAG, "Definition = " + listValues.get(3));
            Log.i(TAG, "RiskTitle = " + listValues.get(4));
            Log.i(TAG, "Risk = " + listValues.get(5));
            Log.i(TAG, "InfoTitle = " + listValues.get(6));

            Log.i(TAG, "moreInfo = " + listValues.get(6));
            Log.i(TAG, "info = " + listValues.get(7));
            Log.i(TAG, "downInfo = " + listValues.get(8));

        }else{
            Log.e(TAG, "Erro:  @TAG: " + TAG + " / " + FUNCTION + " lista incompleta!" );
        }
    }



    public void showClass(String TAG, String FUNCTION, FundOption fund){

        Log.i(TAG, "@TAG: " + TAG + " / " + FUNCTION);

        if( fund!=null && TAG != null) {

            Log.d(TAG, " fund.getTitle() = " + fund.getTitle());
            Log.d(TAG, " fund.getFundName() = " + fund.getFundName());
            Log.d(TAG, " fund.getWhatIs() = " + fund.getWhatIs());
            Log.d(TAG, " fund.getDefinition() = " + fund.getDefinition());
            Log.d(TAG, " fund.getRiskTitle() = " + fund.getRiskTitle());
            Log.d(TAG, " fund.getRisk() = " + fund.getRisk());
            Log.d(TAG, " fund.getInfoTitle() = " + fund.getInfoTitle());

             Log.d(TAG, " fund.getMoreInfo() = " + fund.getMoreInfo());
             Log.d(TAG, " fund.getInfo() = " + fund.getInfo());
             Log.d(TAG, " fund.getDownInfo() = " + fund.getDownInfo());

        }else{
            Log.e(TAG, "Erro:  @TAG: " + TAG + " / " + FUNCTION + " objeto nulo!" );
        }
    }





}
