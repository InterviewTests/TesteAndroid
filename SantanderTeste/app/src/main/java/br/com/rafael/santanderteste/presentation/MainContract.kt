package br.com.rafael.santanderteste.presentation

/**
 * Classe que representa o contrato para a tela de Pricipal
 * A classe Presenter do Cell deve implementar a interface Presenter
 */
class MainContract {

    interface View {

       fun showInvestimentFragment()

        fun showContactFragment()

    }

    interface Presenter {

        fun initInvestimentFragment()

        fun initContactFragment()

        fun setView(view: MainContract.View)
    }
}