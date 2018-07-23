package br.com.santander.desafio.webservice.fund

data class MoreInfo(
	val month: Month? = null,
	val year: Year? = null,
	val jsonMember12months: JsonMember12months? = null
)
