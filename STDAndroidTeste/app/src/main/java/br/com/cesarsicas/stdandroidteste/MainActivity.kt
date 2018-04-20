package br.com.cesarsicas.stdandroidteste

import android.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import br.com.cesarsicas.stdandroidteste.constants.Type
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generateTextView()
    }

    fun generateDynamicElements(type: Int, typeField: Int) {

        when (type) {
            Type.field.value -> {


            }
            Type.text.value -> {

            }
        }

    }

    fun generateTextView(typeField: Int) {

        val myEditText = EditText(this) // Pass it an Activity or Context
        myEditText.layoutParams = ActionBar.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)

        myEditText.hint = "this was generated"


        containerLayout.addView(myEditText)
    }


}
