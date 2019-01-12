package br.com.rafael.santanderteste.repository

/**
 * Contrato a ser implementado na funcao responsavel por obter dados de um determinao repositorio,
 * independente do framework utilizado
 * Interface generica
 */
interface BankRepository<T> {

    fun onResponse(reponse: T?)

    fun onFailure(throwable: Throwable)
}