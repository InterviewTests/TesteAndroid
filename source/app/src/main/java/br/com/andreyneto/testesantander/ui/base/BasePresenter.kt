package br.com.andreyneto.testesantander.ui.base

import android.content.Context
import android.widget.Toast

interface BasePresenter {
    fun start()
    fun toast(ctx: Context, text: String) {
        Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show()
    }
}