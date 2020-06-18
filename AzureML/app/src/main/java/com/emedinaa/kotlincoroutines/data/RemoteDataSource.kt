package com.emedinaa.kotlincoroutines.data

import com.emedinaa.kotlincoroutines.data.http.ApiClient
import org.json.JSONObject

class RemoteDataSource:DataSource {

    override suspend fun processData(jsonObject: JSONObject): ResultObject? {
        return ApiClient.processData(jsonObject)
    }
}