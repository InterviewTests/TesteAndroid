package br.com.santander.santanderinvestimento.investment.data

import br.com.santander.santanderinvestimento.SantanderRepositoryTest
import br.com.santander.santanderinvestimento.investiment.data.InvestmentMapper
import br.com.santander.santanderinvestimento.investiment.data.InvestmentRepositoryImpl
import br.com.santander.santanderinvestimento.investiment.data.entity.InvestmentResponse
import br.com.santander.santanderinvestimento.investiment.domain.InvestmentRepository
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ContactMapperTest : SantanderRepositoryTest() {
    lateinit var jsonResponse: InvestmentResponse
    private lateinit var repository: InvestmentRepository

    @Before
    fun setUp() {
        repository = InvestmentRepositoryImpl(api)
        val values = repository.getInvestment()
        val response = values.blockingFirst()
        jsonResponse = InvestmentResponse(response)
    }

    @Test
    fun `test map first ocurrency to investment response correctly`() {
        val investment = InvestmentMapper.map(jsonResponse)
        assertEquals(jsonResponse.screen.title, investment.title)
        assertEquals(jsonResponse.screen.fundName, investment.fundName)
        assertEquals(jsonResponse.screen.whatIs, investment.whatIs)
        assertEquals(jsonResponse.screen.definition, investment.definition)
        assertEquals(jsonResponse.screen.riskTitle, investment.riskTitle)
        assertEquals(jsonResponse.screen.risk, investment.risk)
        assertEquals(jsonResponse.screen.infoTitle, investment.infoTitle)
        assertEquals(jsonResponse.screen.moreInfo?.month?.fund!!, investment.moreInfo?.month?.fund!!, 0.0)
        assertEquals(jsonResponse.screen.moreInfo?.month?.CDI!!, investment.moreInfo?.month?.CDI!!, 0.0)
        assertEquals(jsonResponse.screen.moreInfo?.year?.fund!!, investment.moreInfo?.year?.fund!!, 0.0)
        assertEquals(jsonResponse.screen.moreInfo?.year?.CDI!!, investment.moreInfo?.year?.CDI!!, 0.0)
        assertEquals(jsonResponse.screen.moreInfo?.months12?.fund!!, investment.moreInfo?.months12?.fund!!, 0.0)
        assertEquals(jsonResponse.screen.moreInfo?.months12?.CDI!!, investment.moreInfo?.months12?.CDI!!, 0.0)
        assertEquals(jsonResponse.screen.info?.get(0)?.name!!, investment.info?.get(0)?.name)
        assertEquals(jsonResponse.screen.info?.get(0)?.data!!, investment.info?.get(0)?.data)
        assertEquals(jsonResponse.screen.downInfo?.get(0)?.name!!, investment.downInfo?.get(0)?.name)
        assertEquals(null, investment.downInfo?.get(0)?.data)
    }
}