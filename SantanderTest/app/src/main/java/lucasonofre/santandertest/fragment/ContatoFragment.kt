package lucasonofre.santandertest.fragment


import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_contato.*
import lucasonofre.santandertest.R
import lucasonofre.santandertest.adapter.ContatoItemAdapter
import lucasonofre.santandertest.adapter.FragmentInterface
import lucasonofre.santandertest.model.Cell
import lucasonofre.santandertest.model.ContactItens
import lucasonofre.santandertest.request.RequestItens
import retrofit2.Call
import retrofit2.Response


class ContatoFragment : Fragment() {

    var btnNewMessage:TextView? = null

    /**
     * Implementing interface to handle the click on the movie
     */
    private val fragmentInterface = object : FragmentInterface {
        override fun onFragmentSelected() {
            openFragmentSucessoContato()
        }

    }

    private fun openFragmentSucessoContato() {

        Toast.makeText(context,"Mensagem enviada",Toast.LENGTH_SHORT).show()

        layout_contato_fragment.visibility = View.GONE
        layout_contato_sucessso.visibility = View.VISIBLE

    }

    var contactItemList :RecyclerView?               = null
    var arrayListInputItem :ArrayList<ContactItens>? = null
    var contactItem :Cell?                           = null

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view =  inflater.inflate(R.layout.fragment_contato, container, false)

        requestCell()
        setupViews(view)


        return view
    }

    private fun setupViews(view: View?) {

        contactItemList     = view?.findViewById(R.id.contact_item_list)
        btnNewMessage       = view?.findViewById(R.id.contact_sucess_fragment_btn)

        btnNewMessage?.setOnClickListener {

            val handle = Handler()
            handle.postDelayed({

                layout_contato_fragment.visibility = View.VISIBLE
                layout_contato_sucessso.visibility = View.GONE


            },500)
        }
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
