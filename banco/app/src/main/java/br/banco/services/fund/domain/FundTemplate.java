package br.banco.services.fund.domain;

import android.util.Log;

import java.util.ArrayList;

import br.banco.services.fund.FundModelTemplate;

public class FundTemplate {
    private String title;
    private String genre;
    private String year;


   // private String Title;

    private String FundName;
    private String WhatIs;
    private String Definition;
    private String RiskTitle;
    private int Risk;
    private String InfoTitle;

    /*--------*/

    private ArrayList MoreInfo;
    private ArrayList Info;
    private ArrayList DownInfo;


    public FundTemplate() {

       // this.title = title;
        // this.genre = genre;
        //this.year = year;
    }

    // strings

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }




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




    public String[][][] getFUNDlist (int fundID, FundModelTemplate model){

       // DataBaseTemplate model = new DataBaseTemplate();
        // 0 = fund , 1 = label, 2 = data

        String[][][] listGet = model.listMoreInfoTemplate;
        String listSet[][][] = new String[][][]
                {
                    {
                        { listGet[fundID][0][0],listGet[fundID][0][1] },
                        { listGet[fundID][1][0],listGet[fundID][1][1] },
                        { listGet[fundID][2][0],listGet[fundID][2][1] }
                    }
                };

        return listSet;
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



    public void showClass(String TAG, String FUNCTION, FundTemplate fund){

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





    public void showListDetail(int fundID){

        FundModelTemplate model = new FundModelTemplate();
        String[][][] list = model.listMoreInfoTemplate;
        String listCopy[][][] = new String[][][]
                {
                    {
                        { list[fundID][0][0], list[fundID][0][1]  },
                        { list[fundID][0][0],list[fundID][1][1] },
                        { list[fundID][0][0],list[fundID][2][1] }
                    }
                };

        if(listCopy.length > 0) {

            Log.d("FUND", "FUNDO DETALHE / showListMOREINFOdetail => [" + fundID + "] ");

            Log.d("FUND", "NOME =  " + listCopy[0][0][0] + " : " + listCopy[0][0][1] );
            Log.d("FUND", "MES = " + listCopy[0][1][0] + " : " + listCopy[0][1][1] );
            Log.d("FUND", "12MESES =  " + listCopy[0][2][0] + " : " + listCopy[0][2][1] );
            Log.d("FUND", " ----------------------------------------- ");

        }else{

            Log.d("FUND","showListMOREINFOdetail -> listMoreInfoTemplate -> VAZIA ");
        }
    }






}
