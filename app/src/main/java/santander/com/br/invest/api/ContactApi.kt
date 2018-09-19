package santander.com.br.invest.api

import io.reactivex.Observable
import retrofit2.http.GET
import santander.com.br.invest.model.CellsResponse

interface ContactApi {

  @GET("cells.json")
  fun getCells(
  ): Observable<CellsResponse>
}
