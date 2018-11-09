package br.com.gservices.santanderteste.pages.investments.data

import br.com.gservices.santanderteste.pages.investments.data.entities.Investments
import br.com.gservices.santanderteste.pages.investments.data.entities.InvestmentsResponse

object InvestmentMapper {
    fun map(investmentJsonResponse: InvestmentsResponse): Investments {
        return Investments(
            investmentJsonResponse.screen.title,
            investmentJsonResponse.screen.fundName,
            investmentJsonResponse.screen.whatIs,
            investmentJsonResponse.screen.definition,
            investmentJsonResponse.screen.riskTitle,
            investmentJsonResponse.screen.risk,
            investmentJsonResponse.screen.infoTitle,
            investmentJsonResponse.screen.info,
            investmentJsonResponse.screen.downInfo,
            investmentJsonResponse.screen.moreInfo)
    }
}