package com.santander.android.event

import java.io.Serializable

data class NewContactEvent(
        val mForm: Map<Int, String> = HashMap()
) : Serializable