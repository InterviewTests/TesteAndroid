package br.com.cesarsicas.stdandroidteste.mvp.form

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import br.com.cesarsicas.stdandroidteste.R
import br.com.cesarsicas.stdandroidteste.constants.Type
import br.com.cesarsicas.stdandroidteste.constants.TypeField
import br.com.cesarsicas.stdandroidteste.domains.Cell
import kotlinx.android.synthetic.main.activity_main.*

class FormActivity : AppCompatActivity(), FormView {
    var presenter: FormPresenter? = null

    override fun showError(message: String?) {
        Toast.makeText(this, message ?: "", Toast.LENGTH_LONG ).show()
    }

    override fun addCells(cells: List<Cell>) {
        Log.wtf("SIZE", cells.size.toString())

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        presenter = FormPresenter()
        presenter?.attachView(this)
        presenter?.getCells()


//        val cell1 = Cell(1, Type.text,
//                "Message test",
//                10.0,
//                TypeField.email,
//                false,
//                false)
//
//        val cell2 = Cell(1, Type.field,
//                "Message test",
//                10.0,
//                TypeField.email,
//                false,
//                false)
//
//        val cell3 = Cell(1, Type.send,
//                "Message test",
//                10.0,
//                TypeField.email,
//                false,
//                false)
//
//        val cell4 = Cell(1, Type.checkbox,
//                "Message test",
//                10.0,
//                TypeField.email,
//                false,
//                false)


//        generateDynamicElements(cell2)
//        generateDynamicElements(cell1)
//        generateDynamicElements(cell3)
//        generateDynamicElements(cell4)


    }

    private fun generateDynamicElements(cell: Cell?) {
        val dynamicView: View? = when (cell?.type) {
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
                generateCheckBox(cell)
            }
            Type.send -> {
                generateButton(cell)
            }
            else -> {
                null
            }


        }

        if (dynamicView != null) {
            configureVisibility(dynamicView, cell)
            configureMargin(dynamicView, cell)
            containerLayout.addView(dynamicView)
        }


    }


    private fun generateCheckBox(cell: Cell?): View? {
        val cb = CheckBox(this)
        val lLayout = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        cb.layoutParams = lLayout
        cb.text = cell?.message

        return cb
    }

    private fun generateButton(cell: Cell?): Button {
        val button = Button(this)


        val lLayout = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        button.layoutParams = lLayout

        button.text = cell?.message

        return button
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
        textInputLayout.layoutParams = lLayout

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

    private fun configureVisibility(view: View, cell: Cell?) {
        if (cell?.hidden == true) {
            view.visibility = View.GONE
        }
    }

    private fun configureMargin(view: View, cell: Cell?) {
        val params = view.layoutParams as ViewGroup.MarginLayoutParams
        params.topMargin = cell?.topSpacing?.toInt() ?: 10

    }
}