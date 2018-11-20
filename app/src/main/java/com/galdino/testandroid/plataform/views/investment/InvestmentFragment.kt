package com.galdino.testandroid.plataform.views.investment

import android.support.v7.widget.LinearLayoutManager
import com.galdino.testandroid.R
import com.galdino.testandroid.data.entity.investment.DownInfo
import com.galdino.testandroid.data.entity.investment.Info
import com.galdino.testandroid.plataform.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_investment.*
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
        mPresenter.loadInvestment()
    }

    override fun onLoading(isLoading: Boolean) {
        onLoading(pbLoading, isLoading)
    }

    override fun showDefaultErrorOnLoadInvestment() {
        showLongToast(R.string.error_on_loading_investment)
    }

    override fun showError(message: String) {
        showLongToast(message)
    }

    override fun loadInfoList(infoList: List<Info>) {
        rvInfo.adapter = InfoAdapter(infoList)
        rvInfo.layoutManager = LinearLayoutManager(context)
    }

    override fun loadDownList(downsInfoList: List<DownInfo>) {
        rvInfo.adapter = InfoAdapter(downsInfoList)
        rvInfo.layoutManager = LinearLayoutManager(context)
    }
}
