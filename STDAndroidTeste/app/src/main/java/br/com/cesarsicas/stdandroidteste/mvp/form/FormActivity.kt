package br.com.cesarsicas.stdandroidteste.mvp.form

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.*
import br.com.cesarsicas.stdandroidteste.R
import br.com.cesarsicas.stdandroidteste.constants.CellType
import br.com.cesarsicas.stdandroidteste.constants.CellTypeField
import br.com.cesarsicas.stdandroidteste.domains.Cell
import br.com.cesarsicas.stdandroidteste.mvp.home.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class FormActivity : AppCompatActivity(), FormView {
    var presenter: FormPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        presenter = FormPresenter()
        presenter?.attachView(this)
        presenter?.getCells()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
        presenter = null

    }


    override fun generateCheckBox(cell: Cell?): View? {
        val cb = CheckBox(this)
        val lLayout = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        cb.layoutParams = lLayout
        cb.text = cell?.message

        return cb
    }

    override fun generateButton(cell: Cell?): Button {
        val button = Button(this)

        val lLayout = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        button.layoutParams = lLayout

        button.text = cell?.message

        button.setTextColor(Color.WHITE)

        button.background = (ContextCompat.getDrawable(this, R.drawable.button_background));

        button.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        return button
    }

    override fun generateTextView(cell: Cell?): TextView {
        val textView = TextView(this)


        val lLayout = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        textView.layoutParams = lLayout

        textView.text = cell?.message

        return textView
    }

    override fun generateEditText(cell: Cell?): TextInputLayout {

        val fLayout = containerLayout.layoutParams as FrameLayout.LayoutParams

        val textInputLayout = TextInputLayout(this)
        textInputLayout.layoutParams = fLayout
        textInputLayout.hint = cell?.message

        val lLayout = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)

        val editText = TextInputEditText(this)
        editText.layoutParams = lLayout
        textInputLayout.layoutParams = lLayout

        textInputLayout.addView(editText)

        makeMask(editText, cell?.typefield)

        return textInputLayout

        //todo Required ???
    }

    override fun makeMask(editText: EditText, typeField: CellTypeField?) {
        //todo validate this types
        when (typeField) {
            CellTypeField.text -> {
                editText.inputType =
                        InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
            }
            CellTypeField.email -> {
                editText.inputType =
                        InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }
            CellTypeField.telNumber -> {
                editText.inputType = InputType.TYPE_CLASS_NUMBER
            }
        }
    }

    override fun configureVisibility(view: View, cell: Cell?) {
        if (cell?.hidden == true) {
            view.visibility = View.GONE
        }
    }

    override fun configureMargin(view: View, cell: Cell?) {
        val params = view.layoutParams as ViewGroup.MarginLayoutParams
        params.topMargin = cell?.topSpacing?.toInt() ?: 10
    }

    override fun addView(view: View) {
        containerLayout.addView(view)
    }

    override fun addCells(cells: List<Cell>) {
        cells.map {
            presenter?.generateDynamicElements(it)
        }
    }

    override fun showError(message: String?) {
        Toast.makeText(this, message ?: "",
                Toast.LENGTH_LONG).show()
    }
}