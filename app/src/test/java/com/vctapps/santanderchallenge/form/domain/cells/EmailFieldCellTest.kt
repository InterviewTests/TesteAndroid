package com.vctapps.santanderchallenge.form.domain.cells

import com.vctapps.santanderchallenge.form.domain.cell.EmailFieldCell
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class EmailFieldCellTest {

    lateinit var cellType: EmailFieldCell

    val VALID_EMAIL = "test@mail.com"

    val INVALID_EMAIL = "test"

    @Before
    fun setUp(){
        cellType = EmailFieldCell()
    }

    @Test
    fun `test if validade email correctly`(){
        val isValid = cellType.validateResponse(VALID_EMAIL)

        assertTrue(isValid)
    }

    @Test
    fun `test if invalidade email correctly`(){
        val isValid = cellType.validateResponse(INVALID_EMAIL)

        assertFalse(isValid)
    }

}