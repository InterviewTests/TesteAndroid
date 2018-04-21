package com.vctapps.santanderchallenge.util

import com.google.gson.GsonBuilder
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

object JsonLoader {

    fun <T> get(fileName: String, clazz: Class<T>): T{
        val loader = ClassLoader.getSystemClassLoader()

        val json = Files.lines(Paths.get(loader.getResource(fileName).toURI()))
                .parallel()
                .collect(Collectors.joining())

        return GsonBuilder().create().fromJson(json, clazz)
    }

    fun getStringJson(fileName: String): String{
        val loader = ClassLoader.getSystemClassLoader()

        return Files.lines(Paths.get(loader.getResource(fileName).toURI()))
                .parallel()
                .collect(Collectors.joining())
    }

}