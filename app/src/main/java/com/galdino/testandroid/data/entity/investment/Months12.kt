package com.galdino.testandroid.data.entity.investment

data class Months12 (
        override val title: String,
        override val fund: Double? = null,
        override val cdi: Double? = null): PeriodModel(title, fund, cdi)