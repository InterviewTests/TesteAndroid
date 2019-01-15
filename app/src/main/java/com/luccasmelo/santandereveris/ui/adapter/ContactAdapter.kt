package com.luccasmelo.santandereveris.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.luccasmelo.santandereveris.BR
import com.luccasmelo.santandereveris.R
import com.luccasmelo.santandereveris.data.model.ContactForm
import com.luccasmelo.santandereveris.data.model.Type
import com.luccasmelo.santandereveris.data.model.Type.*
import com.luccasmelo.santandereveris.data.model.TypeField
import com.luccasmelo.santandereveris.databinding.ButtonItemBinding
import com.luccasmelo.santandereveris.databinding.CheckBoxItemBinding
import com.luccasmelo.santandereveris.databinding.FieldItemBinding
import com.luccasmelo.santandereveris.databinding.TextItemBinding
import com.luccasmelo.santandereveris.util.isValidEmail
import com.luccasmelo.santandereveris.util.isValidPhone
import org.w3c.dom.Text
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService



class ContactAdapter(val context: AppCompatActivity, val list: ArrayList<ContactForm.Cells>) :
    RecyclerView.Adapter<BindableViewHolder>() {
    lateinit var sendLister: () -> (Unit)
    val validateFields:HashMap<Int, Boolean?> = HashMap<Int, Boolean?>()


    init {
        list.forEachIndexed { index, item ->
            if (item.required!! && !item.hidden!! &&  item.type != Type.send) {
                validateFields[index] = false
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = list[position]
        return item.type!!.value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder {
        val type = viewType.toType()
        return when (type) {
            text -> TextViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.text_item,
                    parent,
                    false
                )
            )
            field -> FieldViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.field_item,
                    parent,
                    false
                )
            )
            image -> ImageViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.text_item,
                    parent,
                    false
                )
            )
            checkbox -> CheckBoxViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.check_box_item,
                    parent,
                    false
                )
            )
            send -> ButtonViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.button_item,
                    parent,
                    false
                )
            )

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: BindableViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)

        when (holder) {
            is FieldViewHolder -> {

                holder.binding.field.setOnFocusChangeListener { field: View, hasFocus: Boolean ->
                    field as TextView
                    fieldValidate(hasFocus, item, field, position)
                }
            }

            is CheckBoxViewHolder -> {
                holder.binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                    checkBoxChecked(item, isChecked, position)
                }
            }

            is ButtonViewHolder -> {
                holder.binding.button.setOnClickListener {
                    buttonClickValidate(it)
                }
            }
        }
    }

    private fun fieldValidate(
        hasFocus: Boolean,
        item: ContactForm.Cells,
        field: TextView,
        position: Int
    ) {
        if (!hasFocus && (item.required!! || !field.text.toString().isBlank())) {
            validateFields[position] = when (item.typefield) {
                TypeField.email -> {

                    val validEmail = field.text.toString().isValidEmail()
                    if (!validEmail) {
                        field.error = "E-mail inválido"
                    }
                    validEmail
                }
                TypeField.telNumber -> {
                    val validPhone = field.text.toString().isValidPhone()
                    if (!validPhone) {
                        field.error = "Telefone inválido"
                    }
                    validPhone

                }
                else -> true
            }
        }
    }

    private fun checkBoxChecked(
        item: ContactForm.Cells,
        isChecked: Boolean,
        position: Int
    ) {
        if (item.show != null) {
            list.forEachIndexed { index, cell ->
                if (cell.id == item.show) {
                    list[index].hidden = !isChecked
                    notifyItemChanged(index)
                    if (!isChecked) {
                        validateFields[position] = true
                    } else
                        validateFields[position] = null

                }
            }
        }
    }

    private fun buttonClickValidate(it: View?) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it!!.windowToken, 0)

        for (mapItem in validateFields) {
            if (mapItem.value != null && !mapItem.value!!) {
                Toast.makeText(context, "Preencha os campos corretamente", Toast.LENGTH_LONG).show()
                return
            }
        }

        sendLister.invoke()
    }
}

fun Int.toType(): Type {
    return when (this) {
        1 -> field
        2 -> text
        3 -> image
        4 -> checkbox
        5 -> send
        else -> text
    }
}


open class BindableViewHolder(open val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cells: ContactForm.Cells) {
        binding.setVariable(BR.cell, cells)
        binding.executePendingBindings()
    }
}

class ButtonViewHolder(override val binding: ButtonItemBinding) : BindableViewHolder(binding)

class ImageViewHolder(override val binding: ButtonItemBinding) : BindableViewHolder(binding)

class CheckBoxViewHolder(override val binding: CheckBoxItemBinding) : BindableViewHolder(binding)

class TextViewHolder(override val binding: TextItemBinding) : BindableViewHolder(binding)

class FieldViewHolder(override val binding: FieldItemBinding) : BindableViewHolder(binding)