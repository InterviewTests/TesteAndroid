package lzacheu.com.br.santanderinvestimento.data;

import java.util.ArrayList;
import java.util.List;

import lzacheu.com.br.santanderinvestimento.model.fund.DownInfo;
import lzacheu.com.br.santanderinvestimento.model.fund.Fund;
import lzacheu.com.br.santanderinvestimento.model.fund.Info;
import lzacheu.com.br.santanderinvestimento.model.fund.MoreInfo;
import lzacheu.com.br.santanderinvestimento.model.fund.MoreInfoDetail;
import lzacheu.com.br.santanderinvestimento.model.fund.Screen;

public class FakeFundRepository  extends FundRepository implements FundDataSource {

    private Screen screen;

    @Override
    public void getFunds(LoadFundCallback callback) {
        MoreInfoDetail month = new MoreInfoDetail(0.3d, 0.4d);
        MoreInfoDetail year = new MoreInfoDetail(0.3d, 0.4d);
        MoreInfoDetail _12month = new MoreInfoDetail(0.3d, 0.4d);

        List<Info> infos = new ArrayList<>();
        infos.add(new Info("teste", "Esse é um teste!"));

        List<DownInfo> downInfos = new ArrayList<>();
        downInfos.add(new DownInfo("teste", "Esse é um teste!"));


        screen = new Screen("Tela de investimentos",
                "Fundo ABC", "O que é? ", "Lorem ipsum Lorem ipsum",
                "Qual o risco", 3, "Lorem ipsum",
                new MoreInfo(month, year, _12month), infos, downInfos);

        callback.onFundsLoaded(screen);
    }
}