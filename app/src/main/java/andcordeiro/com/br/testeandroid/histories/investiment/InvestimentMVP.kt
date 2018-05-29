package andcordeiro.com.br.testeandroid.histories.investiment

import andcordeiro.com.br.testeandroid.entities.ResultScreen
import android.content.Context
import rx.Observable

interface InvestimentMVP {

    interface View{
        fun shortShowMessage(msg: String?)

        fun setComponents(result: ResultScreen)

        fun defaultRisk()

        fun setRisk(risk: Int)

        fun context(): Context
    }

    interface Presenter {
        fun setView(view: InvestimentMVP.View)

        fun rxUnsubscribe()

        fun loadScreen()
    }

    interface Model{
        fun loadScreenAsync(): Observable<ResultScreen>

        fun loadScreen(): ResultScreen
    }
}