package br.com.santander.santanderinvestimento.investiment.domain.entity

import com.google.gson.annotations.SerializedName

data class Investment(@SerializedName("title") val title: String?,
                      @SerializedName("fundName")  val fundName: String?,
                      @SerializedName("whatIs")   val whatIs: String?,
                      @SerializedName("definition")   val definition: String?,
                      @SerializedName("riskTitle")  val riskTitle: String?,
                      @SerializedName("risk")  val risk: Int?,
                      @SerializedName("infoTitle")  val infoTitle: String?,
                      @SerializedName("info")  val info: List<Info>?,
                      @SerializedName("downInfo")  val downInfo: List<Info>?,
                      @SerializedName("moreInfo")  val moreInfo: TimeInfo?)