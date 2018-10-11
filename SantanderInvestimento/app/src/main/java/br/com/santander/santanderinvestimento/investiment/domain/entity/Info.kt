package br.com.santander.santanderinvestimento.investiment.domain.entity

import com.google.gson.annotations.SerializedName

data class Info(@SerializedName("name") val name: String,
                @SerializedName("data") val data: String)