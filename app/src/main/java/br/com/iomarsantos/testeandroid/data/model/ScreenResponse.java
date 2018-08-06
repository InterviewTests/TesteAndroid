package br.com.iomarsantos.testeandroid.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import br.com.iomarsantos.testeandroid.entity.InvestmentFund;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScreenResponse implements Serializable {

    @JsonProperty("screen")
    private InvestmentFund screen;

    @JsonProperty("screen")
    public InvestmentFund getScreen() {
        return screen;
    }

    @JsonProperty("screen")
    public void setScreen(InvestmentFund screen) {
        this.screen = screen;
    }

}
