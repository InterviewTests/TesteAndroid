package br.com.andreyneto.testesantander.model

data class InvestResponse(
        var screen: Screen)

data class Screen(
        var title: String?,
        var fundName: String?,
        var whatIs: String?,
        var definition: String?,
        var riskTitle: String?,
        var risk: Int = 0,
        var infoTitle: String?,
        var moreInfo: MoreInfo?,
        var info: List<Info>?)

data class MoreInfo(
        var month: MoreInfoDetail?,
        var year: MoreInfoDetail?,
        var twelveMonths: MoreInfoDetail?)

data class MoreInfoDetail(
        var fund: Double?,
        var cdi: Double?)

data class Info(
        var name: String?,
        var fund: String?,
        var data: String?)
