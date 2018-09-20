package santander.com.br.invest.presenter

import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import santander.com.br.invest.contract.InvestmentContract
import santander.com.br.invest.model.Info
import santander.com.br.invest.model.Screen
import santander.com.br.invest.repository.InvestmentRepository
import javax.inject.Inject

class InvestmentPresenter @Inject constructor(
    private val investmentRepository: InvestmentRepository
) : InvestmentContract.Presenter {

  private val TAG: String = "InvestmentPresenter"

  private lateinit var view: InvestmentContract.View
  private var screen: Screen? = null

  private var disposable: Disposable? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    if (savedInstanceState == null) {
      getFundInfo()
    } else {
      screen = savedInstanceState[InvestmentContract.SCREEN_KEY] as Screen
      getFundInfo()
    }
  }

  override fun bindView(view: InvestmentContract.View) {
    this.view = view
  }

  override fun getFunds() {
    getFundInfo()
  }

  private fun getFundInfo() {
    if (screen == null) {
      disposable = investmentRepository.getFunds()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe {
            showLoading()
          }
          .subscribe(
              { fund ->
                screen = fund.screen
                screen?.let {
                  formatScreenData(it)
                }
              },
              {
                showErrorView(it)
              }
          )
    } else {
      screen?.let {
        formatScreenData(it)
      }
    }
  }

  private fun showInvestmentLayout() {
    view.hideLoading()
    view.hideErrorView()
    view.showMainLayout()
  }

  private fun showLoading() {
    view.hideMainLayout()
    view.hideErrorView()
    view.showLoading()
  }

  private fun showErrorView(it: Throwable) {
    Log.e(TAG, it.message)
    view.hideMainLayout()
    view.hideLoading()
    view.showErrorView()
  }

  private fun updateScreenInfo(screen: Screen, infoList: ArrayList<Info>) {
    showInvestmentLayout()
    view.updateFundInfo(screen)
    view.updateFundList(infoList)

    disposable?.dispose()
  }

  private fun formatScreenData(screen: Screen) {
    val infoList: ArrayList<Info> = ArrayList()
    infoList.addAll(screen.infoList)
    infoList.addAll(screen.downInfoList)

    updateScreenInfo(screen, infoList)
  }
}
