package com.galdino.testandroid.plataform.views.contact

import com.galdino.testandroid.mvp.Contract

interface ContactContract {
    interface View: Contract.View{

    }

    interface Presenter: Contract.Presenter<View>{

    }
}