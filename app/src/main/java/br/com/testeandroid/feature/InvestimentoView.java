package br.com.testeandroid.feature;

import java.util.ArrayList;

import br.com.testeandroid.model.DownInfo;
import br.com.testeandroid.model.Info;
import br.com.testeandroid.model.MoreInfo;
import br.com.testeandroid.model.Screen;

public interface InvestimentoView {

    void setScreen(Screen screen);
    void setInfoInvestimento(MoreInfo moreInfo);
    void setRecycleViewAdapter(ArrayList<Info> infos, ArrayList<DownInfo> downInfos);
    void setRiskInfo(Integer risk);
    void showProgress();
    void finishProgress();
    void ErroLoading();
}
