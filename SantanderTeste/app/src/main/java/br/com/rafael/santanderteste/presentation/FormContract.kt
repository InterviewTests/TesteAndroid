package br.com.rafael.santanderteste.presentation

import br.com.rafael.santanderteste.domain.CellCatalog

/**
 * Classe que representa o contrato para a tela de formulario
 * A classe Presenter do Cell deve implementar a interface Presenter
 */
class FormContract {

    interface View {

        //Retorna os dados de Cell obtidos da API
        fun showCellsList(cellCatalog: CellCatalog)

        //Feedback de espera de requisicao
        fun showLoadingCells()

        //Feedback de erro de requisicao
        fun showErrorToLoadingCells()
    }

    interface Presenter {

        //Chamada para carregar a lista de cells da API
        fun loadCellsList()

        //Configura a View a ser usada no Presenter
        fun setView(view: FormContract.View)
    }

}