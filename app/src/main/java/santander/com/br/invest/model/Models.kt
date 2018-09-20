package santander.com.br.invest.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class FundResponse(val screen: Screen)

@Parcelize
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
) : Parcelable

@Parcelize
data class Info(
    val name: String,
    val data: String?
) : Parcelable

@Parcelize
data class MoreInfo(
    val month: Month,
    val year: Year,
    @Json(name = "12months") val months12: Months12
) : Parcelable

@Parcelize
data class Year(
    val fund: Double,
    @Json(name = "CDI") val cdi: Double
) : Parcelable

@Parcelize
data class Month(
    val fund: Double,
    @Json(name = "CDI")
    val cdi: Double
) : Parcelable

@Parcelize
data class Months12(
    val fund: Double,
    @Json(name = "CDI")
    val cdi: Double
) : Parcelable

data class CellsResponse(val cells: List<CellRemote>)

data class CellRemote(val id: Int,
                      val type: Int,
                      val message: String,
                      @Json(name = "typefield") val typeField: String?,
                      val hidden: Boolean,
                      val topSpacing: Double,
                      val show: Int?,
                      val required: Boolean)

@Parcelize
data class Cell(val id: Int,
                val type: Type,
                val message: String,
                val typeField: TypeField?,
                val hidden: Boolean,
                val topSpacing: Double,
                val show: Int?,
                val required: Boolean) : Parcelable

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