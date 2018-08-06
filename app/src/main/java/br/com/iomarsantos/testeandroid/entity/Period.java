package br.com.iomarsantos.testeandroid.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Period implements Serializable {

    @JsonProperty("fund")
    protected Float fund;
    @JsonProperty("CDI")
    private Float cdi;

    @JsonProperty("fund")
    public void setFund(Float fund) {
        this.fund = fund;
    }

    @JsonProperty("CDI")
    public void setCDI(Float cdi) {
        this.cdi = cdi;
    }

    public String getFundFormatted() {
        return String.format("%s%%", this.fund);
    }

    public Float getFund(){
        return fund;
    }

    public Float getCdi(){
        return cdi;
    }

    public String getCDIFormatted() {
        return String.format("%s%%", this.cdi);
    }

}
