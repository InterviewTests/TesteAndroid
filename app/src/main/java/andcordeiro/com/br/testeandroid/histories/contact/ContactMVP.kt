package andcordeiro.com.br.testeandroid.histories.contact

import andcordeiro.com.br.testeandroid.entities.ResultContact
import android.content.Context
import rx.Observable

interface ContactMVP {

    interface View{
        fun shortShowMessage(msg: String?)

        fun createComponents(result: ResultContact)

        fun context(): Context
    }

    interface Presenter {
        fun setView(view: ContactMVP.View)

        fun rxUnsubscribe()

        fun loadCells()
    }

    interface Model{
        fun loadCellsAsync(): Observable<ResultContact>

        fun loadCells():ResultContact
    }
}