package com.santander.wesleyalves.santandercode.fundosinvestimento.domain.model;

import java.util.List;

public class FundoInvestimentoResponse {
    public String title;
    public String fundName;
    public String whatIs;
    public String definition;
    public String riskTitle;
    public int risk;
    public String infoTitle;
    public MoreInfo moreInfo;
    public List<Info> info;
    public List<DownInfo> downInfo;
}