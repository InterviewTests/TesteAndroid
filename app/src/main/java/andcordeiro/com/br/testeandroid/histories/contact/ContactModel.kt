package andcordeiro.com.br.testeandroid.histories.contact

import andcordeiro.com.br.testeandroid.entities.ResultContact
import andcordeiro.com.br.testeandroid.system.extensions.makeObservable
import andcordeiro.com.br.testeandroid.system.retrofit.ApiService
import rx.Observable
import rx.schedulers.Schedulers
import java.util.concurrent.Callable

class ContactModel(var api: ApiService): ContactMVP.Model {

    override fun loadCellsAsync(): Observable<ResultContact> {
        return makeObservable(Callable { loadCells() }).subscribeOn(Schedulers.computation())
    }

    override fun loadCells(): ResultContact{
        var result = ResultContact()
        val callback = api.getCells().execute()
        result = callback.body() as ResultContact
        return result
    }
}