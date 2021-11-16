package br.com.santander.santanderinvestimento.feature.investiment.data

import br.com.santander.santanderinvestimento.feature.investiment.data.entity.InvestmentResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface InvestmentRepository {

    suspend fun getInvestment(): Flow<Response<InvestmentResponse>>

}