package br.com.santander.santanderinvestimento.feature.investiment.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.santander.santanderinvestimento.R
import br.com.santander.santanderinvestimento.util.presentation.BaseFragment
import br.com.santander.santanderinvestimento.feature.investiment.adapter.DownInfoAdapter
import br.com.santander.santanderinvestimento.feature.investiment.adapter.InfoAdapter
import br.com.santander.santanderinvestimento.feature.investiment.domain.entity.Info
import br.com.santander.santanderinvestimento.feature.investiment.domain.entity.Investment
import br.com.santander.santanderinvestimento.feature.investiment.domain.entity.TimeInfo
import br.com.santander.santanderinvestimento.util.StringHelper.Companion.formatDoubleToString
import br.com.santander.santanderinvestimento.util.showSnack
import kotlinx.android.synthetic.main.fragment_investment.*

class InvestmentFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener  {

    private val investmentViewModel: InvestmentViewModel by activityViewModels()

    fun showSuccess(investment: Investment) {
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

    private fun fillMoreInfo(moreInfo: TimeInfo) {
        txMonthFound.text = formatDoubleToString(moreInfo.month?.fund)
        txMonthCDI.text = formatDoubleToString(moreInfo.month?.CDI)

        txYearFound.text = formatDoubleToString(moreInfo.year?.fund)
        txYearCDI.text = formatDoubleToString(moreInfo.year?.CDI)

        tx12MonthFound.text = formatDoubleToString(moreInfo.months12?.fund)
        tx12MonthCDI.text = formatDoubleToString(moreInfo.months12?.CDI)
    }


    private fun fillDownInfo(list: List<Info>) {
        val listAdapter = DownInfoAdapter(list as MutableList<Info>) { item: String -> investmentViewModel.clickDownload(item) }
        rcvDownInfo.adapter = listAdapter
    }

    private fun fillInfo(list: List<Info>) {
        val listAdapter = InfoAdapter(list as MutableList<Info>)
        rcvInfo.adapter = listAdapter
    }

    private fun fillRiskLayout(risk: Int) {
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
        investmentViewModel.loadFeed()
    }

    fun showMessage(message: String) {
        view?.showSnack(message)
    }

    fun showLoading(active: Boolean) {
        swiperefresh.isRefreshing = active
    }

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
        ivShare.setOnClickListener { investmentViewModel.clickShare() }
        btnInvest.setOnClickListener { investmentViewModel.clickInvest() }
        initViewBinding()
        observeViewModel()
    }

    fun initViewBinding() {
        //binding = LoginActivityBinding.inflate(layoutInflater)
        //val view = binding.root
        //setContentView(view)
    }

    fun observeViewModel() {
        //observe(loginViewModel.loginLiveData, ::handleLoginResult)
        //observeSnackBarMessages(loginViewModel.showSnackBar)
        //observeToast(loginViewModel.showToast)
    }

    private fun prepareRecyclerView() {
        rcvInfo.layoutManager = LinearLayoutManager(context)
        rcvDownInfo.layoutManager = LinearLayoutManager(context)
    }

    private fun prepareSwipeRefreshLayout() {
        swiperefresh?.setColorSchemeColors(Color.RED)
        swiperefresh?.setOnRefreshListener(this)
    }


    override fun onResume() {
        super.onResume()
        investmentViewModel.start()
    }

}