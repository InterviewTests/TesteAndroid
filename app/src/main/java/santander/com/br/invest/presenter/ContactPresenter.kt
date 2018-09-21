package santander.com.br.invest.presenter

import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import santander.com.br.invest.contract.ContactContract
import santander.com.br.invest.model.Cell
import santander.com.br.invest.model.CellRemote
import santander.com.br.invest.model.CellTransformer
import santander.com.br.invest.repository.ContactRepository
import santander.com.br.invest.util.ValidatorFields
import javax.inject.Inject

class ContactPresenter @Inject constructor(
    private val contactRepository: ContactRepository
) : ContactContract.Presenter {

  private val TAG: String = "ContactPresenter"

  private lateinit var view: ContactContract.View
  private var cellList: List<CellRemote>? = null
  private var cellListFormatted: ArrayList<Cell>? = null

  private var disposable: Disposable? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    if (savedInstanceState == null) {
      getCellsInfo()
    } else {
      @Suppress("UNCHECKED_CAST")
      cellListFormatted = savedInstanceState[ContactContract.CELL_LIST_KEY] as ArrayList<Cell>
      getCellsInfo()
    }

  }

  override fun bindView(view: ContactContract.View) {
    this.view = view
  }

  override fun sendContact(name: String?, phone: String?, email: String?, registerEmail: Boolean) {

    var hasError = false

    if (ValidatorFields.isInvalidName(name)) {
      view.showNameErrorMessage()
      hasError = true
    }

    if (ValidatorFields.isPhoneInvalid(phone)) {
      view.showPhoneErrorMessage()
      hasError = true
    }

    if (ValidatorFields.isInvalidEmail(email) && registerEmail) {
      view.showEmailErrorMEssage()
      hasError = true
    }

    if (hasError) return

    view.hideContactLayout()
    view.showMessageSendSuccessful()
  }

  override fun getContact() {
    getCellsInfo()
  }

  override fun showFormsAgain() {
    getCellsInfo()
  }

  private fun getCellsInfo() {
    if (cellList == null && cellListFormatted == null) {
      disposable = contactRepository.getCells()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe {
            showLoading()
          }
          .subscribe(
              { cells ->
                cellList = cells.cells
                cellList?.let {
                  formatCells(it)
                }
              },
              {
                Log.d(TAG, it.message)
                showErrorView()
              }
          )
    } else {

      cellListFormatted?.let {
        view.createContactForm(it)
        return
      }
      cellList?.let {
        formatCells(it)
      }
    }

  }

  private fun showErrorView() {
    view.hideLoading()
    view.hideContactLayout()
    view.showErrorView()
  }

  private fun showLoading() {
    view.hideContactLayout()
    view.hideErrorView()
    view.showLoading()
  }

  private fun formatCells(cellResponseList: List<CellRemote>) {
    view.hideMessageSendSuccessful()
    view.hideLoading()
    view.showContactLayout()
    view.createContactForm(CellTransformer.transformerCells(cellResponseList))
    disposable?.dispose()
  }
}
