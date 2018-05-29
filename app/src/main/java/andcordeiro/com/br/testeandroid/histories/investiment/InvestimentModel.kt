package andcordeiro.com.br.testeandroid.histories.investiment

import andcordeiro.com.br.testeandroid.entities.ResultScreen
import andcordeiro.com.br.testeandroid.system.extensions.makeObservable
import andcordeiro.com.br.testeandroid.system.retrofit.ApiService
import rx.Observable
import rx.schedulers.Schedulers
import java.util.concurrent.Callable

class InvestimentModel(var api: ApiService): InvestimentMVP.Model {

    override fun loadScreenAsync(): Observable<ResultScreen> {
        return makeObservable(Callable { loadScreen() }).subscribeOn(Schedulers.computation())
    }

    override fun loadScreen(): ResultScreen{
        var result = ResultScreen()
        val callback = api.getScreen().execute()
        result = callback.body() as ResultScreen
        return result
    }
}