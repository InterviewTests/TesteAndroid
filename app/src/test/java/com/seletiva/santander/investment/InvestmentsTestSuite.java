package com.seletiva.santander.investment;

import com.seletiva.santander.investment.contact.TestMainFormPresenter;
import com.seletiva.santander.investment.investments.TestInvestmentPresenter;
import com.seletiva.santander.investment.models.TestCell;
import com.seletiva.santander.investment.models.TestCellHolder;
import com.seletiva.santander.investment.tabs.TestTabsPresenter;
import com.seletiva.santander.investment.utils.TestStringUtils;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestMainFormPresenter.class,
        TestInvestmentPresenter.class,
        TestCell.class,
        TestCellHolder.class,
        TestTabsPresenter.class,
        TestStringUtils.class
})

public class InvestmentsTestSuite {}

