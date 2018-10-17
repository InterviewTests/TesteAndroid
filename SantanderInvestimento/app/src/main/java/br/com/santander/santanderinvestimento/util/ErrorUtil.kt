package br.com.santander.santanderinvestimento.util

import com.crashlytics.android.Crashlytics
import retrofit2.HttpException
import javax.net.ssl.HttpsURLConnection


object ErrorUtil {

    fun parseError(response: Throwable): String {
        var erro = "Erro desconhecido, por favor contacte o suporte."
        if (response is HttpException) {
            when (response.code()) {
                HttpsURLConnection.HTTP_BAD_REQUEST -> erro = "Erro de requisição."
                HttpsURLConnection.HTTP_NOT_FOUND -> erro = "Não encontrado"
                HttpsURLConnection.HTTP_INTERNAL_ERROR -> erro = "Há um erro na requisição"
                else -> erro += " Código: " + response.code()
            }
        }
        Crashlytics.log("Erro de conexão: $erro")
        return erro

    }
}
