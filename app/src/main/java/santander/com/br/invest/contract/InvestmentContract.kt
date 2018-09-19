package santander.com.br.invest.contract

import santander.com.br.invest.model.Info
import santander.com.br.invest.model.Screen

object InvestmentContract {

  const val SCREEN_KEY = "screen_key"

  interface Presenter : PresenterFragment<View> {

    fun getFunds()
  }

  interface View {

    fun showMainLayout()
    fun hideMainLayout()

    fun showErrorView()
    fun hideErrorView()

    fun showLoading()
    fun hideLoading()

    fun updateFundInfo(screen: Screen)

    fun updateFundList(infoList: ArrayList<Info>)

  }

}

