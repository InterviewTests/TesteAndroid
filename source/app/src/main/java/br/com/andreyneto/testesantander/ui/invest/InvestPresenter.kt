package br.com.andreyneto.testesantander.ui.invest

import br.com.andreyneto.testesantander.model.InvestResponse
import br.com.andreyneto.testesantander.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InvestPresenter(
        val view: InvestContract.View
): InvestContract.Presenter {

    private val apiService = ApiService()

    init {
        view.setPresenter(this)
    }

    override fun getData() {
        apiService.getApi().fund.enqueue(object : Callback<InvestResponse> {
            override fun onResponse(call: Call<InvestResponse>, response: Response<InvestResponse>) {
                if (response.isSuccessful) {
                    view.showData()
                }
            }

            override fun onFailure(call: Call<InvestResponse>, t: Throwable) {

            }
        })
    }
}