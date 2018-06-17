package lzacheu.com.br.santanderinvestimento.data.remote.fund;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by luiszacheu on 6/16/18.
 */

public interface FundService {

    @GET("/fund.json")
    Call<FundResponse> getFund();
}
