package andcordeiro.com.br.testeandroid.histories.contact

import andcordeiro.com.br.testeandroid.R
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers


class ContactPresenter(var model: ContactMVP.Model) : ContactMVP.Presenter {


    private lateinit var view: ContactMVP.View
    private var subscription: Subscription? = null

    override fun setView(view: ContactMVP.View) {
        this.view = view
    }

    override fun rxUnsubscribe() {
        if (subscription != null && !subscription!!.isUnsubscribed) {
            subscription!!.unsubscribe()
        }
    }

    override fun loadCells() {
        subscription = model.loadCellsAsync()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.createComponents(it)
                }, {
                    view.shortShowMessage(view.context().getString(R.string.error_message))
                })
    }

}