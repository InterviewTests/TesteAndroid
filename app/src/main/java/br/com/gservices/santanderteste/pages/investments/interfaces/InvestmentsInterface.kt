package br.com.gservices.santanderteste.pages.investments.interfaces

import br.com.gservices.santanderteste.pages.investments.data.entities.Investments
import io.reactivex.Observable

interface InvestmentsInterface {
    fun getInvestment(): Observable<Investments>
}