package br.com.andreyneto.testesantander.ui.contact

import br.com.andreyneto.testesantander.model.ContactResponse
import br.com.andreyneto.testesantander.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactPresenter(
        val view: ContactContract.View): ContactContract.Presenter {
    override fun sendForm() {
        view.formSended()
    }

    override fun newMessage() {
        view.showForm()
    }

    private val apiService = ApiService()

    init {
        view.setPresenter(this)
    }

    override fun getCells() {

        apiService.getApi().cells.enqueue(object : Callback<ContactResponse> {
            override fun onResponse(call: Call<ContactResponse>, response: Response<ContactResponse>) {
                if(response.isSuccessful) view.showCells(response.body()?.cells!!)
            }
            override fun onFailure(call: Call<ContactResponse>, t: Throwable) {

            }
        })
    }

    override fun start() {
        getCells()
    }
}