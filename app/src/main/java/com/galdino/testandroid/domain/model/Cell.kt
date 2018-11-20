package com.galdino.testandroid.domain.model

import android.content.Context
import android.util.Patterns
import android.widget.EditText
import com.galdino.testandroid.R
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

typealias CellType = Int
typealias CellTypeField = Any

data class Cell(@SerializedName("id")
                val id: Int? = null,

                @SerializedName("type")
                val type: Int,

                @SerializedName("message")
                val message: String? = null,

                @SerializedName("typefield")
                val typefield: Any? = null,

                @SerializedName("hidden")
                val hidden: Boolean,

                @SerializedName("topSpacing")
                val topSpacing: Double? = null,

                @SerializedName("show")
                val show: Any? = null,

                @SerializedName("required")
                val required: Boolean,

                var cellAnswer: CellAnswer? = null,

                @Transient
                var field: EditText? = null): BaseModel() {

    object Type
    {
        val FIELD: CellType = 1
        val TEXT: CellType = 2
        val IMAGE: CellType = 3
        val CHECK_BOX: CellType = 4
        val SEND: CellType = 5
    }

    object TypeField{
        val TEXT: CellTypeField = 1
        val TELL_NUMBER: CellTypeField = 2
        val EMAIL: CellTypeField = 3
        val TELL_NUMBER_S: CellTypeField = "telnumber"
    }

    fun isValid(context: Context?): Boolean {

        if(type == Type.FIELD && !hidden && required) {
            if(cellAnswer == null || cellAnswer!!.text.isNullOrEmpty())
            {
                showErrorWithAnimation(context, field, R.string.empty_field)
                return false
            }
            val text:String = cellAnswer!!.text!!
            when(typefield) {
                TypeField.EMAIL->{
                   if(!Patterns.EMAIL_ADDRESS.matcher(text).matches())
                   {
                       showErrorWithAnimation(context, field,R.string.invalid_email)
                   }
                }
                TypeField.TELL_NUMBER,
                TypeField.TELL_NUMBER_S->{
                    if(text.length<13)
                    {
                        showErrorWithAnimation(context, field,R.string.invalid_phone_number)
                    }
                }
            }
        }
        return true
    }


}