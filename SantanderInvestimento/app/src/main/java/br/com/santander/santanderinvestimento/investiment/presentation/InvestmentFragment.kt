package br.com.santander.santanderinvestimento.investiment.presentation

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.santander.santanderinvestimento.R
import br.com.santander.santanderinvestimento.core.presentation.BaseFragment
import br.com.santander.santanderinvestimento.investiment.adapter.InfoAdapter
import br.com.santander.santanderinvestimento.investiment.domain.entity.Info
import br.com.santander.santanderinvestimento.investiment.domain.entity.Investment
import br.com.santander.santanderinvestimento.util.showSnack
import kotlinx.android.synthetic.main.fragment_investment.*
import org.koin.android.ext.android.inject

class InvestmentFragment : BaseFragment(), InvestmentContract.View, SwipeRefreshLayout.OnRefreshListener {
    override fun showSuccess(investment: Investment) {
        txTitle.text = investment.title
        txFundName.text = investment.fundName
        txWhatsIs.text = investment.whatIs
        txDefinition.text = investment.definition
        txRiskTitle.text = investment.riskTitle
        txInfoTitle.text = investment.infoTitle

        fillRiskLayout(investment.risk)
        fillInfo(investment.info)
        fillDownInfo(investment.downInfo)
    }

    fun fillDownInfo(list :List<Info>){
        val listAdapter = InfoAdapter(list as MutableList<Info>)
        rcvDownInfo.adapter = listAdapter
        list.let { listAdapter.addOpenSourcesToList(it) }
    }

    fun fillInfo(list: List<Info>) {
        val listAdapter = InfoAdapter(list as MutableList<Info>)
        rcvInfo.adapter = listAdapter
        list.let { listAdapter.addOpenSourcesToList(it) }
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

    override val presenter: InvestmentContract.Presenter by inject()

    companion object {
        fun newInstance() = InvestmentFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_investment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        prepareSwipeRefreshLayout()
    }

    fun prepareRecyclerView() {

        rcvInfo.layoutManager = LinearLayoutManager(context)
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