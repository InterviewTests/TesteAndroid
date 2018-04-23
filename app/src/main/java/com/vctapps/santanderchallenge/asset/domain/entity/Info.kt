package com.vctapps.santanderchallenge.asset.domain.entity

import com.vctapps.santanderchallenge.core.domain.InvalidData

data class Info(val name: String = InvalidData.INVALID_STRING,
                val data: String = InvalidData.INVALID_STRING)