package rrzaniolo.testandroidsantander.network.contact.models.response;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This is the Response Model for the getCells api.
 * */
public class GetCellsResponse {

    //region --- Variable
    @SerializedName("cells")
    private List<CellResponse> cells;
    public List<CellResponse> getCells() {
        return cells;
    }
    public void setCells(List<CellResponse> cells) {
        this.cells = cells;
    }
    //endregion

    //region --- Constructors
    public GetCellsResponse() { }

    public GetCellsResponse(List<CellResponse> cells) {
        this.cells = cells;
    }
    //endregion
}
