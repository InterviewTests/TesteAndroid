package br.com.cesarsicas.stdandroidteste

import android.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import br.com.cesarsicas.stdandroidteste.constants.Type
import br.com.cesarsicas.stdandroidteste.constants.TypeField
import br.com.cesarsicas.stdandroidteste.domains.Cell
import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewGroup.MarginLayoutParams
import android.widget.FrameLayout
import android.text.InputType
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cell1 = Cell(1, Type.text,
                "Message test",
                10.0,
                TypeField.email,
                false,
                false)

        val cell2 = Cell(1, Type.field,
                "Message test",
                10.0,
                TypeField.email,
                false,
                false)

        generateDynamicElements(cell1)
        generateDynamicElements(cell2)

    }

    fun generateDynamicElements(cell: Cell?) {
        val dynamicView:View?

        dynamicView = when (cell?.type) {
            Type.field -> {
                generateEditText(cell)
            }
            Type.text -> {
                generateTextView(cell)

            }
            Type.image -> {
                //todo not used yet
                null
            }
            Type.checkbox -> {
                null
            }
            Type.send -> {
                null
            }
            else ->{
                null
            }


        }

        if(dynamicView != null){
            configureVisibility(dynamicView, cell)
            containerLayout.addView(dynamicView)
        }



    }


    private fun generateTextView(cell: Cell?): TextView {
        val textView = TextView(this)


        val lLayout = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        textView.layoutParams = lLayout

        textView.text = cell?.message

        return textView
    }

    private fun generateEditText(cell: Cell?): TextInputLayout {

        val fLayout = containerLayout.layoutParams as FrameLayout.LayoutParams

        val textInputLayout = TextInputLayout(this)
        textInputLayout.layoutParams = fLayout
        textInputLayout.hint = cell?.message

        val lLayout = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        val editText = TextInputEditText(this)
        editText.layoutParams = lLayout

        textInputLayout.addView(editText)

        makeMask(editText, cell?.typefield)

        return textInputLayout

        //todo Required ???


    }

    private fun makeMask(editText: EditText, typeField: TypeField?) {
        //todo validate this types

        when (typeField) {
            TypeField.text -> {
                editText.inputType =
                        InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
            }
            TypeField.email -> {
                editText.inputType =
                        InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }
            TypeField.telNumber -> {
                editText.inputType = InputType.TYPE_CLASS_NUMBER
            }
        }
    }

    private fun configureVisibility(view:View, cell:Cell?){
        if (cell?.hidden == true) {
            view.visibility = View.GONE
        }
    }

    private fun configureMargin(view:View, cell:Cell?){
        val params = view.layoutParams as MarginLayoutParams
        params.topMargin = cell?.topSpacing?.toInt() ?: 10

    }

}
