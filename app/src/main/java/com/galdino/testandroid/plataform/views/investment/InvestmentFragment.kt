package com.galdino.testandroid.plataform.views.investment

import android.content.Intent
import android.graphics.Point
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.galdino.testandroid.R
import com.galdino.testandroid.data.entity.investment.*
import com.galdino.testandroid.plataform.views.base.BaseFragment
import com.galdino.testandroid.util.MyAnimationUtils
import kotlinx.android.synthetic.main.fragment_investment.*
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
        ivShareInvestment.setOnClickListener(this)
    }

    override fun onLoading(isLoading: Boolean) {
        onLoading(pbLoading, isLoading)
    }

    override fun showDefaultErrorOnLoadInvestment() {
        showLongToast(R.string.error_on_loading_investment)
    }

    override fun showDefaultErrorOnLoadMoreInfo() {
        showLongToast(R.string.error_on_load_risks)
    }

    override fun showDefaultErrorOnLoadRisks() {
        showLongToast(R.string.error_on_load_more_info)
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

    override fun loadMoreInfoList(periodList: List<PeriodModel>) {
        rvMoreInfo.adapter = MoreInfoAdapter(periodList)
        rvMoreInfo.layoutManager = LinearLayoutManager(context)
    }

    override fun loadRisksList(risks: List<Risk>) {
        val display = activity?.windowManager?.defaultDisplay

        val size = Point()
        display?.getSize(size)
        var widthScreen = size.x
        widthScreen /= risks.size

        var margin= resources.getDimension(R.dimen.margin_default8x)
        margin /= 2
        rvRisks.adapter = RiskAdapter(risks, widthScreen-margin.toInt())
        rvRisks.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }

    override fun onDownloadClicked(downInfo: DownInfo) {
        mPresenter.onDownloadClicked(downInfo)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            btInvest.id->{
                mPresenter.onInvestClicked()
            }
            ivShareInvestment.id->{
                mPresenter.shareInvestmentClicked()
            }
        }
    }

    override fun downloading() {
        showLongToast(R.string.downloading)
    }

    override fun invest() {
        showLongToast(R.string.invest)
    }

    override fun hideBackgroundLoading() {
        MyAnimationUtils.translateHide(vLoading, context,null,null,500)
        MyAnimationUtils.translateShow(btInvest, context,null,null,500)
    }

    override fun shareInvestmentByWhatsApp(msg: String) {
        val whatsappIntent = Intent(Intent.ACTION_SEND)
        whatsappIntent.type = "text/plain"
        whatsappIntent.setPackage("com.whatsapp")
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, msg)
        try {
            startActivity(whatsappIntent)
        } catch (ex: android.content.ActivityNotFoundException) {
            showLongToast(R.string.error_whats_app_not_installed)
        }

    }
}
