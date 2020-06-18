package com.emedinaa.kotlincoroutines.data

import com.emedinaa.kotlincoroutines.Course
import com.emedinaa.kotlincoroutines.Review
import com.emedinaa.kotlincoroutines.data.http.ApiClient
import org.json.JSONObject

class RemoteDataSource:DataSource {

    override suspend fun fetchCourses(): List<Course> {
        return  ApiClient.getCourses()
    }

    override suspend fun fetchCourseByName(name: String): List<Course> {
        return  ApiClient.getCourses()
    }

    override suspend fun fetchReviews(): List<Review> {
        return  ApiClient.getReviews()
    }

    override suspend fun processData(jsonObject: JSONObject): ResultObject? {
        return ApiClient.processData(jsonObject)
    }
}