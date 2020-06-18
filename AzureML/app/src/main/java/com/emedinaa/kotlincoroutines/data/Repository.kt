package com.emedinaa.kotlincoroutines.data

import com.emedinaa.kotlincoroutines.Course
import com.emedinaa.kotlincoroutines.Review
import org.json.JSONObject

class Repository(private val dataSource: DataSource) {

    suspend fun processData(jsonObject: JSONObject):ResultObject?{
        return dataSource.processData(jsonObject)
    }
}