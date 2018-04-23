package com.vctapps.santanderchallenge.asset.data.entity

import com.google.gson.annotations.SerializedName

data class ScreenResponse(@SerializedName("title")      val title: String?,
                          @SerializedName("fundName")   val assetName: String?,
                          @SerializedName("whatIs")     val whatIs: String?,
                          @SerializedName("definition") val definition: String?,
                          @SerializedName("riskTitle")  val riskTitle: String?,
                          @SerializedName("risk")       val risk: Int?,
                          @SerializedName("infoTitle")  val infoTitle: String?,
                          @SerializedName("moreInfo")   val moreInfo: MoreInfoResponse?,
                          @SerializedName("info")       val info: MutableList<InfoDetailResponse>?,
                          @SerializedName("downInfo")   val downInfo: MutableList<InfoDetailResponse>?)