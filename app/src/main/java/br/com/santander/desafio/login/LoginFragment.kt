package br.com.santander.desafio.login

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.annotation.Nullable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import br.com.enzoteles.quickhelp.fragment.HelpFragment
import br.com.enzoteles.quickhelp.mask.HelpMask
import br.com.santander.desafio.main.MainActivity
import br.com.santander.desafio.R
import br.com.santander.desafio.detail.DetailFragment
import br.com.santander.desafio.webservice.cells.CellsItem
import br.com.santander.desafio.webservice.cells.ResponseCells
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*


class LoginFragment: HelpFragment(), LoginMVP.View, View.OnClickListener{

    lateinit var presenter: LoginMVP.Presenter
    lateinit var login: LoginFragment
    lateinit var detail: DetailFragment
    var mPhoneTextWatcher: TextWatcher?= null

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
        val contato = getString(R.string.lg_bt_contact)
        toolbar.tb_tv_title.setText(contato)
        lg_bt_send.setOnClickListener(this)
        msg_btn_send.setOnClickListener(this)
        avi.show()
        inputPhone()
    }
    override fun initDate() {
        presenter.getCells()?.observe(activity as MainActivity, object : Observer<ResponseCells> {
            override fun onChanged(@Nullable response: ResponseCells?) {
                avi.hide()
                lg_ll_options.visibility = View.VISIBLE
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
            lg_et_name.hint = cell!!.message
        }else if(cell.id == 3 && !cell.hidden!!){
            lg_cb_email.visibility = View.VISIBLE
            lg_cb_email.setText(cell.message)
        }else if(cell.id == 4 && !cell.hidden!!){
            log_it_email.visibility = View.VISIBLE
            lg_et_email.setText(cell.message)
        }else if(cell.id == 6 && !cell.hidden!!){
            log_it_phone.visibility = View.VISIBLE
            //lg_et_phone.setHint(cell.message)
            lg_et_phone.hint = cell!!.message
        }else if(cell.id == 7 && !cell.hidden!!){
            lg_bt_send.visibility = View.VISIBLE
            lg_bt_send.setText(cell.message)
        }
    }

    override fun inputPhone() {

        if(mPhoneTextWatcher == null){
            mPhoneTextWatcher = HelpMask.insertPhone(lg_et_phone)
        } else{
            lg_et_phone.removeTextChangedListener(mPhoneTextWatcher)
            mPhoneTextWatcher = HelpMask.insertPhone(lg_et_phone)
        }
        lg_et_phone.addTextChangedListener(mPhoneTextWatcher)
        lg_et_phone.inputType = InputType.TYPE_CLASS_PHONE
    }

    override fun validationEditText(lg_et_name: EditText?, lg_et_email: EditText?) {
        if(lg_et_name!!.text.isNullOrEmpty()){
            lg_et_name.setError(getString(R.string.validation_tx_name))
        }

        if(lg_et_phone!!.text.isNullOrEmpty()){
            lg_et_phone.setError(getString(R.string.validation_tx_phone))
        }
    }

    override fun onClick(v: View?) {



        when(v!!.id) {
            R.id.lg_bt_send -> {
                if(lg_et_name!!.text.isNullOrEmpty() || lg_et_phone!!.text.isNullOrEmpty()){
                    validationEditText(lg_et_name, lg_et_phone)
                }else{
                    lg_ll_options.visibility = View.GONE
                    lg_ll_msg.visibility = View.VISIBLE
                }
            }
            R.id.msg_btn_send -> {
                lg_ll_options.visibility = View.VISIBLE
                lg_ll_msg.visibility = View.GONE
            }
        }

    }

}



