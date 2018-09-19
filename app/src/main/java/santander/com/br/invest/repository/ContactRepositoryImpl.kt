package santander.com.br.invest.repository

import io.reactivex.Observable
import santander.com.br.invest.api.ContactApi
import santander.com.br.invest.model.CellsResponse
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val investmentApi: ContactApi
) : ContactRepository {

  override fun getCells(): Observable<CellsResponse> {
    return investmentApi.getCells()
  }

}
