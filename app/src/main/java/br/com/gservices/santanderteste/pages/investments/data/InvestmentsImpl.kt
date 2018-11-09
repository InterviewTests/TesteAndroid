package br.com.gservices.santanderteste.pages.investments.data

import br.com.gservices.santanderteste.core.network.SantanderApi
import br.com.gservices.santanderteste.pages.investments.data.entities.Investments
import br.com.gservices.santanderteste.pages.investments.interfaces.InvestmentsInterface
import io.reactivex.Observable

class InvestmentsImpl (private val santanderApi: SantanderApi) : InvestmentsInterface {
    override fun getInvestment(): Observable<Investments> {
        return santanderApi.getInvestment()
            .flatMap { response ->
                if (response.isSuccessful) {
                    Observable.just(response.body())
                } else {
                    Observable.empty()
                }
            }
            .map(InvestmentMapper::map)
    }
}