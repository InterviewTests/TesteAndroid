package br.com.santander.santanderinvestimento.feature.investiment.data

import br.com.santander.santanderinvestimento.api.SantanderApi
import br.com.santander.santanderinvestimento.feature.investiment.data.entity.InvestmentResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class InvestmentRepositoryImpl(private val santanderApi: SantanderApi, private val ioDispatcher: CoroutineContext) : InvestmentRepository {
    override suspend fun getInvestment(): Flow<Response<InvestmentResponse>> {
        return flow { emit(value = santanderApi.getInvestment()) }.flowOn(ioDispatcher)
    }
}
