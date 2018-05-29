package andcordeiro.com.br.testeandroid.system.extensions


import rx.Observable
import java.util.concurrent.Callable

fun <T> makeObservable(func: Callable<T>): Observable<T> {
    return Observable.create({ subscriber ->
        subscriber.onNext(func.call())
    })
}