package br.com.santander.santanderinvestimento.investiment.data

import br.com.santander.santanderinvestimento.investiment.data.entity.InvestmentResponse
import br.com.santander.santanderinvestimento.investiment.domain.entity.Investment

object InvestmentMapper {
    fun map(investmentJsonResponse: InvestmentResponse): Investment {
        return Investment(
                investmentJsonResponse.screen.title,
                investmentJsonResponse.screen.fundName,
                investmentJsonResponse.screen.whatIs,
                investmentJsonResponse.screen.definition,
                investmentJsonResponse.screen.riskTitle,
                investmentJsonResponse.screen.risk,
                investmentJsonResponse.screen.infoTitle,
                investmentJsonResponse.screen.info,
                investmentJsonResponse.screen.downInfo)
    }
}