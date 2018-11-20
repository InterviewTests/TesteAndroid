package com.galdino.testandroid.data.entity.investment

data class Year(
        override val title: String,
        override val fund: Double? = null,
        override val cdi: Double? = null): PeriodModel(title, fund, cdi)