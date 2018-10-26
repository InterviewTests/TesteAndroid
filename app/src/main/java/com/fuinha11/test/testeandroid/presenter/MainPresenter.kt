package com.fuinha11.test.testeandroid.presenter

import android.content.Context
import android.view.View.GONE
import android.view.View.VISIBLE
import com.fuinha11.test.testeandroid.contract.MainScreenContracts
import com.fuinha11.test.testeandroid.data.model.Cell
import com.fuinha11.test.testeandroid.data.model.Investment
import com.fuinha11.test.testeandroid.service.api.ApiService
import com.fuinha11.test.testeandroid.service.api.data.response.CellsResponse
import com.fuinha11.test.testeandroid.service.api.data.response.FundResponse
import com.fuinha11.test.testeandroid.ui.view.CellHolder
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.androidannotations.annotations.RootContext



@EBean
open class MainPresenter :
        BasePresenter<MainScreenContracts.View>(),
        MainScreenContracts.Presenter {

    @Bean
    lateinit var apiService: ApiService

    val cells: MutableList<CellHolder> = mutableListOf()


    override fun getFields() {
        view.showLoading()
        apiService.getCells(object : Callback<CellsResponse>{
            override fun onFailure(call: Call<CellsResponse>, t: Throwable) {
                view.hideLoading()
                onError(Exception(t))
            }

            override fun onResponse(call: Call<CellsResponse>, response: Response<CellsResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val resp = response.body() as CellsResponse
                    for (c in resp.cells) {
                        val holder = CellHolder(c, activity)
                        if (holder.cell.type == Cell.CellType.send)
                            holder.setCallback { sendBtnClick() }
                        if (holder.cell.show != null)
                            holder.setCallback { toogleView(holder.cell.show) }
                        cells.add(holder)
                    }
                    view.populateContactFragment(cells)
                } else
                    onError(Exception("Impossible to find cells"))

                view.hideLoading()
            }
        })
    }

    private fun toogleView(show: Int) {
        for(c in cells) {
            if (c.cell.id == show) {
                if (c.view.visibility == GONE)
                    c.view.visibility = VISIBLE
                else
                    c.view.visibility = GONE
                break
            }
        }
    }

    override fun getInvestment() {
        view.showLoading()
        apiService.getInvestment(object : Callback<FundResponse>{
            override fun onFailure(call: Call<FundResponse>, t: Throwable) {
                view.hideLoading()
                onError(Exception(t))
            }

            override fun onResponse(call: Call<FundResponse>, response: Response<FundResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val resp = response.body() as FundResponse
                    view.populateInvestmentFragment(resp.screen)
                } else
                    onError(Exception("Impossible to find cells"))

                view.hideLoading()
            }
        })
    }

    override fun sendBtnClick() {
        view.dismissKeyboard()
        if (fieldsAreValid(cells))
            view.showThankYouView()
        else
            view.showCellErrors()
    }

    override fun fieldsAreValid(cells: List<CellHolder>): Boolean {
        for (c in cells) {
            if (!c.isValid())
                return false
        }
        return true
    }

}