package br.com.rafael.santanderteste.repository

import br.com.rafael.santanderteste.datasource.BankServices
import br.com.rafael.santanderteste.datasource.RetrofitClient
import br.com.rafael.santanderteste.domain.CellCatalog
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

    }
}