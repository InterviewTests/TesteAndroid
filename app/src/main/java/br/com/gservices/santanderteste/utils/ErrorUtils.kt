package br.com.gservices.santanderteste.utils

import retrofit2.HttpException
import javax.net.ssl.HttpsURLConnection

object ErrorUtils {
    fun parseError(resp: Throwable): String {
        var err = "Erro desconhecido, contate o suporte."
        if (resp is HttpException) {
            when (resp.code()) {
                HttpsURLConnection.HTTP_BAD_REQUEST -> err = "Erro de requisição."
                HttpsURLConnection.HTTP_NOT_FOUND -> err = "Não encontrado"
                HttpsURLConnection.HTTP_INTERNAL_ERROR -> err = "Há um erro na requisição"
                else -> err += " Código: " + resp.code()
            }
        }
        return err
    }
}