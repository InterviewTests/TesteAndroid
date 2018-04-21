package com.bruno.santander.santanderchallenge.investimento.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoreInfo {

    @JsonProperty("month")
    private MoreInfoPeriod month;
    @JsonProperty("year")
    private MoreInfoPeriod year;
    @JsonProperty("12months")
    private MoreInfoPeriod twelveMonths;

}
