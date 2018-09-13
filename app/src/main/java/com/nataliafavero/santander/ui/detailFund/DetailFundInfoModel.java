package com.nataliafavero.santander.ui.detailFund;

/**
 * Created by nataliafavero on 12/09/18.
 */

public class DetailFundInfoModel {

    private String name;
    private String fund;
    private String data;

    public DetailFundInfoModel(String name, String fund, String data) {
        this.name = name;
        this.fund = fund;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
