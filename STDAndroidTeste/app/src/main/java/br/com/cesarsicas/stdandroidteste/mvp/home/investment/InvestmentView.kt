package br.com.cesarsicas.stdandroidteste.mvp.home.investment

import br.com.cesarsicas.stdandroidteste.domains.InvestmentData

/**
 * Created by julio on 4/22/18.
 */
interface InvestmentView {
    fun showError(message: String?)
    fun setInvestmentData(data: InvestmentData)
}