package com.galdino.testandroid.data.entity.investment

import com.google.gson.annotations.SerializedName

open class InfoModel(
        @SerializedName("modelName")
        open val name: String? = null,

        @SerializedName("modelData")
        open val data: String? = null) {
}