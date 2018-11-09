package br.com.gservices.santanderteste.pages.investments.data.entities

import com.google.gson.annotations.SerializedName

data class Informations(
    @SerializedName("name") val name: String?,
    @SerializedName("data") val data: String?)