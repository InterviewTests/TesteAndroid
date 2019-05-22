package br.banco.services.fund.domain.national;

import android.os.Parcelable;

import java.util.ArrayList;

public abstract class FundParcel implements Parcelable {


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

    public FundParcel() {
        //setFundTemplate();
    }






}
