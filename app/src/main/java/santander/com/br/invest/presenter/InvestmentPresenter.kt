package santander.com.br.invest.presenter

import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import santander.com.br.invest.contract.InvestmentContract
import santander.com.br.invest.model.Screen
import santander.com.br.invest.repository.InvestmentRepository
import javax.inject.Inject

class InvestmentPresenter @Inject constructor(
    private val investmentRepository: InvestmentRepository
) : InvestmentContract.Presenter {

  private lateinit var view: InvestmentContract.View

  override fun onCreate(savedInstanceState: Bundle?) {
  }

  override fun bindView(view: InvestmentContract.View) {
  }

}
