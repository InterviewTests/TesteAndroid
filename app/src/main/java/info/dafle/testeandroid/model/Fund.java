package info.dafle.testeandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Fund {

    private Screen screen;

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public class Screen {

        private String title;
        private String fundName;
        private String whatIs;
        private String definition;
        private String riskTitle;
        private int risk;
        private String infoTitle;
        private MoreInfo moreInfo;
        private List<Info> info;
        private List<Info> downInfo;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFundName() {
            return fundName;
        }

        public void setFundName(String fundName) {
            this.fundName = fundName;
        }

        public String getWhatIs() {
            return whatIs;
        }

        public void setWhatIs(String whatIs) {
            this.whatIs = whatIs;
        }

        public String getDefinition() {
            return definition;
        }

        public void setDefinition(String definition) {
            this.definition = definition;
        }

        public String getRiskTitle() {
            return riskTitle;
        }

        public void setRiskTitle(String riskTitle) {
            this.riskTitle = riskTitle;
        }

        public int getRisk() {
            return risk;
        }

        public void setRisk(int risk) {
            this.risk = risk;
        }

        public String getInfoTitle() {
            return infoTitle;
        }

        public void setInfoTitle(String infoTitle) {
            this.infoTitle = infoTitle;
        }

        public MoreInfo getMoreInfo() {
            return moreInfo;
        }

        public void setMoreInfo(MoreInfo moreInfo) {
            this.moreInfo = moreInfo;
        }

        public List<Info> getInfo() {
            return info;
        }

        public void setInfo(List<Info> info) {
            this.info = info;
        }

        public List<Info> getDownInfo() {
            return downInfo;
        }

        public void setDownInfo(List<Info> downInfo) {
            this.downInfo = downInfo;
        }

        public class MoreInfo {

            private Investiment month;
            private Investiment year;
            @SerializedName("12months")
            private Investiment months12;

            public Investiment getMonth() {
                return month;
            }

            public void setMonth(Investiment month) {
                this.month = month;
            }

            public Investiment getYear() {
                return year;
            }

            public void setYear(Investiment year) {
                this.year = year;
            }

            public Investiment getMonths12() {
                return months12;
            }

            public void setMonths12(Investiment months12) {
                this.months12 = months12;
            }

            public class Investiment {

                private double fund;
                private double CDI;

                public double getFund() {
                    return fund;
                }

                public void setFund(double fund) {
                    this.fund = fund;
                }

                public double getCDI() {
                    return CDI;
                }

                public void setCDI(double CDI) {
                    this.CDI = CDI;
                }
            }
        }

        public class Info {

            private String name;
            private String data;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }
        }

        public String[] titles() {

            return new String[]{
                 title,
                 fundName,
                 whatIs,
                 definition,
                 riskTitle
            };
        }
    }
}
