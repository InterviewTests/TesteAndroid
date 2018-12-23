package com.seletiva.santander.investment.controllers.cells;

import com.seletiva.santander.investment.ui.form.domain.model.CellHolder;
import com.seletiva.santander.investment.controllers.BaseResponse;

public interface CellResponse extends BaseResponse {
    void onCellsResponse(CellHolder cellHolder);
}
