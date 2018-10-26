package br.com.andreyneto.testesantander.model

import com.google.gson.annotations.SerializedName

//----------------------Invest
data class InvestResponse(
        var screen: Screen)

data class Screen(
        var title: String,
        var fundName: String,
        var whatIs: String,
        var definition: String,
        var riskTitle: String,
        var risk: Int = 0,
        var infoTitle: String,
        var moreInfo: MoreInfo,
        var info: List<Info>,
        var downInfo: List<Info>)

data class MoreInfo(
        var month: MoreInfoDetail,
        var year: MoreInfoDetail,
        @SerializedName("12months") var twelveMonths: MoreInfoDetail)

data class MoreInfoDetail(
        var fund: Double,
        var CDI: Double)

data class Info(
        var name: Any,
        var fund: Any,
        var data: Any?)
//----------------------Contact

data class ContactResponse(
        var cells: List<Cell>)

data class Cell(
        var id: Int,
        var type: Int,
        var message: String,
        var typefield: String,
        var hidden: Boolean,
        var topSpacing: Int,
        var show: Int,
        var required: Boolean)