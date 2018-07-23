package br.com.santander.desafio.webservice.fund

data class Screen(
	val riskTitle: String? = null,
	val infoTitle: String? = null,
	val whatIs: String? = null,
	val definition: String? = null,
	val risk: Int? = null,
	val downInfo: List<DownInfoItem?>? = null,
	val title: String? = null,
	val fundName: String? = null,
	val moreInfo: MoreInfo? = null,
	val info: List<InfoItem?>? = null
)
