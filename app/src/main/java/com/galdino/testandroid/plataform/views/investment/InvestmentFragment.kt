package com.galdino.testandroid.plataform.views.investment

import com.galdino.testandroid.R
import com.galdino.testandroid.plataform.views.base.BaseFragment
import org.koin.android.ext.android.inject

class InvestmentFragment : BaseFragment(), InvestmentContract.View
{
    private val mPresenter: InvestmentContract.Presenter by inject()
    companion object {
        @JvmStatic
        fun newInstance() =
                InvestmentFragment()
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_investment
    }

    override fun onInitView() {
        mPresenter.attach(this)
    }
}
