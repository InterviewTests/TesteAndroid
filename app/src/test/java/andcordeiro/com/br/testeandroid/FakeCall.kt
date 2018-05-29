package andcordeiro.com.br.testeandroid

import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FakeCall<T>(
        private val response: Response<T>)
    : Call<T> {

    companion object {
        inline fun <reified T> buildSuccess(body: T?): FakeCall<T> {
            return FakeCall(Response.success(body))
        }
    }

    override fun execute(): Response<T> = response

    override fun enqueue(callback: Callback<T>?) {}

    override fun isExecuted(): Boolean = false

    override fun clone(): Call<T> = this

    override fun isCanceled(): Boolean = false

    override fun cancel() {}

    override fun request(): Request? = null
}