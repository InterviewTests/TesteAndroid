package br.com.rafael.santanderteste.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.rafael.santanderteste.R
import br.com.rafael.santanderteste.domain.CellCatalog

class MainActivity : AppCompatActivity(), FormContract.View {

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            var presenter = FormPresenter()
            presenter.setView(this)
            presenter.loadCellsList()
    }

    override fun showCellsList(cellCatalog: CellCatalog) {

    }

    override fun showLoadingCells() {

    }

    override fun showErrorToLoadingCells() {

    }
}
