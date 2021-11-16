package br.com.santander.santanderinvestimento.feature.contact.data

import br.com.santander.santanderinvestimento.feature.contact.data.entity.ContactResponse
import br.com.santander.santanderinvestimento.api.SantanderApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class ContactRepositoryImpl(private val santanderApi: SantanderApi, private val ioDispatcher: CoroutineContext) : ContactRepository {
    override suspend fun getContacts(): Flow<Response<ContactResponse>> {
        return flow { emit(value = santanderApi.getContacts()) }.flowOn(ioDispatcher)
    }
}