package com.nataliafavero.santander.data.model;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by nataliafavero on 10/09/18.
 */

public class ScreenResponse {

    @Expose
    private String title;
    @Expose
    private String fundName;
    @Expose
    private String whatIs;
    @Expose
    private String definition;
    @Expose
    private String riskTitle;
    @Expose
    private int risk;
    @Expose
    private String infoTitle;
    @Expose
    private MoreInfo moreInfo;
    @Expose
    private List<Info> info;

    public String getTitle() {
        return title;
    }

    public String getFundName() {
        return fundName;
    }

    public String getWhatIs() {
        return whatIs;
    }

    public String getDefinition() {
        return definition;
    }

    public String getRiskTitle() {
        return riskTitle;
    }

    public int getRisk() {
        return risk;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public MoreInfo getMoreInfo() {
        return moreInfo;
    }

    public List<Info> getInfo() {
        return info;
    }

    class MoreInfo {
        @Expose
        private MoreInfoDetail month;
        @Expose
        private MoreInfoDetail year;
        @Expose
        private MoreInfoDetail twelveMoths;

        public MoreInfoDetail getMonth() {
            return month;
        }

        public MoreInfoDetail getYear() {
            return year;
        }

        public MoreInfoDetail getTwelveMoths() {
            return twelveMoths;
        }
    }

    class MoreInfoDetail {
        @Expose
        private Double fund;
        @Expose
        private Double CDI;

        public Double getFund() {
            return fund;
        }

        public Double getCDI() {
            return CDI;
        }
    }

    class Info {
        @Expose
        private String name;
        @Expose
        private String data;

        public String getName() {
            return name;
        }

        public String getData() {
            return data;
        }
    }
}
