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
import lucasonofre.santandertest.R.id.layout_contato_fragment
import lucasonofre.santandertest.R.id.layout_contato_sucessso
import lucasonofre.santandertest.adapter.ContatoItemAdapter
import lucasonofre.santandertest.adapter.FragmentInterface
import lucasonofre.santandertest.model.Cell
import lucasonofre.santandertest.model.ContactItens
import lucasonofre.santandertest.presenter.ContatoPresenter
import lucasonofre.santandertest.request.RequestItens
import retrofit2.Call
import retrofit2.Response

interface ContatoViewListener{
    fun showSuccessPage()
    fun hideSuccessPage()
    fun displayError(message:String)
    fun updateRecyclerView(items:ArrayList<ContactItens>)
}

class ContatoFragment:Fragment(), ContatoViewListener {

    var btnNewMessage:TextView?       = null
    var contactItemList:RecyclerView? = null

    var presenter:ContatoPresenter = ContatoPresenter()

    /**
     * Implementa a interface que recebe o click do botão
     */
    private val fragmentInterface = object : FragmentInterface {
        override fun onFragmentSelected() {
           presenter.validateFields()
        }
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_contato, container, false)

        setupViews(view)
        //setUpAdapter()

        presenter.bindTo(this)
        presenter?.requestFormItems(context!!)

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    private val btnNewMessageListener = object : View.OnClickListener {
        override fun onClick(v: View?) {
            hideSuccessPage()
        }
    }

    /**
     * Recupera os Ids e configura o click do botão
     */
    private fun setupViews(view: View?) {
        contactItemList = view?.findViewById(R.id.contact_item_list)
        btnNewMessage   = view?.findViewById(R.id.contact_sucess_fragment_btn)

        btnNewMessage?.setOnClickListener(btnNewMessageListener)
    }

    /**
     * Passa a resposta da chamada para o adapter ContatoItemAdapter
     */
    private fun setUpAdapter() {
        contactItemList?.layoutManager = LinearLayoutManager(context)
        contactItemList?.adapter       = ContatoItemAdapter(activity!!, presenter.items, fragmentInterface)
    }

    override fun showSuccessPage() {
        val handle = Handler()
        handle.postDelayed({
            layout_contato_fragment.visibility = View.VISIBLE
            layout_contato_sucessso.visibility = View.GONE
        },500)
    }

    override fun hideSuccessPage() {
        layout_contato_fragment.visibility = View.GONE
        layout_contato_sucessso.visibility = View.VISIBLE
    }

    override fun updateRecyclerView(items: ArrayList<ContactItens>) {
        contactItemList?.layoutManager = LinearLayoutManager(context)
        contactItemList?.adapter       = ContatoItemAdapter(activity!!, items, fragmentInterface)
        //contactItemList?.adapter?.notifyDataSetChanged()
    }

    override fun displayError(message: String) {
        Toast.makeText(context!!, message, Toast.LENGTH_SHORT).show()
    }
}
