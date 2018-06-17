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
