package br.com.gservices.santanderteste.pages.investments.presenter

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.gservices.santanderteste.R
import br.com.gservices.santanderteste.core.presenter.BaseFragment
import br.com.gservices.santanderteste.pages.investments.adapters.DownInformationsAdapter
import br.com.gservices.santanderteste.pages.investments.adapters.InformationsAdapter
import br.com.gservices.santanderteste.pages.investments.data.entities.Informations
import br.com.gservices.santanderteste.pages.investments.data.entities.InvestmentTimeInformations
import br.com.gservices.santanderteste.pages.investments.data.entities.Investments
import br.com.gservices.santanderteste.pages.investments.interfaces.ContractInvestmentInterface
import br.com.gservices.santanderteste.utils.StringUtils.Companion.formatDoubleToString
import br.com.gservices.santanderteste.utils.showSnack
import kotlinx.android.synthetic.main.fragment_investment.*
import org.koin.android.ext.android.inject

class InvestmentsFragment : BaseFragment(), ContractInvestmentInterface.View, SwipeRefreshLayout.OnRefreshListener {
    override fun showSuccess(investment: Investments) {
        clRoot.visibility = View.VISIBLE
        txTitle.text = investment.title
        txFundName.text = investment.fundName
        txWhatsIs.text = investment.whatIs
        txDefinition.text = investment.definition
        txRiskTitle.text = investment.riskTitle
        txInfoTitle.text = investment.infoTitle

        fillRiskLayout(investment.risk!!)
        fillInfo(investment.info!!)
        fillDownInfo(investment.downInfo!!)
        fillMoreInfo(investment.moreInfo!!)
    }

    fun fillMoreInfo(moreInfo: InvestmentTimeInformations) {
        txMonthFound.text = formatDoubleToString(moreInfo.month?.fund)
        txMonthCDI.text = formatDoubleToString(moreInfo.month?.CDI)

        txYearFound.text = formatDoubleToString(moreInfo.year?.fund)
        txYearCDI.text = formatDoubleToString(moreInfo.year?.CDI)

        tx12MonthFound.text = formatDoubleToString(moreInfo.months12?.fund)
        tx12MonthCDI.text = formatDoubleToString(moreInfo.months12?.CDI)
    }


    fun fillDownInfo(list: List<Informations>) {
        val listAdapter = DownInformationsAdapter(list as MutableList<Informations>) { item: String -> presenter.clickDownload(item) }
        rcvDownInfo.adapter = listAdapter
    }



    fun fillInfo(list: List<Informations>) {
        val listAdapter = InformationsAdapter(list as MutableList<Informations>)
        rcvInfo.adapter = listAdapter
    }

    fun fillRiskLayout(risk: Int) {
        when (risk) {
            1 -> {
                ivRisk1.visibility = View.VISIBLE
                bgRisk1.layoutParams.height = resources.getDimensionPixelSize(R.dimen.size_risk_expanded)
            }
            2 -> {
                ivRisk2.setImageResource(R.drawable.arrow)
                bgRisk2.layoutParams.height = resources.getDimensionPixelSize(R.dimen.size_risk_expanded)
            }
            3 -> {
                ivRisk3.setImageResource(R.drawable.arrow)
                bgRisk3.layoutParams.height = resources.getDimensionPixelSize(R.dimen.size_risk_expanded)
            }
            4 -> {
                ivRisk4.setImageResource(R.drawable.arrow)
                bgRisk4.layoutParams.height = resources.getDimensionPixelSize(R.dimen.size_risk_expanded)
            }
            5 -> {
                ivRisk5.setImageResource(R.drawable.arrow)
                bgRisk5.layoutParams.height = resources.getDimensionPixelSize(R.dimen.size_risk_expanded)
            }
        }
    }

    override fun onRefresh() {
        presenter.loadFeed()
    }

    override fun showMessage(message: String) {
        view?.showSnack(message)
    }

    override fun showLoading(active: Boolean) {
        swiperefresh.isRefreshing = active
    }

    override val presenter: ContractInvestmentInterface.Presenter by inject()

    companion object {
        fun newInstance() = InvestmentsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_investment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        prepareSwipeRefreshLayout()
        ivShare.setOnClickListener { presenter.clickShare() }
        btnInvest.setOnClickListener { presenter.clickInvest() }
    }

    fun prepareRecyclerView() {

        rcvInfo.layoutManager = LinearLayoutManager(context)
        rcvDownInfo.layoutManager = LinearLayoutManager(context)
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