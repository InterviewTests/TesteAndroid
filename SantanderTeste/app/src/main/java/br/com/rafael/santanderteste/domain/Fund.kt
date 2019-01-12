package br.com.rafael.santanderteste.domain

import com.google.gson.annotations.SerializedName

data class Fund (
    var title: String?,
    var fundName: String?,
    var whatIs: String?,
    var definition: String?,
    var riskTitle: String?,
    var risk: Int?,
    var infoTitle: String?,
    var moreInfo: MoreInfo?,
    var info: List<GeneralInfo>
)

data class MoreInfo (
    var month: FundInfo?,
    var year: FundInfo?,
    @SerializedName("12months")
    var _12months: FundInfo?
)

data class FundInfo (
    var fund: Double?,
    var CDI: Double?
)

data class GeneralInfo (
    var name: String?,
    var data: String?
)