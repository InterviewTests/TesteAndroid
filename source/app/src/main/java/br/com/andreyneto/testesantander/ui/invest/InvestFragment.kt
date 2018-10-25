package br.com.andreyneto.testesantander.ui.invest

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.andreyneto.testesantander.R

class InvestFragment : Fragment(), InvestContract.View {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_invest, container, false)
    }

    override fun setPresenter(presenter: InvestContract.Presenter) {

    }

    override fun showData() {

    }

    companion object {

        fun newInstance(): InvestFragment {
            return InvestFragment()
        }
    }
}
