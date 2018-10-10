package br.com.santander.santanderinvestimento.investiment.data

import br.com.santander.santanderinvestimento.core.data.SantanderApi
import br.com.santander.santanderinvestimento.investiment.domain.InvestmentRepository
import br.com.santander.santanderinvestimento.investiment.domain.entity.Investment
import io.reactivex.Flowable

class InvestmentRepositoryImpl(private val santanderApi: SantanderApi) : InvestmentRepository {
    override fun getInvestment(): Flowable<Investment> {
        return santanderApi.getInvestment()
                .flatMap { response ->
                    if (response.isSuccessful) {
                        Flowable.just(response.body())
                    } else {
                        Flowable.empty()
                    }
                }
                .map(InvestmentMapper::map)
    }
}