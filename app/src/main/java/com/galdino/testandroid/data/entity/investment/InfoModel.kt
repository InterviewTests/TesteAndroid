package com.galdino.testandroid.data.entity.investment

import com.google.gson.annotations.SerializedName

open class InfoModel(
        @SerializedName("name")
        open val name: String? = null,

        @SerializedName("data")
        open val data: String? = null) {
}