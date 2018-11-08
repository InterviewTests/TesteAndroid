package com.galdino.testandroid.mvp.helpers

import android.content.Context
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.InputStream
import java.util.concurrent.TimeUnit

abstract class BaseMockedHelper(private val context: Context) {

    protected fun <C> getSingleFromFile(fileName: String, responseBodyClass: Class<C>, delayInSeconds: Long = 0): Single<C> {
        return Single.fromCallable { getFromFile(fileName, responseBodyClass) }
                .delay(delayInSeconds, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
    }

    private fun <C> getFromFile(fileName: String, clazz: Class<C>): C {
        val inputStream: InputStream = context.assets.open(fileName)
        val inputString = inputStream.bufferedReader().use { it.readText() }
        return Gson().fromJson<C>(inputString, clazz)
    }

}