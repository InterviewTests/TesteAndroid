package br.com.rafael.santanderteste.repository

import br.com.rafael.santanderteste.datasource.BankServices
import br.com.rafael.santanderteste.datasource.RetrofitClient
import br.com.rafael.santanderteste.domain.entity.CellCatalog
import br.com.rafael.santanderteste.domain.entity.ScreenFund
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Classe provedora de dados.
 * Os dados são obtidos da API por meio da lib Retrofit.
 */
class BankProvider {

    companion object {
        private var bankServices = RetrofitClient.getRetrofitClient().create(BankServices::class.java)

        /**
         * Funcao que retorna os dados de celulas (cells) que serao usados para configurar o formulario
         * @param repository Interface de contrato generica. Deve-se passar o tipo de retorno do callback
         */
        fun getCells(repository: BankRepository<CellCatalog>) {
            val call: Call<CellCatalog?>? = bankServices?.getCells()
            call?.enqueue(object: Callback<CellCatalog?> {
                override fun onResponse(call: Call<CellCatalog?>, response: Response<CellCatalog?>) {
                    repository.onResponse(response.body())
                }
                override fun onFailure(call: Call<CellCatalog?>, t: Throwable) {
                    repository.onFailure(t)
                }
            })
        }

        /**
         * Funcao que retorna os dados de fundo de investimento
         * @param repository Interface de contrato generica. Deve-se passar o tipo de retorno do callback
         */
        fun getFund(repository: BankRepository<ScreenFund>) {
            val call: Call<ScreenFund?>? = bankServices?.getFund()
            call?.enqueue(object: Callback<ScreenFund?> {
                override fun onResponse(call: Call<ScreenFund?>, response: Response<ScreenFund?>) {
                    repository.onResponse(response.body())
                }
                override fun onFailure(call: Call<ScreenFund?>, t: Throwable) {
                    repository.onFailure(t)
                }
            })
        }

    }
}