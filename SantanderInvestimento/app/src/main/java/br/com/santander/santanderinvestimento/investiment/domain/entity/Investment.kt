package br.com.santander.santanderinvestimento.investiment.domain.entity

data class Investment(val title: String,
                      val fundName: String,
                      val whatIs: String,
                      val definition: String,
                      val riskTitle: String,
                      val risk: Int,
                      val infoTitle: String,
                      val info: List<Info>,
                      val downInfo: List<Info>)