package br.com.rafael.santanderteste.presentation.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import br.com.rafael.santanderteste.R
import br.com.rafael.santanderteste.helper.FragmentHelper
import br.com.rafael.santanderteste.presentation.MainContract
import br.com.rafael.santanderteste.presentation.MainPresenter
import br.com.rafael.santanderteste.presentation.fragment.ContactFragment
import br.com.rafael.santanderteste.presentation.fragment.FundFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private var investimentLabel: String? = ""
    private var contactLabel: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        investimentLabel = resources.getString(R.string.investiment)
        contactLabel = resources.getString(R.string.contact)

        setSupportActionBar(toolbarMain)
        var colorDrawable = ColorDrawable(resources.getColor(R.color.colorPrimaryDark))
        toolbarMain.setTitleTextColor(colorDrawable.color)
        toolbarMain.title = investimentLabel

        var mainPresenter = MainPresenter()
        mainPresenter.setView(this)

        // Inicializa a tela principal com o fragment de Investimentos
        mainPresenter.initInvestimentFragment()

        btInvestiment.setOnClickListener {
            mainPresenter.initInvestimentFragment()
        }

        btContact.setOnClickListener {
            mainPresenter.initContactFragment()
        }
    }

    // Configura a chamada para o fragment de Investimento se exibido
    override fun showInvestimentFragment() {
        toolbarMain.title = investimentLabel
        setupInvestButton()
        setupFragment(FundFragment())
    }

    // Configura a chamada para o fragment de Contato se exibido
    override fun showContactFragment() {
        toolbarMain.title = contactLabel
        setupContactButton()
        setupFragment(ContactFragment())
    }

    // Atualiza a cor do fundo do botao de Investimento, deixando-o em modo ativo
    private fun setupInvestButton() {
        btInvestiment.setBackgroundResource(R.color.red_dark)
        btContact.setBackgroundResource(R.color.red_light)
    }

    // Atualiza a cor do fundo do botao de Contato, deixando-o em modo ativo
    private fun setupContactButton() {
        btInvestiment.setBackgroundResource(R.color.red_light)
        btContact.setBackgroundResource(R.color.red_dark)
    }

    /**
     * Configura um novo fragment que será atualizado no frame principal
     * @param fragment Instancia do fragment que será atualizado no frame principal
     */
    private fun setupFragment(fragment: Fragment) {
        FragmentHelper.replace_framgment(supportFragmentManager,
            R.id.frameMain,
            fragment)
    }

}