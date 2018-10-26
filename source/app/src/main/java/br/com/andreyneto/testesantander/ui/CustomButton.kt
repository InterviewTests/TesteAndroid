package br.com.andreyneto.testesantander.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.ContextThemeWrapper
import br.com.andreyneto.testesantander.R

class CustomButton(context: Context) : android.support.v7.widget.AppCompatButton(ContextThemeWrapper(context, R.style.Title)) {

    init {
        background = ContextCompat.getDrawable(context, R.drawable.button_background)
        stateListAnimator = null
        isAllCaps = false
        setTextColor(ContextCompat.getColor(context, android.R.color.white))
        textSize = 16f
    }
}
