package br.com.cesarsicas.stdandroidteste.mvp.home.investment

import br.com.cesarsicas.stdandroidteste.domains.InvestmentData
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import javax.inject.Inject

/**
 * Created by julio on 4/22/18.
 */
class InvestmentPresenter @Inject constructor() {

    @Inject
    lateinit var interactor: InvestmentInteractor

    var view: InvestmentView? = null

    private var disposable: Disposable? = null

    fun attachView(view: InvestmentView) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun getInvestmenentData() {
        disposable = interactor.getInvestmentData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    view?.setInvestmentData(data)

                }) { throwable ->

                    view?.showError(throwable.message)
                }
    }

    fun parseJsonData(inputStream: InputStream) {

        try {

            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()

            val jsonString = kotlin.text.String(buffer, Charset.forName("UTF-8"))

            val data = Gson().fromJson(jsonString, InvestmentData::class.java)

            view?.setInvestmentData(data)


        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}