package br.com.santander.santanderinvestimento.feature.contact.data

import br.com.santander.santanderinvestimento.SantanderRepositoryTest
import br.com.santander.santanderinvestimento.feature.contact.data.entity.ContactResponse
import br.com.santander.santanderinvestimento.feature.contact.domain.Type
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.single
import org.junit.Before
import org.junit.Test
import kotlin.coroutines.CoroutineContext

class ContactMapperTest : SantanderRepositoryTest() {
    lateinit var jsonResponse: ContactResponse
    private lateinit var repository: ContactRepository
    private lateinit var context: CoroutineContext

    @Before
    suspend fun setUp() {

        repository = ContactRepositoryImpl(api,context)
        val values = repository.getContacts()
        val response = ContactResponse(values.single().body()?.cells)
        jsonResponse = response
    }


    @Test
    fun `test map first ocurrency to contact response correctly`() {
        val contacts = ContactMapper.map(jsonResponse)
        val firstContact = contacts[0]
        assertEquals(6, contacts.size)
        assertEquals(1, firstContact.id)
        assertEquals(Type.TEXT, firstContact.type)
        assertEquals("Olá, primeiro se apresente com o seu nome:", firstContact.message)
        assertEquals(1, firstContact.typeField)
        assertEquals(false, firstContact.hidden)
        assertEquals(60.0, firstContact.topSpacing!!, 0.0)
        assertEquals(null, firstContact.show)
        assertEquals(false, firstContact.required)
    }

    @Test
    fun `test type contact convert to correct type`() {
        val cells = ContactMapper.map(jsonResponse)
        assertEquals(cells[0].type, Type.TEXT)
        assertEquals(cells[1].type, Type.TEXT)
        assertEquals(cells[2].type, Type.TEXT)
        assertEquals(cells[3].type, Type.TEXT)
        assertEquals(cells[4].type, Type.TEXT)
        assertEquals(cells[5].type, Type.TEXT)
    }
}
