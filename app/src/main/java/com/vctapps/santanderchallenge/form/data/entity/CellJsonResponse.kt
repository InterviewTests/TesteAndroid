package com.vctapps.santanderchallenge.form.data.entity

import com.google.gson.annotations.SerializedName

data class CellJsonResponse(@SerializedName("cells") val cells: MutableList<CellResponse>)