package br.com.santander.desafio.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.enzoteles.quickhelp.fragment.HelpManagerFragment
import br.com.enzoteles.quickhelp.security.HelpSecurity
import br.com.santander.desafio.Constants
import br.com.santander.desafio.R
import br.com.santander.desafio.content.ContentFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var content : ContentFragment
    @Inject
    lateinit var manager: HelpManagerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Constants.context = this

        initInject()
        HelpSecurity.manager = manager
        manager!!.addFragment(R.id.content, content, "login", false)
    }

    private fun initInject() {

        val loginComponent = DaggerMainComponent.builder()
                .mainModule(MainModule(this))
                .build()
        loginComponent.inject(this)
    }
}
