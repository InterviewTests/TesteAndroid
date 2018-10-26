package br.com.andreyneto.testesantander.ui.contact

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.andreyneto.testesantander.R
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment: Fragment(), ContactContract.View {

    private var mPresenter: ContactContract.Presenter? = null

    override fun showCells(cells: List<Any>?) {
        container.visibility = View.VISIBLE
    }

    override fun setPresenter(presenter: ContactContract.Presenter) {
        mPresenter = presenter
    }

    override fun onStart() {
        super.onStart()
        mPresenter?.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    companion object {

        fun newInstance(): ContactFragment {
            return ContactFragment()
        }
    }
}