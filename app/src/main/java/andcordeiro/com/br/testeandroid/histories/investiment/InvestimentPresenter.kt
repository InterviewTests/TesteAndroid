package andcordeiro.com.br.testeandroid.histories.investiment


import andcordeiro.com.br.testeandroid.R
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers


class InvestimentPresenter(var model: InvestimentMVP.Model) : InvestimentMVP.Presenter {

    private lateinit var view: InvestimentMVP.View
    private var subscription: Subscription? = null

    override fun setView(view: InvestimentMVP.View) {
        this.view = view
    }

    override fun rxUnsubscribe() {
        if (subscription != null && !subscription!!.isUnsubscribed) {
            subscription!!.unsubscribe()
        }
    }

    override fun loadScreen() {
        subscription = model.loadScreenAsync()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.setComponents(it)
                }, {
                    view.shortShowMessage(view.context().getString(R.string.error_message))
                })
    }
}