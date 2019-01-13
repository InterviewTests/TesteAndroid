package br.com.rafael.santanderteste.domain

import com.google.gson.annotations.SerializedName

// Entidade que representa um fundo de investimento
data class Fund (
    var title: String?,
    var fundName: String?,
    var whatIs: String?,
    var definition: String?,
    var riskTitle: String?,
    var risk: Int?,
    var infoTitle: String?,
    var moreInfo: MoreInfo?,
    var info: List<GeneralInfo>,
    var downInfo: List<GeneralInfo>
)

// Informacoes de investimentos para determinados periodos
data class MoreInfo (
    var month: FundInfo?,
    var year: FundInfo?,
    @SerializedName("12months")
    var _12months: FundInfo?
)

// Informacoes de investimento
data class FundInfo (
    var fund: Double?,
    var CDI: Double?
)

// Informacoes genericas
data class GeneralInfo (
    var name: String?,
    var data: String?
)