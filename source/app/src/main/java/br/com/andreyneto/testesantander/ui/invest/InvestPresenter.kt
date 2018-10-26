package br.com.andreyneto.testesantander.ui.invest

import android.content.Context
import android.widget.Toast
import br.com.andreyneto.testesantander.model.InvestResponse
import br.com.andreyneto.testesantander.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InvestPresenter(
        val view: InvestContract.View
): InvestContract.Presenter {

    override fun toast(ctx: Context, text: String) {
        Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show()
    }

    override fun start() {
        getData()
    }

    private val apiService = ApiService()

    init {
        view.setPresenter(this)
    }

    override fun getData() {
        apiService.getApi().fund.enqueue(object : Callback<InvestResponse> {
            override fun onResponse(call: Call<InvestResponse>, response: Response<InvestResponse>) {
                if (response.isSuccessful) {
                    view.showData(response.body()?.screen!!)
                }
            }

            override fun onFailure(call: Call<InvestResponse>, t: Throwable) {

            }
        })
    }
}