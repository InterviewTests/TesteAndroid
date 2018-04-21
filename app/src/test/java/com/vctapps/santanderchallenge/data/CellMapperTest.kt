package com.vctapps.santanderchallenge.data

import com.vctapps.santanderchallenge.form.data.CellMapper
import com.vctapps.santanderchallenge.form.data.entity.CellJsonResponse
import com.vctapps.santanderchallenge.util.JsonLoader
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CellMapperTest {

    lateinit var cellJsonResponse: CellJsonResponse

    val LIST_CELL_SIZE = 6

    val FIRST_ID = 1

    val FIRST_TYPE = 2

    val FIRST_MESSAGE = "Olá, primeiro se apresente com o seu nome:"

    val FIRST_TYPEFILD = -1

    val FIRST_HIDDEN = false

    val FIRST_TOP_SPACING = 60.0

    val FIRST_SHOW = -1

    val FIRST_REQUIRED = false

    @Before
    fun setUp(){
        cellJsonResponse = JsonLoader.get("json/cells.json", CellJsonResponse::class.java)
    }

    @Test
    fun `test map to cells response correctly`(){
        val cells = CellMapper.map(cellJsonResponse)

        val firstCell = cells[0]

        assertEquals(LIST_CELL_SIZE, cells.size)

        assertEquals(FIRST_ID, firstCell.id)
        assertEquals(FIRST_TYPE, firstCell.type)
        assertEquals(FIRST_MESSAGE, firstCell.message)
        assertEquals(FIRST_TYPEFILD, firstCell.typeField)
        assertEquals(FIRST_HIDDEN, firstCell.hidden)
        assertEquals(FIRST_TOP_SPACING, firstCell.topSpacing, 0.0)
        assertEquals(FIRST_SHOW, firstCell.show)
        assertEquals(FIRST_REQUIRED, firstCell.required)
    }

}