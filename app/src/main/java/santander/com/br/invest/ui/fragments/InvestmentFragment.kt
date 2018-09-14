package santander.com.br.invest.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.investment_fragment.*
import santander.com.br.invest.R
import santander.com.br.invest.contract.InvestmentContract
import santander.com.br.invest.di.component.DaggerInvestmentComponent
import santander.com.br.invest.model.Screen
import javax.inject.Inject

class InvestmentFragment : Fragment(), InvestmentContract.View {

  @Inject
  lateinit var presenter: InvestmentContract.Presenter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.investment_fragment, container, false)

    initDagger()
    initView(savedInstanceState)

    return view
  }

  private fun initView(savedInstanceState: Bundle?) {
    presenter.bindView(this)
    presenter.onCreate(savedInstanceState)
  }

  private fun initDagger() {
    DaggerInvestmentComponent.builder().build().inject(this@InvestmentFragment)
  }

}
