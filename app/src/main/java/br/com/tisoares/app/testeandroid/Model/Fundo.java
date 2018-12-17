package br.com.tisoares.app.testeandroid.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by TIAGO SOARES on 14/12/2018.
 */
public class Fundo {

    public Fundo() {
        infos = new ArrayList<Info>();
        downInfos = new ArrayList<Info>();
    }

    private String title;
    private String fundName;
    private String whatIs;
    private String definition;
    private String riskTitle;
    private String infoTitle;
    //risk": pode ser um int de 1 a 5
    private int risk;

    private MoreInfo moreInfo;

    private ArrayList<Info> infos;

    private ArrayList<Info> downInfos;

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        if (risk >= 1 && risk <= 5) {
            this.risk = risk;
        } else {
            throw new IllegalArgumentException("risk não é um valor permitido");
        }
    }

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

    public ArrayList<Info> getInfos() {
        return infos;
    }

    public void setInfos(ArrayList<Info> infos) {
        this.infos = infos;
    }

    public ArrayList<Info> getDownInfos() {
        return downInfos;
    }

    public void setDownInfos(ArrayList<Info> downInfos) {
        this.downInfos = downInfos;
    }

    /**
     *  Monta o Objeto atraves da string do servidor
     * @param response String contendo um objeto JSON para carregar as propriedades
     * @return retorno se deu certo a criacao do objeto
     */
    public boolean createByJSON(String response){

        try {
            JSONObject j = new JSONObject(response);
            JSONObject jScreen = j.getJSONObject("screen");

            this.title = jScreen.getString("title");
            this.fundName = jScreen.getString("fundName");
            this.whatIs = jScreen.getString("whatIs");
            this.definition = jScreen.getString("definition");
            this.riskTitle = jScreen.getString("riskTitle");
            this.setRisk(jScreen.getInt("risk"));
            this.riskTitle = jScreen.getString("riskTitle");

            // moreInfo
            this.moreInfo = new MoreInfo();
            this.moreInfo.setMonth(new Periodo());
            this.moreInfo.setYear(new Periodo());
            this.moreInfo.set_12months(new Periodo());

            JSONObject jMoreInfo = jScreen.getJSONObject("moreInfo");

            this.moreInfo.getMonth().setFund(jMoreInfo.getJSONObject("month").getDouble("fund"));
            this.moreInfo.getMonth().setCDI(jMoreInfo.getJSONObject("month").getDouble("CDI"));
            this.moreInfo.getYear().setFund(jMoreInfo.getJSONObject("year").getDouble("fund"));
            this.moreInfo.getYear().setCDI(jMoreInfo.getJSONObject("year").getDouble("CDI"));
            this.moreInfo.get_12months().setFund(jMoreInfo.getJSONObject("12months").getDouble("fund"));
            this.moreInfo.get_12months().setCDI(jMoreInfo.getJSONObject("12months").getDouble("CDI"));

            /* ############################################################################################# */

            // info
            JSONArray arryInfo = jScreen.getJSONArray("info");

            for (int i = 0; i < arryInfo.length(); i++){
                JSONObject jInfo = arryInfo.getJSONObject(i);
                this.infos.add(new Info(jInfo.getString("name"), jInfo.getString("data")));
            }

            /* ############################################################################################# */

            // downInfo
            JSONArray arryDownInfo = jScreen.getJSONArray("downInfo");

            for (int i = 0; i < arryDownInfo.length(); i++){
                JSONObject jDownInfo = arryDownInfo.getJSONObject(i);

                String data = "";
                if (!jDownInfo.isNull("data")){
                    data = jDownInfo.getString("data");
                }

                this.infos.add(new Info(jDownInfo.getString("name"), data));
            }

            /* ############################################################################################# */
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
