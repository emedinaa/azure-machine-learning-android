package com.emedinaa.kotlincoroutines.data

import org.json.JSONObject

interface DataSource {

    suspend fun processData(jsonObject: JSONObject):ResultObject?
}