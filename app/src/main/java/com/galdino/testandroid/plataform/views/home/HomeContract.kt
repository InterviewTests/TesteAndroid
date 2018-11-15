package com.galdino.testandroid.plataform.views.home

import com.galdino.testandroid.mvp.Contract

interface HomeContract
{
    interface View: Contract.View{

    }
    interface Presenter: Contract.Presenter<View>{

    }
}