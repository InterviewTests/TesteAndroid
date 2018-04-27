package io.github.pierry.better_call_me.api.contracts;

import io.github.pierry.better_call_me.domain.viewmodels.CellList;
import io.github.pierry.better_call_me.domain.viewmodels.FundBase;
import io.reactivex.Observable;
import retrofit2.Response;

public interface ISyncApi {

  Observable<Response<CellList>> fetchCells();

  Observable<Response<FundBase>> fetchFund();
}
