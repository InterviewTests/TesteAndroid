package com.rafhack.testeandroid.data.entities.investment

data class Investment(
        var title: String = "",
        var fundName: String = "",
        var whatIs: String = "",
        var definition: String = "",
        var riskTitle: String = "",
        var risk: Int = 0,
        var infoTitle: String = "",
        var moreInfo: MoreInfo = MoreInfo(),
        var info: ArrayList<Info> = arrayListOf(),
        var downInfo: ArrayList<Info> = arrayListOf()
)