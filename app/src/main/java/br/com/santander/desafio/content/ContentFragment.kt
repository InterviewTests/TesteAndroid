package br.com.santander.desafio.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.enzoteles.quickhelp.fragment.HelpFragment
import br.com.enzoteles.quickhelp.security.HelpSecurity
import br.com.santander.desafio.R
import br.com.santander.desafio.detail.DetailFragment
import br.com.santander.desafio.login.LoginFragment
import kotlinx.android.synthetic.main.content.*

/**
 * Created by Enzo Teles on 22,June,2018
 * Barueri - SP
 * email: enzo.carvalho.teles@gmail.com
 * Software Developer Senior Mobile
 */
class ContentFragment: HelpFragment(), View.OnClickListener{

    lateinit var login: LoginFragment
    lateinit var detail: DetailFragment

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.content, container, false)
        return view

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login = LoginFragment()
        detail = DetailFragment()
        lg_bt_investment.setOnClickListener(this)
        lg_bt_contact.setOnClickListener(this)
        HelpSecurity.manager!!.addFragment(R.id.ct_ll_body, login, "login", false)
    }

    override fun onClick(v: View?) {

        when(v!!.id) {
            R.id.lg_bt_contact -> HelpSecurity.manager!!.replaceFragment(R.id.ct_ll_body, login, "login", false)
            R.id.lg_bt_investment -> HelpSecurity.manager!!.replaceFragment(R.id.ct_ll_body, detail, "detail", false)
        }
    }

}