package lucasonofre.santandertest.model

data class Screen (
    val title: String,
    val fundName: String,
    val whatIs: String,
    val definition: String,
    val riskTitle: String,
    val risk: Long,
    val infoTitle: String,
    val moreInfo: MoreInfo,
    val info: List<Info>,
    val downInfo: List<Info>
)

data class MoreInfo (
    val month:       Yield,
    val year:        Yield,
    val the12Months: Yield
)

class Yield (
    val fund: Float,
    val cdi:  Float
    )

data class Info (
    val name: String? = null,
    val data: String? = null
)