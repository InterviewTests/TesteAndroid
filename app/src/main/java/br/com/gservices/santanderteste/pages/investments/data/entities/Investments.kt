package br.com.gservices.santanderteste.pages.investments.data.entities

import com.google.gson.annotations.SerializedName

data class Investments(
    @SerializedName("title") val title: String?,
    @SerializedName("fundName")  val fundName: String?,
    @SerializedName("whatIs")   val whatIs: String?,
    @SerializedName("definition")   val definition: String?,
    @SerializedName("riskTitle")  val riskTitle: String?,
    @SerializedName("risk")  val risk: Int?,
    @SerializedName("infoTitle")  val infoTitle: String?,
    @SerializedName("info")  val info: List<Informations>?,
    @SerializedName("downInfo")  val downInfo: List<Informations>?,
    @SerializedName("moreInfo")  val moreInfo: InvestmentTimeInformations?)