package br.com.santander.santanderinvestimento.contact.data

import br.com.santander.santanderinvestimento.SantanderRepositoryTest
import br.com.santander.santanderinvestimento.contact.domain.ContactRepository
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ContactRepositoryTest : SantanderRepositoryTest() {

    private lateinit var repository: ContactRepository
    @Before
    fun setUp() {
        repository = ContactRepositoryImpl(api)
    }

    @Test
    fun `test get correctly`() {
        val values = repository.getContacts()
                .test()
                .assertNoErrors()

        assertEquals(1, values.valueCount())
        assertEquals(6, values.values()[0].size)
    }

    @Test
    fun `test get contact when http 500`(){
        val values = repository.getContacts()
        val jsonResponse = Gson().toJson(values.test().values())
        val mockedResponse = MockResponse()
        mockedResponse.setResponseCode(500)
        mockedResponse.setBody(jsonResponse)
        mockServer.enqueue(mockedResponse)
        assertEquals(1, values.test().valueCount())
    }

}