package com.vctapps.santanderchallenge.form.data

import com.squareup.okhttp.mockwebserver.MockResponse
import com.vctapps.santanderchallenge.BaseNetworkTest
import com.vctapps.santanderchallenge.form.domain.CellRepository
import com.vctapps.santanderchallenge.util.JsonLoader
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CellRepositoryTest: BaseNetworkTest() {

    lateinit var repository: CellRepository

    val jsonResponse = JsonLoader.getStringJson("json/cells.json")

    @Before
    override fun setUp(){
        super.setUp()

        repository = CellRepositoryImpl(api)
    }

    @Test
    fun `test get cells correctly`(){
        val mockedResponse = MockResponse()
        mockedResponse.setResponseCode(200)
        mockedResponse.setBody(jsonResponse)

        mockServer.enqueue(mockedResponse)

        val values = repository.getCells()
                .test()
                .assertNoErrors()

        val cells = values.values()[0]

        assertEquals(1, values.valueCount())
        assertEquals(6, cells.size)
    }

    @Test
    fun `test get empty cells when http 404`(){
        val mockedResponse = MockResponse()
        mockedResponse.setResponseCode(404)
        mockedResponse.setBody(jsonResponse)

        mockServer.enqueue(mockedResponse)

        val values = repository.getCells()
                .test()
                .assertNoErrors()


        assertEquals(0, values.valueCount())
    }

    @Test
    fun `test get empty cells when http 500`(){
        val mockedResponse = MockResponse()
        mockedResponse.setResponseCode(500)
        mockedResponse.setBody(jsonResponse)

        mockServer.enqueue(mockedResponse)

        val values = repository.getCells()
                .test()
                .assertNoErrors()

        assertEquals(0, values.valueCount())
    }

}