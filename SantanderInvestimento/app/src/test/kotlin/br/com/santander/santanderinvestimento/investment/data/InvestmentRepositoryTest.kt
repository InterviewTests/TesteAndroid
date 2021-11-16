package br.com.santander.santanderinvestimento.investment.data

import br.com.santander.santanderinvestimento.SantanderRepositoryTest
import br.com.santander.santanderinvestimento.feature.investiment.data.InvestmentRepositoryImpl
import br.com.santander.santanderinvestimento.feature.investiment.data.InvestmentRepository
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class InvestmentRepositoryTest : SantanderRepositoryTest() {

    private lateinit var repository: InvestmentRepository

    val TITLE = "Fundos de investimento"
    val FUND_NAME = "Vinci Valorem FI Multimercado"
    val WHAT_IS = "O que é?"
    val DEFINITION = "O Fundo tem por objetivo proporcionar aos seus cotistas rentabilidade no longo prazo através de investimentos."
    val RISK_TITLE = "Grau de risco do investimento"
    val RISK = 4
    val INFO_TITLE = "Mais informações sobre o investimento"
    val MORE_INFO_FIRST_MONTH_FUND = 0.3
    val MORE_INFO_FIRST_MONTH_CDI = 0.3
    val INFO_NAME = "Taxa de administração"
    val INFO_DATA = "0,50%"
    val DOWN_INFO_NAME = "Essenciais"
    val DOWN_INFO_DATA = null

    @Before
    fun setUp() {
        repository = InvestmentRepositoryImpl(api)
    }

    @Test
    fun `test get investment correctly`() {
        val response = repository.getInvestment()
                .test()
                .assertNoErrors()

        assertNotNull(response)
        assertEquals(1, response.valueCount())

        assertEquals(TITLE, response.values()[0].title)
        assertEquals(FUND_NAME, response.values()[0].fundName)
        assertEquals(WHAT_IS, response.values()[0].whatIs)
        assertEquals(DEFINITION, response.values()[0].definition)
        assertEquals(RISK_TITLE, response.values()[0].riskTitle)
        assertEquals(RISK, response.values()[0].risk)
        assertEquals(INFO_TITLE, response.values()[0].infoTitle)
        assertEquals(MORE_INFO_FIRST_MONTH_FUND, response.values()[0].moreInfo?.month?.fund!!, 0.0)
        assertEquals(MORE_INFO_FIRST_MONTH_CDI, response.values()[0].moreInfo?.month?.CDI!!, 0.0)
        assertEquals(INFO_NAME, response.values()[0].info?.get(0)?.name)
        assertEquals(INFO_DATA, response.values()[0].info?.get(0)?.data)
        assertEquals(DOWN_INFO_NAME, response.values()[0].downInfo?.get(0)?.name)
        assertEquals(DOWN_INFO_DATA, response.values()[0].downInfo?.get(0)?.data)
    }

    @Test
    fun `test get investment when http 500`(){
        val values = repository.getInvestment()
        val jsonResponse = Gson().toJson(values.test().values())
        val mockedResponse = MockResponse()
        mockedResponse.setResponseCode(500)
        mockedResponse.setBody(jsonResponse)
        mockServer.enqueue(mockedResponse)
        Assert.assertEquals(1, values.test().valueCount())
    }
}