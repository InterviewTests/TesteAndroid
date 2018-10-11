package br.com.santander.santanderinvestimento.contact.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.santander.santanderinvestimento.R
import br.com.santander.santanderinvestimento.contact.data.entity.Contact
import br.com.santander.santanderinvestimento.core.presentation.BaseFragment
import br.com.santander.santanderinvestimento.util.showSnack
import kotlinx.android.synthetic.main.fragment_contact.*
import org.koin.android.ext.android.inject

class ContactFragment : BaseFragment(), ContactContract.View, SwipeRefreshLayout.OnRefreshListener {

    override fun showSuccess(contact: List<Contact>) {

    }

    override fun onRefresh() {
        presenter.loadContact()
    }

    override fun showMessage(message: String) {
        view?.showSnack(message)
    }

    override fun showLoading(active: Boolean) {
        swiperefresh.isRefreshing = active
    }

    override val presenter: ContactContract.Presenter by inject()

    companion object {
        fun newInstance() = ContactFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareSwipeRefreshLayout()
    }

    private fun prepareSwipeRefreshLayout() {
        swiperefresh?.setColorSchemeColors(Color.RED)
        swiperefresh?.setOnRefreshListener(this)
    }


    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)
        presenter.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unSubscribe()
    }

}