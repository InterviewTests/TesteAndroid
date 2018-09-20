package santander.com.br.invest.api

import io.reactivex.Observable
import retrofit2.http.GET
import santander.com.br.invest.model.FundResponse

interface InvestmentApi {

  @GET("fund.json")
  fun getFund(
  ): Observable<FundResponse>
}
