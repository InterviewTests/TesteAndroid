package br.com.santander.desafio.login

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.enzoteles.quickhelp.fragment.HelpFragment
import br.com.santander.desafio.MainActivity
import br.com.santander.desafio.R
import br.com.santander.desafio.detail.DetailFragment
import br.com.santander.desafio.webservice.cells.CellsItem
import br.com.santander.desafio.webservice.cells.ResponseCells
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*


class LoginFragment: HelpFragment(), LoginMVP.View{
    lateinit var presenter: LoginMVP.Presenter
    lateinit var login: LoginFragment
    lateinit var detail: DetailFragment

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.login, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = LoginPresenter()
        initUI()
        initDate()
    }

    @SuppressLint("ResourceType")
    override fun initUI() {
        val contato = getString(R.id.lg_bt_contact) as String
        toolbar.tb_tv_title.setText(contato)
    }
    override fun initDate() {
        presenter.getCells()?.observe(activity as MainActivity, object : Observer<ResponseCells> {
            override fun onChanged(@Nullable response: ResponseCells?) {
                verificationCells(response)
            }
        })
    }

    override fun verificationCells(responses: ResponseCells?) {
        responses!!.cells!!.forEach {
            cell -> validationCampos(cell)
        }
    }

    override fun validationCampos(cell: CellsItem?) {

        if(cell!!.id == 2 && !cell!!.hidden!!){
            log_it_name.visibility = View.VISIBLE
            lg_et_name.setText(cell!!.message)
        }else if(cell.id == 3 && !cell.hidden!!){
            lg_cb_email.visibility = View.VISIBLE
            lg_cb_email.setText(cell.message)
        }else if(cell.id == 4 && !cell.hidden!!){
            log_it_email.visibility = View.VISIBLE
            lg_et_email.setText(cell.message)
        }else if(cell.id == 6 && !cell.hidden!!){
            log_it_phone.visibility = View.VISIBLE
            lg_et_phone.setText(cell.message)
        }else if(cell.id == 7 && !cell.hidden!!){
            lg_bt_send.visibility = View.VISIBLE
            lg_bt_send.setText(cell.message)
        }
    }

}



