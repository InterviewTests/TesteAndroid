package lucasonofre.santandertest.custom

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import lucasonofre.santandertest.R

class InfoDownButton constructor(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    var imageParameter: ImageView
    var textParameter : TextView

    init {

        //Configura o Layoyt
        gravity     = Gravity.CENTER
        orientation = LinearLayout.HORIZONTAL

        //Cria as Views
        imageParameter = ImageView(context)
        textParameter  = TextView(context)

        imageParameter.layoutParams = LinearLayout.LayoutParams(25, 25)
        textParameter .layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        textParameter .setPadding(5,5,0,0)

        //Add's the view's in the layout
        addView(imageParameter)
        addView(textParameter)

        //Recebe os parâmetros dos XML
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.InfoDownButton)
        textParameter .setTextColor(Color.WHITE)
        //imageParameter.scaleType = ImageView.ScaleType.FIT_XY
        imageParameter.setImageDrawable(attributes.getDrawable(R.styleable.InfoDownButton_image))
        textParameter.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary))
        textParameter.text = attributes.getString(R.styleable.InfoDownButton_text)!!


        attributes.recycle()
    }
}