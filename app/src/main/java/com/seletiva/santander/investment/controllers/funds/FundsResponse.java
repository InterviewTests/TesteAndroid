package com.seletiva.santander.investment.controllers.funds;

import com.seletiva.santander.investment.controllers.BaseResponse;

public interface FundsResponse extends BaseResponse {
    void onFundsResponse(Object cellHolder);
}
