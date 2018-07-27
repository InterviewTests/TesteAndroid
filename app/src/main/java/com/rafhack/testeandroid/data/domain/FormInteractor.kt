package com.rafhack.testeandroid.data.domain

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.rafhack.testeandroid.data.entities.form.Cell
import com.rafhack.testeandroid.data.remote.ServiceGenerator
import com.rafhack.testeandroid.data.remote.services.FormService
import io.reactivex.Single


class FormInteractor {

    private val service get() = ServiceGenerator.createService(FormService::class.java)

    fun getCells(): Single<ArrayList<Cell>> {
        return service.getCells()
                .map { t: JsonObject? ->
                    val cellsType = object : TypeToken<ArrayList<Cell>>() {}.type
                    val cells: ArrayList<Cell> = GsonBuilder().create().fromJson(t?.get("cells"), cellsType)
                    cells
                }
    }
}