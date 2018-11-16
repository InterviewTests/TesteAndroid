package com.galdino.testandroid.domain

import com.galdino.testandroid.data.entity.CellResponseBody

interface IRepository {
    fun getCell(): CellResponseBody
}