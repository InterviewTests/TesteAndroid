package com.rafhack.testeandroid.investment

import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafhack.testeandroid.R
import com.rafhack.testeandroid.base.BaseProgressFragment
import com.rafhack.testeandroid.data.entities.investment.Investment

class InvestmentFragment : BaseProgressFragment(), InvestmentContract.View {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setProgress(active: Boolean) {
        if (active) showProgress() else hideProgress()
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(view?.findViewById(R.id.fragment_base_progress_ctl_root)!!,
                message, Snackbar.LENGTH_LONG).show()
    }

    override fun showInvestmentInfo(investment: Investment) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}