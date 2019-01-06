package lucasonofre.santandertest.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import lucasonofre.santandertest.R
import lucasonofre.santandertest.model.Cell
import lucasonofre.santandertest.model.Screen
import lucasonofre.santandertest.request.RequestItens
import retrofit2.Call
import retrofit2.Response


class InvestimentoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_investimento, container, false)

        return view
    }

    /**
     * Chamada que recebe a requisição da tela de contatos
     */
    private fun requestFund() {

        activity?.let {

            RequestItens(it).getFund().enqueue(object:retrofit2.Callback<Screen>{
                override fun onFailure(call: Call<Screen>, t: Throwable) {
                    Log.i("Error", t.message)
                }

                override fun onResponse(call: Call<Screen>, response: Response<Screen>) {
                    Log.i("Response", response.body().toString())
                }
            })
        }

    }


}
