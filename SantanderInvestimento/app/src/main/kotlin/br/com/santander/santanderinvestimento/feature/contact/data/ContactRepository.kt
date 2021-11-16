package br.com.santander.santanderinvestimento.feature.contact.data

import br.com.santander.santanderinvestimento.feature.contact.data.entity.ContactResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ContactRepository {

    suspend fun getContacts(): Flow<Response<ContactResponse>>

}