package santander.com.br.invest.model

import com.squareup.moshi.Json


data class FundResponse(val screen: Screen)

data class Screen(
    val title: String,
    val fundName: String,
    val whatIs: String,
    val definition: String,
    val riskTitle: String,
    val risk: Int,
    val infoTitle: String,
    @Json(name = "moreInfo")  val taxInfo: MoreInfo,
    @Json(name = "info") val infoList: List<Info>,
    @Json(name = "downInfo")   val downInfoList: List<Info>
)

data class Info(
    val name: String,
    val data: String?
)

data class MoreInfo(
    val month: Month,
    val year: Year,
    @Json(name = "12months") val months12: Months12
)

data class Year(
    val fund: Double,
    @Json(name = "CDI") val cdi: Double
)

data class Month(
    val fund: Double,
    @Json(name = "CDI")
    val cdi: Double
)

data class Months12(
    val fund: Double,
    @Json(name = "CDI")
    val cdi: Double
)