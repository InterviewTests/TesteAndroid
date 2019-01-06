package lucasonofre.santandertest.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lucasonofre.santandertest.R
import lucasonofre.santandertest.model.Cell
import lucasonofre.santandertest.request.RequestItens
import retrofit2.Call
import retrofit2.Response


class ContatoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view =  inflater.inflate(R.layout.fragment_contato, container, false)

        setupViews(view)

        return view
    }

    private fun setupViews(view: View?) {


    }

    /**
     * Chamada que recebe a requisição da tela de investimentos
     */
    private fun requestCell() {

        activity?.let {

            RequestItens(it).getCells().enqueue(object:retrofit2.Callback<Cell>{
                override fun onFailure(call: Call<Cell>, t: Throwable) {
                    Log.i("Error", t.message)
                }

                override fun onResponse(call: Call<Cell>, response: Response<Cell>) {
                    Log.i("Response", response.body().toString())
                }
            })
        }

    }


}
