package br.com.itamarlourenco.santandertecnologia_testeandroid.model.interfaces;

import java.util.ArrayList;

import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Funds;

public interface IFunds extends IBase{

    String getTitle();

    String getFundName();

    String getWhatIs();

    String getDefinition();

    String getRiskTitle();

    String getRisk();

    String getInfoTitle();

    Funds.MoreInfo getMoreInfo();

    ArrayList<Funds.InfoSchema> getInfo();

    ArrayList<Funds.InfoSchema> getDownInfo();

}
