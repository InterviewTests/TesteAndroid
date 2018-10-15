package br.com.itamarlourenco.santandertecnologia_testeandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import br.com.itamarlourenco.santandertecnologia_testeandroid.exceptions.ValidateException;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.interfaces.IFunds;

public class Funds implements IFunds {
    private String title;
    private String fundName;
    private String whatIs;
    private String definition;
    private String riskTitle;
    private String risk;
    private String infoTitle;
    private MoreInfo moreInfo;
    private ArrayList<InfoSchema> info;
    private ArrayList<InfoSchema> downInfo;

    @Override
    public boolean isValidData() throws ValidateException {
        return false;
    }

    public class MoreInfo {
        private MoreInfoSchema month;
        private MoreInfoSchema year;
        @SerializedName("12months")
        private MoreInfoSchema months12;

        public MoreInfoSchema getMonth() {
            return month;
        }

        public MoreInfoSchema getYear() {
            return year;
        }

        public MoreInfoSchema getMonths12() {
            return months12;
        }

        public class MoreInfoSchema {
            private float fund;
            private float CDI;

            public float getFund() {
                return fund;
            }

            public float getCDI() {
                return CDI;
            }
        }
    }

    public class InfoSchema {
        private String name;
        private String data;

        public String getName() {
            return name;
        }

        public String getData() {
            return data;
        }
    }

    @Override
    public String getTitle() {
        return title;
    }


    public String getFundName() {
        return fundName;
    }

    @Override
    public String getWhatIs() {
        return whatIs;
    }

    @Override
    public String getDefinition() {
        return definition;
    }

    @Override
    public String getRiskTitle() {
        return riskTitle;
    }

    @Override
    public String getRisk() {
        return risk;
    }

    @Override
    public String getInfoTitle() {
        return infoTitle;
    }

    @Override
    public MoreInfo getMoreInfo() {
        return moreInfo;
    }

    @Override
    public ArrayList<InfoSchema> getInfo() {
        return info;
    }

    @Override
    public ArrayList<InfoSchema> getDownInfo() {
        return downInfo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public void setWhatIs(String whatIs) {
        this.whatIs = whatIs;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setRiskTitle(String riskTitle) {
        this.riskTitle = riskTitle;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public void setMoreInfo(MoreInfo moreInfo) {
        this.moreInfo = moreInfo;
    }

    public void setInfo(ArrayList<InfoSchema> info) {
        this.info = info;
    }

    public void setDownInfo(ArrayList<InfoSchema> downInfo) {
        this.downInfo = downInfo;
    }

    public class Screen{
        private Funds screen;

        public Funds getScreen() {
            return screen;
        }
    }
}
