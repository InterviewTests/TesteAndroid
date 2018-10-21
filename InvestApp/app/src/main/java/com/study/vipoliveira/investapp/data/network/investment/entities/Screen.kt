package com.study.vipoliveira.investapp.data.network.investment.entities

data class Screen (
        val title: String,
        val fundName: String,
        val whatIs: String,
        val definition: String,
        val riskTitle: String,
        val risk: Int,
        val infoTitle: String,
        val moreInfo: MoreInfo,
        val info: List<Info>,
        val downInfo: List<Info>
)