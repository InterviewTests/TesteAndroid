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
    @Json(name = "moreInfo") val taxInfo: MoreInfo,
    @Json(name = "info") val infoList: List<Info>,
    @Json(name = "downInfo") val downInfoList: List<Info>
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

data class CellsResponse(val cells: List<CellRemote>)

data class CellRemote(val id: Int,
                      val type: Int,
                      val message: String,
                      @Json(name = "typefield") val typeField: String?,
                      val hidden: Boolean,
                      val topSpacing: Double,
                      val show: Int?,
                      val required: Boolean)

data class Cell(val id: Int,
                val type: Type,
                val message: String,
                val typeField: TypeField?,
                val hidden: Boolean,
                val topSpacing: Double,
                val show: Int?,
                val required: Boolean)

enum class Type {
  FIELD,
  TEXT,
  IMAGE,
  CHECK_BOX,
  SEND
}

enum class TypeField {
  TEXT,
  TEL_NUMBER,
  EMAIL
}