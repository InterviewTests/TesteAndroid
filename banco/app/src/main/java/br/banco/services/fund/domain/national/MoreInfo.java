package br.banco.services.fund.domain.national;

import java.util.HashMap;
import java.util.Map;

public class MoreInfo {

    //JsonProperty("month")
    private Month month;
    //JsonProperty("year")
    private Year year;
    //JsonProperty("12months")
    private MonthSimulation MonthSimulation;
    //JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    //JsonProperty("month")
    public Month getMonth() {
        return month;
    }

    //JsonProperty("month")
    public void setMonth(Month month) {
        this.month = month;
    }

    //JsonProperty("year")
    public Year getYear() {
        return year;
    }

    //JsonProperty("year")
    public void setYear(Year year) {
        this.year = year;
    }

    //JsonProperty("12months")
    public MonthSimulation getSimulation() {
        return MonthSimulation;
    }

    //JsonProperty("12months")
    public void setSimulation(MonthSimulation MonthSimulation) {
        this.MonthSimulation = MonthSimulation;
    }

    //JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    //JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }



}