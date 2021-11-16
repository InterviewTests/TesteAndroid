package br.com.santander.santanderinvestimento.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnack(title: String){
    Snackbar.make(this, title, Snackbar.LENGTH_LONG).show()
}