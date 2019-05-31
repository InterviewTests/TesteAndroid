package br.banco.services.fund.domain.national;

import java.util.HashMap;
import java.util.Map;

public class Year {

    //JsonProperty("fund")
    private Double fund;
    //JsonProperty("CDI")
    private Double cDI;
    //JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    //JsonProperty("fund")
    public Double getFund() {
        return fund;
    }

    //JsonProperty("fund")
    public void setFund(Double fund) {
        this.fund = fund;
    }

    //JsonProperty("CDI")
    public Double getCDI() {
        return cDI;
    }

    //JsonProperty("CDI")
    public void setCDI(Double cDI) {
        this.cDI = cDI;
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