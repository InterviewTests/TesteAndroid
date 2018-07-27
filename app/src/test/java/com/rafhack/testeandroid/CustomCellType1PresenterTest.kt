package com.rafhack.testeandroid

import com.rafhack.testeandroid.form.customCells.customCellType1.CustomCellType1Contract
import com.rafhack.testeandroid.form.customCells.customCellType1.CustomCellType1Presenter
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
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



}