package lucasonofre.santandertest.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lucasonofre.santandertest.R
import lucasonofre.santandertest.adapter.ContatoItemAdapter
import lucasonofre.santandertest.model.Cell
import lucasonofre.santandertest.model.ContactItens
import lucasonofre.santandertest.request.RequestItens
import retrofit2.Call
import retrofit2.Response


class ContatoFragment : Fragment() {

    var contactItemList:RecyclerView?               = null
    var arrayListInputItem:ArrayList<ContactItens>? = null
    var contactItem:Cell?                           = null

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view =  inflater.inflate(R.layout.fragment_contato, container, false)

        requestCell()
        setupViews(view)


        return view
    }

    private fun setupViews(view: View?) {

        contactItemList     = view?.findViewById(R.id.contact_item_list)

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
                    contactItem = response.body()
                    setupInfo(contactItem)

                }
            })
        }
    }

    private fun setupInfo(contactItem: Cell?) {

        arrayListInputItem  = contactItem?.contactItens
        contactItemList?.layoutManager = LinearLayoutManager(context)
        contactItemList?.adapter       = ContatoItemAdapter(activity!!, arrayListInputItem!!)
    }


}
