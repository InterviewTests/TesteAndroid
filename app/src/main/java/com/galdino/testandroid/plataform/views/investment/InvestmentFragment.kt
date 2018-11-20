package com.galdino.testandroid.plataform.views.investment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.galdino.testandroid.R
import com.galdino.testandroid.data.entity.investment.DownInfo
import com.galdino.testandroid.data.entity.investment.Info
import com.galdino.testandroid.data.entity.investment.ScreenInvestment
import com.galdino.testandroid.plataform.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_investment.*
import kotlinx.android.synthetic.main.fragment_investment.view.*
import org.koin.android.ext.android.inject

class InvestmentFragment : BaseFragment(), InvestmentContract.View, InfoAdapter.Listener, View.OnClickListener {
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
        loadListeners()
    }

    private fun loadListeners() {
        btInvest.setOnClickListener(this)
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

    override fun loadScreenData(screenInvestment: ScreenInvestment) {
        tvTitle.text = screenInvestment.title
        tvFundName.text = screenInvestment.fundName
        tvWhatIs.text = screenInvestment.whatIs
        tvDefinition.text = screenInvestment.definition
        tvRiskTitle.text = screenInvestment.riskTitle
        tvMoreInfo.text = screenInvestment.infoTitle
    }

    override fun loadInfoList(infoList: List<Info>) {
        rvInfo.adapter = InfoAdapter(infoList)
        rvInfo.layoutManager = LinearLayoutManager(context)
    }

    override fun loadDownList(downsInfoList: List<DownInfo>) {
        rvDownInfo.adapter = InfoAdapter(downsInfoList, true, this)
        rvDownInfo.layoutManager = LinearLayoutManager(context)
    }

    override fun onDownloadClicked(downInfo: DownInfo) {
        mPresenter.onDownloadClicked(downInfo)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            btInvest.id->{
                mPresenter.onInvestClicked()
            }
        }
    }

    override fun downloading() {
        showLongToast(R.string.downloading)
    }

    override fun invest() {
        showLongToast(R.string.invest)
    }
}
