package santander.com.br.invest.contact

import junit.framework.TestCase.assertEquals
import org.junit.Test
import santander.com.br.invest.model.CellRemote
import santander.com.br.invest.model.CellTransformer
import santander.com.br.invest.model.Type
import santander.com.br.invest.model.TypeField

class ContactModelTest {

  @Test
  fun `is enum transforming correctly`() {
    val cellRemoteList: ArrayList<CellRemote> = ArrayList()
    cellRemoteList.add(CellRemote(1, 1, "Test 1", "1", false, 10.0, null, true))
    cellRemoteList.add(CellRemote(2, 2, "Test 2", "2", false, 10.0, null, true))
    cellRemoteList.add(CellRemote(3, 3, "Test 3", "telnumber", false, 10.0, null, true))
    cellRemoteList.add(CellRemote(4, 4, "Test 4", "3", false, 10.0, null, true))
    cellRemoteList.add(CellRemote(5, 5, "Test 5", "null", false, 10.0, null, true))

    val cellList = CellTransformer.transformerCells(cellRemoteList)

    assertEquals(cellList[0].type, Type.FIELD)
    assertEquals(cellList[1].type, Type.TEXT)
    assertEquals(cellList[2].type, Type.IMAGE)
    assertEquals(cellList[3].type, Type.CHECK_BOX)
    assertEquals(cellList[4].type, Type.SEND)

    assertEquals(cellList[0].typeField, TypeField.TEXT)
    assertEquals(cellList[1].typeField, TypeField.TEL_NUMBER)
    assertEquals(cellList[2].typeField, TypeField.TEL_NUMBER)
    assertEquals(cellList[3].typeField, TypeField.EMAIL)
    assertEquals(cellList[4].typeField, null)

  }
}