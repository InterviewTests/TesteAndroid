package br.com.andreyneto.testesantander.ui.components

import android.content.Context
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.ContextThemeWrapper
import br.com.andreyneto.testesantander.R

class CustomButton(context: Context) : android.support.v7.widget.AppCompatButton(ContextThemeWrapper(context, R.style.Title)) {

    init {
        background = ContextCompat.getDrawable(context, R.drawable.button_background)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) stateListAnimator = null
        isAllCaps = false
        setTextColor(ContextCompat.getColor(context, android.R.color.white))
        textSize = 16f
    }
}
