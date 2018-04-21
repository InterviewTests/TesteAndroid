package com.vctapps.santanderchallenge.form.data.entity

import com.google.gson.annotations.SerializedName

data class CellResponse(@SerializedName("id")         val id: Int?,
                        @SerializedName("type")       val type: Int?,
                        @SerializedName("message")    val message: String?,
                        @SerializedName("typefield")  val typeField: Any?,
                        @SerializedName("hidden")     val hidden: Boolean?,
                        @SerializedName("topSpacing") val topSpacing: Double?,
                        @SerializedName("show")       val show: Int?,
                        @SerializedName("required")   val required: Boolean?)