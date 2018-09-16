package com.santander.luizlago.testeandroid.api.models

data class Fund(
        val title: String,
        val funName: String,
        val whatIs: String,
        val definition: String,
        val riskTitle: String,
        val risk: Int,
        val infoTitle: String,
        val moreInfo: MoreInfo,
        val info: List<InfoValue>,
        val downInfo: List<InfoValue>
)