package br.banco.services.fund.domain.national;

import java.util.ArrayList;

import br.banco.services.fund.domain.national.ScreenFund;
import br.banco.services.fund.domain.national.ScreenFundTemplate;
import br.banco.services.fund.domain.national.Fund;
import br.banco.services.fund.domain.national.DownInfo;
import br.banco.services.fund.domain.national.Info;
import br.banco.services.fund.domain.national.MoreInfo;

public class ScreenFundTemplate {

    public static final String ID_KEY = "id";
    public static final String KEY_FAVORITE = "key-favorite";

    /**
     *  Definicoes de layout
     *
     */

    public static final int HEADER_TYPE = 10;
    public static final int BODY_TYPE = 11;
    public static final int FOOTER_TYPE = 12;

    public static final int FUND_TYPE = 0;
    public static final int MOREINFO_TYPE = 1;
    public static final int INFO_TYPE = 2;
    public static final int DOWNLOAD_TYPE = 3;


    public int origin;
    public int location;

    public int type;
    public int data;
    public String text;

    // ---------------- LAYOUT / TELA -----------------------

    public String Title;
    public String Subtitle;

    public String Session;
    public String Description;

    public String ChartTitle;
    public ArrayList Chart;

    public String AdressLink;
    public String AdressLabel;

    public String TableTitle;
    public String TableValue;


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSubtitle() {
        return Subtitle;
    }

    public void setSubtitle(String subtitle) {
        Subtitle = subtitle;
    }

    public String getSession() {
        return Session;
    }

    public void setSession(String session) {
        Session = session;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getChartTitle() {
        return ChartTitle;
    }

    public void setChartTitle(String chartTitle) {
        ChartTitle = chartTitle;
    }

    public ArrayList getChart() {

       // Chart.add("03%");
       // Chart.add("12%");
       // Chart.add("17%");

        return Chart;
    }

    public void setChart(ArrayList chart) {

       // chart.add("03%");
       // chart.add("12%");
      //  chart.add("17%");

        Chart = chart;
    }

    public String getAdressLink() {
        return AdressLink;
    }

    public void setAdressLink(String adressLink) {
        AdressLink = adressLink;
    }

    public String getAdressLabel() {
        return AdressLabel;
    }

    public void setAdressLabel(String adressLabel) {
        AdressLabel = adressLabel;
    }

    public String getTableTitle() {
        return TableTitle;
    }

    public void setTableTitle(String tableTitle) {
        TableTitle = tableTitle;
    }

    public String getTableValue() {
        return TableValue;
    }

    public void setTableValue(String tableValue) {
        TableValue = tableValue;
    }

    public  ScreenFundTemplate(Object object){



    }



    /**
     *  Definicoes de dados IMAGEM, TEXTO, ETC
     *
     */

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }




    /**
     *  Definicoes de layout - CAMPOS
     *
     */


    int id;
    String Header;
    String Body;
    String Footer;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return Header;
    }

    public void setHeader(String header) {
        Header = header;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getFooter() {
        return Footer;
    }

    public void setFooter(String footer) {
        Footer = footer;
    }















}
