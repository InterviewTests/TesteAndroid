package lucasonofre.santandertest.fragment


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    var contactItem :Cell?                           = null
    var btnNewMessage:TextView?                      = null
    var contactItemList :RecyclerView?               = null
    var arrayListInputItem :ArrayList<ContactItens>? = null


    /**
     * Implementa a interface que recebe o click do botão
     */
    private val fragmentInterface = object : FragmentInterface {
        override fun onFragmentSelected() {
            openFragmentContatoSucesso()
        }
    }

    /**
     * Abre o fragment ContatoSucesso, tornando o mesmo visível e tornando invisível o fragment Contato
     */
    private fun openFragmentContatoSucesso() {

        Toast.makeText(context,"Mensagem enviada",Toast.LENGTH_SHORT).show()

        layout_contato_fragment.visibility = View.GONE
        layout_contato_sucessso.visibility = View.VISIBLE

    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view =  inflater.inflate(R.layout.fragment_contato, container, false)

        requestCell()
        setupViews(view)


        return view
    }

    /**
     * Recupera os Ids e configura o click do botão
     */
    private fun setupViews(view: View?) {

        contactItemList     = view?.findViewById(R.id.contact_item_list)
        btnNewMessage       = view?.findViewById(R.id.contact_sucess_fragment_btn)

        btnNewMessage?.setOnClickListener {

            //Handler para haver delay ao abrir a tela de Contato para uma nova mensagem
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
                    setUpAdapter(contactItem)

                }
            })
        }
    }

    /**
     * Passa a resposta da chamada para o adapter ContatoItemAdapter
     */
    private fun setUpAdapter(contactItem: Cell?) {

        arrayListInputItem  = contactItem?.contactItens
        contactItemList?.layoutManager = LinearLayoutManager(context)
        contactItemList?.adapter       = ContatoItemAdapter(activity!!, arrayListInputItem!!,fragmentInterface)
    }



}
