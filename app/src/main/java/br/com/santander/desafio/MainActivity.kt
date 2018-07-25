package br.com.santander.desafio

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.enzoteles.quickhelp.fragment.HelpManagerFragment
import br.com.enzoteles.quickhelp.security.HelpSecurity
import br.com.santander.desafio.ContentFragment.ContentFragment
import br.com.santander.desafio.login.LoginFragment

class MainActivity : AppCompatActivity() {

    lateinit var content : ContentFragment
    lateinit var manager: HelpManagerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Constants.context = this
        manager = HelpManagerFragment(this)
        HelpSecurity.manager = manager
        content = ContentFragment()
        manager!!.addFragment(R.id.content, content, "login", false)
    }
}
