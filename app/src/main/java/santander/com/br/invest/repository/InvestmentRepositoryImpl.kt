package santander.com.br.invest.repository

import io.reactivex.Observable
import santander.com.br.invest.api.InvestmentApi
import santander.com.br.invest.model.FundResponse
import javax.inject.Inject

class InvestmentRepositoryImpl @Inject constructor(
    private val investmentApi: InvestmentApi
) : InvestmentRepository {

  override fun getFunds(): Observable<FundResponse> {
    return investmentApi.getFund()
  }

}
