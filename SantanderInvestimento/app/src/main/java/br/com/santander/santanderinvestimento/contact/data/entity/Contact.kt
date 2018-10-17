package br.com.santander.santanderinvestimento.contact.data.entity

import br.com.santander.santanderinvestimento.util.JSONConvertable
import com.google.gson.annotations.SerializedName

class Contact(@SerializedName("id") val id: Int?,
              @SerializedName("type") var type: Any?,
              @SerializedName("message") val message: String?,
              @SerializedName("typefield") var typeField: Any?,
              @SerializedName("hidden") val hidden: Boolean?,
              @SerializedName("topSpacing") val topSpacing: Double?,
              @SerializedName("show") val show: Int?,
              @SerializedName("required") val required: Boolean?,
              var userExpected: String,
              var messageError: String,
              var requireValidateCheck: Boolean) : JSONConvertable