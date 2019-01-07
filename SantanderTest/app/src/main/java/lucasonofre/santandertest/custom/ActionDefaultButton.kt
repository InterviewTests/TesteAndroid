package lucasonofre.santandertest.custom

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import lucasonofre.santandertest.R

//class ActionDefaultButton constructor(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
//
//    var button: Button
//
//    init {
//
//        //Set's the layout parameters
//        gravity     = Gravity.CENTER
//        orientation = LinearLayout.HORIZONTAL
//
//        //Instantiate and Set's the view's parameters
//        button = Button(context)
//
//        button.layoutParams = LinearLayout.LayoutParams(25, 25)
//        button .layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//
//        //Add's the view's in the layout
//        addView(button)
//
//        //Get the parameters on the XML
//        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ActionDefaultButton)
//        button.setTextColor(Color.WHITE)
//        button.text = attributes.getString(R.styleable.InfoDownButton_text)!!
//        button.setBackgroundResource(attributes.getResourceId(R.styleable.ActionDefaultButton_background,0))
//        button.setOnClickListener { button.setBackgroundColor(ContextCompat.getColor(context,R.color.green)) }
//
//        attributes.recycle()
//    }
//}