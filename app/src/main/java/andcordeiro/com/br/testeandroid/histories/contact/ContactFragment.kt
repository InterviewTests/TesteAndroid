package andcordeiro.com.br.testeandroid.histories.contact

import andcordeiro.com.br.testeandroid.R
import andcordeiro.com.br.testeandroid.entities.Cell
import andcordeiro.com.br.testeandroid.entities.ResultContact
import andcordeiro.com.br.testeandroid.entities.Validate
import andcordeiro.com.br.testeandroid.system.PhoneFormat
import andcordeiro.com.br.testeandroid.system.dagger.App
import andcordeiro.com.br.testeandroid.system.extensions.*
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.fragment_contact.*
import java.lang.ref.WeakReference
import javax.inject.Inject


class ContactFragment : Fragment(), ContactMVP.View {

    companion object {
        fun newInstance(): ContactFragment = ContactFragment()
    }

    @Inject
    internal lateinit var presenter: ContactMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity!!.application as App).getComponent().inject(this)
        presenter.loadCells()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_contact, container, false)

    override fun onStart() {
        super.onStart()
        presenter.setView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.rxUnsubscribe()
    }

    override fun context(): Context = activity!!.baseContext

    override fun shortShowMessage(msg: String?) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun createComponents(result: ResultContact) {
        val editIdList = arrayListOf<Validate>()
        for (cell: Cell in result.cells!!) {
            if (!cell.hidden!!) {
                when (cell.type) {
                    1 -> {
                        val editText = layoutInflater.inflate(R.layout.edit_text_custom,
                                linearContact, false) as EditText
                        editText.tag = cell.id!!
                        editText.hint = cell.message
                        when (cell.typeField) {
                            "1" -> {
                                editText.validate({ text ->
                                    text.isValidText()
                                }, ContextCompat.getColor(context!!, R.color.rosso_corsa)
                                        , ContextCompat.getColor(context!!, R.color.kelly_green))
                            }
                            "telnumber", "2" -> {
                                editText.addTextChangedListener(PhoneFormat(WeakReference(editText)))
                                editText.validate({ text ->
                                    text.isValidNumber()
                                }, ContextCompat.getColor(context!!, R.color.rosso_corsa)
                                        , ContextCompat.getColor(context!!, R.color.kelly_green))
                            }
                            "3" -> {
                                editText.validate({ text ->
                                    text.isValidEmail()
                                }, ContextCompat.getColor(context!!, R.color.rosso_corsa)
                                        , ContextCompat.getColor(context!!, R.color.kelly_green))
                            }
                        }
                        editIdList.add(Validate(cell.id!!, cell.typeField))
                        linearContact.addView(editText)
                    }
                    2 -> {
                        val textView = layoutInflater.inflate(R.layout.text_view_custom,
                                linearContact, false) as TextView
                        textView.tag = cell.id!!
                        textView.text = cell.message
                        linearContact.addView(textView)
                    }
                    4 -> {
                        val checkBox = layoutInflater.inflate(R.layout.check_box_custom,
                                linearContact, false) as CheckBox
                        checkBox.tag = cell.id!!
                        checkBox.text = cell.message
                        linearContact.addView(checkBox)
                    }
                    5 -> {
                        val button = layoutInflater.inflate(R.layout.button_custom,
                                linearContact, false) as Button
                        button.tag = cell.id!!
                        button.text = cell.message
                        button.setOnClickListener {
                            if (editIdList.isNotEmpty()) {
                                var count = 0
                                editIdList.asIterable().forEach({
                                    val editText =
                                            linearContact.findViewWithTag<EditText>(it.id)
                                    when (it.typeField) {
                                        "1" -> {
                                            if (!editText.text.toString().isValidText()) {
                                                count++
                                            }
                                        }
                                        "telnumber", "2" -> {
                                            if (!editText.text.toString().isValidNumber()) {
                                                count++
                                            }
                                        }
                                        "3" -> {
                                            if (!editText.text.toString().isValidEmail()) {
                                                count++
                                            }
                                        }
                                    }
                                })
                                if (count < 1) {
                                    val fragmentManager = fragmentManager
                                    val fragmentTransaction =
                                            fragmentManager!!.beginTransaction()
                                    fragmentTransaction.replace(this.id,
                                            MessageSucessFragment.newInstance())
                                    fragmentTransaction.commit()
                                }
                            }
                        }
                        linearContact.addView(button)
                    }
                }

            }
        }
        linearContact.show()
    }
}
