package br.com.iomarsantos.testeandroid.ui.entity;

import org.junit.Test;

import br.com.iomarsantos.testeandroid.entity.Month;
import br.com.iomarsantos.testeandroid.entity.MoreInfo;
import br.com.iomarsantos.testeandroid.entity.TwelveMonths;
import br.com.iomarsantos.testeandroid.entity.Year;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InvestimentoTest {

    @Test
    public void Should_Return_Title_When_Received_Title() {
        String titleReturned = MockModelScreen.newInvestmentFund().getTitle();
        assertThat(titleReturned, is(equalTo("Fundos de investimento")));
    }

    @Test
    public void Should_Return_Fund_Name_When_Received_Fund_Name() {
        String fundNameReturned = MockModelScreen.newInvestmentFund().getFundName();
        assertThat(fundNameReturned, is(equalTo("Vinci Valorem FI Multimercado")));
    }

    @Test
    public void Should_Return_WhatIs_When_Received_WhatIs() {
        String whatIsReturned = MockModelScreen.newInvestmentFund().getWhatIs();
        assertThat(whatIsReturned, is(equalTo("O que é?")));
    }

    @Test
    public void Should_Return_Definition_When_Received_Definition() {
        String definitionReturned = MockModelScreen.newInvestmentFund().getDefinition();
        assertThat(definitionReturned, is(equalTo("O Fundo tem por objetivo proporcionar aos seus cotistas rentabilidade no longo prazo através de investimentos.")));
    }

    @Test
    public void Should_Return_Risk_Title_When_Received_Risk_Title() {
        String riskTitlenReturned = MockModelScreen.newInvestmentFund().getRiskTitle();
        assertThat(riskTitlenReturned, is(equalTo("Grau de risco do investimento")));
    }

    @Test
    public void Should_Return_Risk_When_Received_Risk() {
        Integer riskReturned = MockModelScreen.newInvestmentFund().getRisk();
        assertThat(riskReturned, is(equalTo(4)));
    }

    @Test
    public void Should_Return_InfoTitle_When_Received_InfoTitle() {
        String infoTitleReturned = MockModelScreen.newInvestmentFund().getInfoTitle();
        assertThat(infoTitleReturned, is(equalTo("Mais informações sobre o investimento")));
    }

    @Test
    public void Should_Return_CDI_And_Fund_When_Receiving_Month() {
        MoreInfo moreInfo = MockModelScreen.newInvestmentFund().getMoreInfo();

        Month month = moreInfo.getMonth();
        float fundReturned = month.getFund();
        float cdiReturned = month.getCdi();

        assertThat(fundReturned, is(equalTo(0.3F)));
        assertThat(cdiReturned, is(equalTo(0.3F)));

    }

    @Test
    public void Should_Return_CDI_And_Fund_When_Receiving_Year() {
        MoreInfo moreInfo = MockModelScreen.newInvestmentFund().getMoreInfo();

        Year year = moreInfo.getYear();
        float fundReturned = year.getFund();
        float cdiReturned = year.getCdi();

        assertThat(fundReturned, is(equalTo(13.01F)));
        assertThat(cdiReturned, is(equalTo(12.08F)));

    }

    @Test
    public void Should_Return_CDI_And_Fund_When_Receiving_TwelveMonths() {
        MoreInfo moreInfo = MockModelScreen.newInvestmentFund().getMoreInfo();

        TwelveMonths twelveMonths = moreInfo.getTwelveMonths();
        float fundReturned = twelveMonths.getFund();
        float cdiReturned = twelveMonths.getCdi();

        assertThat(fundReturned, is(equalTo(17.9F)));
        assertThat(cdiReturned, is(equalTo(17.6F)));

    }


}
