package com.santander.android.event

import java.io.Serializable

data class SendContactEvent(
        val mForm: Map<Int, String> = HashMap()
) : Serializable