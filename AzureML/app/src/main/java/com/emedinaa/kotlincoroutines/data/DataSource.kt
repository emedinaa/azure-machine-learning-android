package com.emedinaa.kotlincoroutines.data

import com.emedinaa.kotlincoroutines.Course
import com.emedinaa.kotlincoroutines.Review
import org.json.JSONObject

interface DataSource {

    suspend fun fetchCourses():List<Course>

    suspend fun fetchCourseByName(name:String):List<Course>

    suspend fun fetchReviews():List<Review>

    suspend fun processData(jsonObject: JSONObject):ResultObject?
}