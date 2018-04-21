package com.vctapps.santanderchallenge.domain.cells

import com.vctapps.santanderchallenge.form.domain.cell.TextFieldCell
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TextFieldCellTest {

    lateinit var cellType: TextFieldCell

    val VALID_TEXT = "."

    val INVALID_TEXT = ""

    @Before
    fun setUp(){
        cellType = TextFieldCell()
    }

    @Test
    fun `test if validade text correctly`(){
        val isValid = cellType.validateResponse(VALID_TEXT)

        Assert.assertTrue(isValid)
    }

    @Test
    fun `test if invalidade text correctly`(){
        val isValid = cellType.validateResponse(INVALID_TEXT)

        Assert.assertFalse(isValid)
    }

}