package com.galdino.testandroid.data.entity.investment

data class Month(
        override val fund: Double? = null,
        override val cdi: Double? = null): PeriodModel(fund, cdi)