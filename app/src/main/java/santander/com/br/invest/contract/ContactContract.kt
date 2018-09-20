package santander.com.br.invest.contract

import santander.com.br.invest.model.Cell

object ContactContract {

  const val CELL_LIST_KEY = "cell_list_key"

  interface Presenter : PresenterFragment<View> {
    fun sendContact(name: String?, phone: String?, email: String?, registerEmail: Boolean)
    fun showFormsAgain()
    fun getContact()
  }

  interface View {
    fun showContactLayout()
    fun hideContactLayout()

    fun showErrorView()
    fun hideErrorView()

    fun showLoading()
    fun hideLoading()

    fun createContactForm(cellList: ArrayList<Cell>)

    fun showNameErrorMessage()
    fun showEmailErrorMEssage()
    fun showPhoneErrorMessage()

    fun showMessageSendSuccessful()
    fun hideMessageSendSuccessful()
  }
}
