package com.rafhack.testeandroid

import com.rafhack.testeandroid.form.customCells.customCellType1.CustomCellType1Contract
import com.rafhack.testeandroid.form.customCells.customCellType1.CustomCellType1Presenter
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CustomCellType1PresenterTest {

    @Mock
    private val view: CustomCellType1Contract.View? = null
    private lateinit var presenter: CustomCellType1Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = CustomCellType1Presenter(view!!)
    }

    @Test
    fun validatePhoneSuccessTest() {
        Assert.assertTrue(presenter.validatePhoneField("(11) 1111-1111"))
        verify(view!!).updateErrorState(true)
    }

    @Test
    fun validatePhoneFailTest() {
        Assert.assertFalse(presenter.validatePhoneField(""))
        verify(view!!).updateErrorState(false)
    }

    @Test
    fun validateEmailSuccessTest() {
        Assert.assertTrue(presenter.validateEmailField("d.d@d.d"))
        verify(view!!).updateErrorState(true)
    }

    @Test
    fun validateEmailFailTest() {
        Assert.assertFalse(presenter.validateEmailField("d.d"))
        verify(view!!).updateErrorState(false)
    }

}