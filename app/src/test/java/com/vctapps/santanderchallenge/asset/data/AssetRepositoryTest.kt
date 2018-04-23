package com.vctapps.santanderchallenge.asset.data

import com.google.gson.Gson
import com.squareup.okhttp.mockwebserver.MockResponse
import com.vctapps.santanderchallenge.BaseNetworkTest
import com.vctapps.santanderchallenge.asset.domain.AssetRepository
import com.vctapps.santanderchallenge.core.domain.InvalidData
import com.vctapps.santanderchallenge.util.JsonLoader
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class AssetRepositoryTest: BaseNetworkTest() {

    lateinit var repository: AssetRepository

    val jsonResponse = JsonLoader.getStringJson("json/fund.json")

    val TITLE = "Fundos de investimento"
    val FUND_NAME = "Vinci Valorem FI Multimercado"
    val WHAT_IS = "O que é?"
    val DEFINITION = "O Fundo tem por objetivo proporcionar aos seus cotistas rentabilidade no longo prazo através de investimentos."
    val RISK_TITLE = "Grau de risco do investimento"
    val RISK = 4
    val INFO_TITLE = "Mais informações sobre o investimento"
    val MORE_INFO_FIRST_MONTH_FUND = 0.3
    val MORE_INFO_FIRST_MONTH_CDI = 0.3
    val INFO_FIRST_NAME = "Taxa de administração"
    val INFO_FIRST_DATA = "0,50%"
    val DOWN_INFO_FIRST_NAME = "Essenciais"
    val DOWN_INFO_FIRST_DATA = InvalidData.INVALID_STRING

    @Before
    override fun setUp() {
        super.setUp()

        repository = AssetRepositoryImpl(api, Gson())
    }

    @Test
    fun `test if correctly get asset response`(){
        val mockedResponse = MockResponse()
        mockedResponse.setResponseCode(200)
        mockedResponse.setBody(jsonResponse)

        mockServer.enqueue(mockedResponse)

        val response = repository.getAsset()
                .test()
                .assertNoErrors()

        assertNotNull(response)
        assertEquals(1, response.valueCount())

        assertEquals(TITLE, response.values()[0].title)
        assertEquals(FUND_NAME, response.values()[0].assetName)
        assertEquals(WHAT_IS, response.values()[0].whatIs)
        assertEquals(DEFINITION, response.values()[0].definition)
        assertEquals(RISK_TITLE, response.values()[0].riskTitle)
        assertEquals(RISK, response.values()[0].risk)
        assertEquals(INFO_TITLE, response.values()[0].infoTitle)
        assertEquals(MORE_INFO_FIRST_MONTH_FUND, response.values()[0].monthInfo.fund, 0.0)
        assertEquals(MORE_INFO_FIRST_MONTH_CDI, response.values()[0].monthInfo.cdi, 0.0)
        assertEquals(INFO_FIRST_NAME, response.values()[0].info[0].name)
        assertEquals(INFO_FIRST_DATA, response.values()[0].info[0].data)
        assertEquals(DOWN_INFO_FIRST_NAME, response.values()[0].downInfo[0].name)
        assertEquals(DOWN_INFO_FIRST_DATA, response.values()[0].downInfo[0].data)

    }

    @Test
    fun `test if send on error when there is http 500`() {
        val mockedResponse = MockResponse()
        mockedResponse.setResponseCode(500)
        mockedResponse.setBody(jsonResponse)

        mockServer.enqueue(mockedResponse)

        repository.getAsset()
                .test()
                .assertError(Throwable::class.java)

    }

    @Test
    fun `test if send on error when there is http 404`() {
        val mockedResponse = MockResponse()
        mockedResponse.setResponseCode(404)
        mockedResponse.setBody(jsonResponse)

        mockServer.enqueue(mockedResponse)

        repository.getAsset()
                .test()
                .assertError(Throwable::class.java)

    }

}