package com.galdino.testandroid.data.entity.investment

import com.google.gson.annotations.SerializedName

data class ScreenInvestment(@SerializedName("title")
                       val title: String? = null,
                            @SerializedName("fundName")
                       val fundName: String? = null,
                            @SerializedName("whatIs")
                       val whatIs: String? = null,
                            @SerializedName("definition")
                       val definition: String? = null,
                            @SerializedName("riskTitle")
                       val riskTitle: String? = null,
                            @SerializedName("risk")
                       val risk: Int? = null,
                            @SerializedName("infoTitle")
                       val infoTitle: String? = null,
                            @SerializedName("moreInfo")
                       val moreInfo: MoreInfo? = null,
                            @SerializedName("info")
                       val info: List<Info>? = null,
                            @SerializedName("downInfo")
                       val downInfo: List<DownInfo>? = null)