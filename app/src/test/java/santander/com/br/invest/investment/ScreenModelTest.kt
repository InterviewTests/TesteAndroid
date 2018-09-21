package santander.com.br.invest.investment

import junit.framework.TestCase.assertEquals
import org.junit.Test
import santander.com.br.invest.model.*

class ScreenModelTest {

  private val title: String = "Title"
  private val fundName: String = "fundName"
  private val whatIs: String = "whatIs"
  private val definition: String = "definition"
  private val riskTitle: String = "riskTitle"
  private val risk: Int = 3
  private val infoTitle = "infoTitle"
  private val taxInfo: MoreInfo = MoreInfo(
      Month(2.4, 7.8),
      Year(1.4, 6.9),
      Months12(11.5, 8.5))
  private val infoList: ArrayList<Info> = ArrayList()
  private val downInfoList: ArrayList<Info> = ArrayList()


  @Test
  fun `assert concat list size`() {
    infoList.add(Info("name", "data"))
    downInfoList.add(Info("name", "data"))

    val screen = Screen(title, fundName, whatIs, definition, riskTitle, risk, infoTitle, taxInfo, infoList, downInfoList)

    assertEquals(screen.concatLists().size, 2)
  }

}