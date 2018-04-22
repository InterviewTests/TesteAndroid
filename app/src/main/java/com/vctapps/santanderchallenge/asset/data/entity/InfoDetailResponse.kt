package com.vctapps.santanderchallenge.asset.data.entity

import com.google.gson.annotations.SerializedName

data class InfoDetailResponse(@SerializedName("name") val name: String?,
                              @SerializedName("data") val data: String?)