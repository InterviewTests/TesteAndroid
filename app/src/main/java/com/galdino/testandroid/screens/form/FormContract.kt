package com.galdino.testandroid.screens.form

import com.galdino.testandroid.mvp.Contract

interface FormContract
{
    interface View: Contract.View{

    }
    interface Presenter: Contract.Presenter<View>{

    }
    interface Interactor: Contract.Interactor{

    }
}