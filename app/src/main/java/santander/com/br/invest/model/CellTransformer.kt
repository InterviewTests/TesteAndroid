package santander.com.br.invest.model

object CellTransformer {

   fun transformerCells(cellResponseList: List<CellRemote>): ArrayList<Cell> {
    val cellList = ArrayList<Cell>()

    cellResponseList.forEach {
      val type: Type = getType(it.type)
      val typeField: TypeField? = getTypeField(it.typeField)

      val cell = Cell(it.id, type, it.message, typeField, it.hidden, it.topSpacing, it.show, it.required)
      cellList.add(cell)
    }
    return cellList
  }

  private fun getType(type: Int): Type {
    return when (type) {
      1 -> Type.FIELD
      2 -> Type.TEXT
      3 -> Type.IMAGE
      4 -> Type.CHECK_BOX
      else -> Type.SEND
    }
  }

  private fun getTypeField(typeField: String?): TypeField? {
    return when (typeField) {
      "1" -> TypeField.TEXT
      "2", "telnumber" -> TypeField.TEL_NUMBER
      "3" -> TypeField.EMAIL
      else -> {
        return null
      }
    }
  }
}