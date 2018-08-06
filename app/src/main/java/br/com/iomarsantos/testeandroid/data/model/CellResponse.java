package br.com.iomarsantos.testeandroid.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

import br.com.iomarsantos.testeandroid.entity.Cell;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CellResponse {

    @JsonProperty("cells")
    private List<Cell> cells = Collections.emptyList();

    @JsonProperty("cells")
    public List<Cell> getCells() {
        return cells;
    }

    @JsonProperty("cells")
    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

}
