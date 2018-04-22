package com.vctapps.santanderchallenge.asset.domain.entity

import com.vctapps.santanderchallenge.core.domain.InvalidData

data class TimeInfo(val fund: Double = InvalidData.INVALID_DOUBLE,
                    val cdi: Double = InvalidData.INVALID_DOUBLE)