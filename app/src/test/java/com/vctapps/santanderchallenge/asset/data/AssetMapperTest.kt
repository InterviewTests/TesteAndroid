package com.vctapps.santanderchallenge.asset.data

import com.google.gson.GsonBuilder
import com.vctapps.santanderchallenge.asset.data.entity.AssetJsonResponse
import com.vctapps.santanderchallenge.core.domain.InvalidData
import com.vctapps.santanderchallenge.util.JsonLoader
import org.junit.Assert.assertEquals
import org.junit.Test

class AssetMapperTest {

    val jsonResponse = JsonLoader.getStringJson("json/valid_fund.json")

    val jsonNullResponse = JsonLoader.getStringJson("json/null_fund.json")

    val gson = GsonBuilder().setLenient().create()

    val assetJsonResponse = gson.fromJson(jsonResponse, AssetJsonResponse::class.java)

    val nullAssetJsonResponse = gson.fromJson(jsonNullResponse, AssetJsonResponse::class.java)

    @Test
    fun `test map from assetJsonResponse to asset is correctly`(){
        val asset = AssetMapper.map(assetJsonResponse)

        assertEquals(assetJsonResponse.screen?.title, asset.title)
        assertEquals(assetJsonResponse.screen?.assetName, asset.assetName)
        assertEquals(assetJsonResponse.screen?.whatIs, asset.whatIs)
        assertEquals(assetJsonResponse.screen?.definition, asset.definition)
        assertEquals(assetJsonResponse.screen?.riskTitle, asset.riskTitle)
        assertEquals(assetJsonResponse.screen?.risk, asset.risk)
        assertEquals(assetJsonResponse.screen?.infoTitle, asset.infoTitle)
        assertEquals(assetJsonResponse.screen?.moreInfo?.month?.fund!!, asset.monthInfo.fund, 0.0)
        assertEquals(assetJsonResponse.screen?.moreInfo?.month?.CDI!!, asset.monthInfo.cdi, 0.0)
        assertEquals(assetJsonResponse.screen?.moreInfo?.year?.fund!!, asset.yearInfo.fund, 0.0)
        assertEquals(assetJsonResponse.screen?.moreInfo?.year?.CDI!!, asset.yearInfo.cdi, 0.0)
        assertEquals(assetJsonResponse.screen?.moreInfo?.twelveMonths?.fund!!, asset.twelveInfo.fund, 0.0)
        assertEquals(assetJsonResponse.screen?.moreInfo?.twelveMonths?.CDI!!, asset.twelveInfo.cdi, 0.0)
        assertEquals(assetJsonResponse.screen?.info!![0].name!!, asset.info[0].name)
        assertEquals(assetJsonResponse.screen?.info!![0].data!!, asset.info[0].data)
        assertEquals(assetJsonResponse.screen?.downInfo!![0].name!!, asset.downInfo[0].name)
        assertEquals(InvalidData.INVALID_STRING, asset.downInfo[0].data)

    }

    @Test
    fun `test map from null assetJsonResponse to asset is correctly`(){
        val asset = AssetMapper.map(nullAssetJsonResponse)

        assertEquals(InvalidData.INVALID_STRING, asset.title)
        assertEquals(InvalidData.INVALID_STRING, asset.assetName)
        assertEquals(InvalidData.INVALID_STRING, asset.whatIs)
        assertEquals(InvalidData.INVALID_STRING, asset.definition)
        assertEquals(InvalidData.INVALID_STRING, asset.riskTitle)
        assertEquals(InvalidData.INVALID_INT, asset.risk)
        assertEquals(InvalidData.INVALID_STRING, asset.infoTitle)
        assertEquals(InvalidData.INVALID_DOUBLE, asset.monthInfo.fund, 0.0)
        assertEquals(InvalidData.INVALID_DOUBLE, asset.monthInfo.cdi, 0.0)
        assertEquals(InvalidData.INVALID_DOUBLE, asset.yearInfo.fund, 0.0)
        assertEquals(InvalidData.INVALID_DOUBLE, asset.yearInfo.cdi, 0.0)
        assertEquals(InvalidData.INVALID_DOUBLE, asset.twelveInfo.fund, 0.0)
        assertEquals(InvalidData.INVALID_DOUBLE, asset.twelveInfo.cdi, 0.0)
        assertEquals(InvalidData.INVALID_STRING, asset.info[0].name)
        assertEquals(InvalidData.INVALID_STRING, asset.info[0].data)
        assertEquals(InvalidData.INVALID_STRING, asset.downInfo[0].name)
        assertEquals(InvalidData.INVALID_STRING, asset.downInfo[0].data)

    }

}