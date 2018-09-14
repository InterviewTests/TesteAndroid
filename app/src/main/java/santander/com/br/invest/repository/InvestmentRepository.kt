package santander.com.br.invest.repository

import io.reactivex.Observable
import santander.com.br.invest.model.FundResponse

interface InvestmentRepository {

  fun getFunds(): Observable<FundResponse>

}
