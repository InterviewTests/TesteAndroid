package santander.com.br.invest.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_error.*
import kotlinx.android.synthetic.main.fragment_investment.*
import kotlinx.android.synthetic.main.layout_risk_view.*
import santander.com.br.invest.R
import santander.com.br.invest.R.string.number_percentage_placeholder
import santander.com.br.invest.contract.InvestmentContract
import santander.com.br.invest.di.component.DaggerInvestmentComponent
import santander.com.br.invest.extension.gone
import santander.com.br.invest.extension.visible
import santander.com.br.invest.model.Info
import santander.com.br.invest.model.MoreInfo
import santander.com.br.invest.model.Screen
import santander.com.br.invest.ui.adapter.InvestAdapter
import javax.inject.Inject

class InvestmentFragment : Fragment(), InvestmentContract.View {

  @Inject
  lateinit var presenter: InvestmentContract.Presenter

  private var investView: View? = null

  private val RISK_SELECTED_HEIGHT by lazy { TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15f, resources.displayMetrics).toInt() }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    investView = inflater.inflate(R.layout.fragment_investment, container, false)

    initDagger()

    return investView
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(savedInstanceState)
    initListeners()
  }

  override fun showMainLayout() {
    mainLayout.visible()
  }

  override fun hideMainLayout() {
    mainLayout.gone()
  }

  override fun showErrorView() {
    viewError.visible()
  }

  override fun hideErrorView() {
    viewError.gone()
  }

  override fun showLoading() {
    progressBar.visible()
  }

  override fun hideLoading() {
    progressBar.gone()
  }

  override fun updateFundInfo(screen: Screen) {
    txtTitle.text = screen.title
    txtFundName.text = screen.fundName
    txtWhatIs.text = screen.whatIs
    txtDefinition.text = screen.definition
    txtRiskLevel.text = screen.riskTitle
    txtInfoTitle.text = screen.infoTitle

    setRiskLevel(screen.risk)

    setTaxInfo(screen.taxInfo)
  }

  override fun updateFundList(infoList: ArrayList<Info>) {
    initRecycler(infoList)
  }

  private fun initView(savedInstanceState: Bundle?) {
    presenter.bindView(this)
    presenter.onCreate(savedInstanceState)
  }

  private fun initDagger() {
    DaggerInvestmentComponent.builder().build().inject(this@InvestmentFragment)
  }

  private fun initListeners() {
    tryAgain.setOnClickListener {
      presenter.getFunds()
    }
  }

  private fun initRecycler(infoList: ArrayList<Info>) {
    val investAdapter = InvestAdapter(infoList)
    recycleViewInfo.adapter = investAdapter
    investAdapter.notifyDataSetChanged()
  }

  private fun setRiskLevel(riskLevel: Int) {
    when (riskLevel) {
      1 -> {
        imageOne.visible()
        setRiskViewHeight(viewOne)
      }
      2 -> {
        imageTwo.visible()
        setRiskViewHeight(viewTwo)
      }
      3 -> {
        imageThree.visible()
        setRiskViewHeight(viewThree)
      }
      4 -> {
        imageFour.visible()
        setRiskViewHeight(viewFour)
      }
      5 -> {
        imageFive.visible()
        setRiskViewHeight(viewFive)
      }
    }

  }

  private fun setRiskViewHeight(riskView: View) {
    val layoutParameters: ViewGroup.LayoutParams = riskView.layoutParams
    layoutParameters.height = RISK_SELECTED_HEIGHT
    riskView.layoutParams = layoutParameters
  }

  private fun setTaxInfo(taxInfo: MoreInfo) {
    txtCdiMonth.text = getString(number_percentage_placeholder, taxInfo.month.cdi)
    txtFundMonth.text = getString(number_percentage_placeholder, taxInfo.month.fund)

    txtCdiYear.text = getString(number_percentage_placeholder, taxInfo.year.cdi)
    txtFundYear.text = getString(number_percentage_placeholder, taxInfo.year.fund)

    txt12Cdi.text = getString(number_percentage_placeholder, taxInfo.months12.cdi)
    txt12Fund.text = getString(number_percentage_placeholder, taxInfo.months12.fund)
  }
}
