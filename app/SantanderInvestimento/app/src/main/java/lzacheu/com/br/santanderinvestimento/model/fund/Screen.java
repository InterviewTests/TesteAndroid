package lzacheu.com.br.santanderinvestimento.model.fund;

import java.util.List;

/**
 * Created by luiszacheu on 6/16/18.
 */

public class Screen  {

    private String title;
    private String fundName;
    private String whatIs;
    private String definition;
    private String riskTitle;
    private Integer risk;
    private String infoTitle;
    private MoreInfo moreInfo;
    private List<Info> info = null;
    private List<DownInfo> downInfo = null;

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

    public Integer getRisk() {
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

    public List<DownInfo> getDownInfo() {
        return downInfo;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "title='" + title + '\'' +
                ", fundName='" + fundName + '\'' +
                ", whatIs='" + whatIs + '\'' +
                ", definition='" + definition + '\'' +
                ", riskTitle='" + riskTitle + '\'' +
                ", risk=" + risk +
                ", infoTitle='" + infoTitle + '\'' +
                ", moreInfo=" + moreInfo +
                ", info=" + info +
                ", downInfo=" + downInfo +
                '}';
    }
}
