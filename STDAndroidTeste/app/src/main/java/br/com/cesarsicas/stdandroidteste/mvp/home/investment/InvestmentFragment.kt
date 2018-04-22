package br.com.cesarsicas.stdandroidteste.mvp.home.investment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.cesarsicas.stdandroidteste.R


/**
 * A simple [Fragment] subclass.
 */
class InvestmentFragment : Fragment(), InvestmentView {
    val presenter = InvestmentPresenter()

    override fun showError(message: String?) {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                     savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_investment, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
        presenter.getInvestmenentData()
    }

    override fun setInvestmentData(){

    }

}
