package com.galdino.testandroid.plataform.views.home

import com.galdino.testandroid.mvp.BasePresenter

class HomePresenter(): BasePresenter<HomeContract.View>(), HomeContract.Presenter {
    override fun onInvestmentClicked() {
        mView?.inflateInvestment()
    }

    override fun onContactClicked() {
        mView?.inflateContact()
    }
}