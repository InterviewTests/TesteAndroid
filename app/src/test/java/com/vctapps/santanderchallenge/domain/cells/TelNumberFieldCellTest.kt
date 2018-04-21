package com.vctapps.santanderchallenge.domain.cells

import com.vctapps.santanderchallenge.form.domain.cell.TelNumberFieldCell
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TelNumberFieldCellTest {

    lateinit var cellType: TelNumberFieldCell

    val VALID_PHONE_NUMBER = "(11) 1234-1234"

    val VALID_CELL_PHONE_NUMBER = "(11) 91234-1234"

    val INVALID_PHONE_NUMBER = "(11)"

    @Before
    fun setUp(){
        cellType = TelNumberFieldCell()
    }

    @Test
    fun `test if validade phone number correctly`(){
        val isValid = cellType.validateResponse(VALID_PHONE_NUMBER)

        Assert.assertTrue(isValid)
    }

    @Test
    fun `test if validade cell phone number correctly`(){
        val isValid = cellType.validateResponse(VALID_CELL_PHONE_NUMBER)

        Assert.assertTrue(isValid)
    }

    @Test
    fun `test if invalidade phone number correctly`(){
        val isValid = cellType.validateResponse(INVALID_PHONE_NUMBER)

        Assert.assertFalse(isValid)
    }

}