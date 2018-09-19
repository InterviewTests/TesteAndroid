package santander.com.br.invest.repository

import io.reactivex.Observable
import santander.com.br.invest.model.CellsResponse

interface ContactRepository {

  fun getCells(): Observable<CellsResponse>

}
