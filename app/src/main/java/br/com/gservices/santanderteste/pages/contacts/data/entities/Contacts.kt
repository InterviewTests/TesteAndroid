package br.com.gservices.santanderteste.pages.contacts.data.entities

import br.com.gservices.santanderteste.utils.ConvertableJSON
import com.google.gson.annotations.SerializedName

class Contacts(
    @SerializedName("id") val id: Int?,
    @SerializedName("type") var type: Any?,
    @SerializedName("message") val message: String?,
    @SerializedName("typefield") var typeField: Any?,
    @SerializedName("hidden") val hidden: Boolean?,
    @SerializedName("topSpacing") val topSpacing: Double?,
    @SerializedName("show") val show: Int?,
    @SerializedName("required") val required: Boolean?,
    var expected: String,
    var errMsg: String,
    var requiredCheck: Boolean) : ConvertableJSON