package com.galdino.testandroid.data.entity.investment

import com.google.gson.annotations.SerializedName

data class Info(@SerializedName("name")
                override val name: String? = null,

                @SerializedName("data")
                override val data: String? = null): InfoModel(name, data) {
}