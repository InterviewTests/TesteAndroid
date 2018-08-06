package br.com.iomarsantos.testeandroid.ui.entity;

import java.util.ArrayList;
import java.util.List;

import br.com.iomarsantos.testeandroid.data.model.ScreenResponse;
import br.com.iomarsantos.testeandroid.entity.InvestmentFund;
import br.com.iomarsantos.testeandroid.entity.Month;
import br.com.iomarsantos.testeandroid.entity.MoreInfo;
import br.com.iomarsantos.testeandroid.entity.TwelveMonths;
import br.com.iomarsantos.testeandroid.entity.Year;

public class MockModelScreen {

    public static ScreenResponse newScreenResponse(int size) {
        ScreenResponse screenResponse = new ScreenResponse();
        screenResponse.setScreen(newInvestmentFund());
        return screenResponse;
    }

    public static InvestmentFund newInvestmentFund() {
        InvestmentFund investmentFund = new InvestmentFund();
        investmentFund.setTitle("Fundos de investimento");
        investmentFund.setFundName("Vinci Valorem FI Multimercado");
        investmentFund.setWhatIs("O que é?");
        investmentFund.setDefinition("O Fundo tem por objetivo proporcionar aos seus cotistas rentabilidade no longo prazo através de investimentos.");
        investmentFund.setRiskTitle("Grau de risco do investimento");
        investmentFund.setRisk(4);
        investmentFund.setInfoTitle("Mais informações sobre o investimento");
        investmentFund.setMoreInfo(newMoreInfo());

        return investmentFund;
    }

    public static MoreInfo newMoreInfo() {
        MoreInfo moreInfo = new MoreInfo();
        moreInfo.setMonth(newMonth());
        moreInfo.setYear(newYear());
        moreInfo.setTwelveMonths(newTwelveMonths());
        return moreInfo;
    }

    public static Month newMonth() {
        Month month = new Month();
        month.setFund(0.3F);
        month.setCDI(0.3F);
        return month;
    }

    public static Year newYear() {
        Year year = new Year();
        year.setFund(13.01F);
        year.setCDI(12.08F);
        return year;
    }

    public static TwelveMonths newTwelveMonths() {
        TwelveMonths twelveMonths = new TwelveMonths();
        twelveMonths.setFund(17.9F);
        twelveMonths.setCDI(17.6F);
        return twelveMonths;
    }

    public static List<InvestmentFund> newInvestmentFundlList(int size) {
        ArrayList<InvestmentFund> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(newInvestmentFund());
        }
        return list;
    }

}
