package com.emedinaa.kotlincoroutines.data.http

import android.util.Log
import com.emedinaa.kotlincoroutines.Course
import com.emedinaa.kotlincoroutines.Review
import com.emedinaa.kotlincoroutines.data.CourseResponse
import com.emedinaa.kotlincoroutines.data.ResultObject
import com.emedinaa.kotlincoroutines.data.ResultResponse
import com.emedinaa.kotlincoroutines.data.ReviewResponse
import com.google.gson.GsonBuilder
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

object ApiClient {

    //https://ussouthcentral.services.azureml.net/workspaces/7ae973c564804362a10cbb7734b36d48/services/5a00c16c2116402b9a8cecde99f10829/execute?api-version=2.0&format=swagger
    private const val URL =  "https://ussouthcentral.services.azureml.net"
    private const val TOKEN = "iDwPGmbauJGQIh5SopFqs3EfldTB3iJRdYoLst9uWuX7eNpa2xkg6Gf0VlOgmuDPa4C+hxxZGTlbhhmxDAFVlw=="
    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor())
            .build()
    }

    fun processData(jsonObject: JSONObject):ResultObject?{
        return try {
            val MEDIATYPE = "application/json".toMediaTypeOrNull()

            val response = httpClient.newCall(
                buildPostRequest("/workspaces/7ae973c564804362a10cbb7734b36d48/services/5a00c16c2116402b9a8cecde99f10829/execute?api-version=2.0&format=swagger",
                    jsonObject.toString().toRequestBody(MEDIATYPE)
                )
            ).execute()
            if(response.isSuccessful){
                val resultResponse = GsonBuilder().create().fromJson(response.body?.string(),
                    ResultResponse::class.java)
                resultResponse.data
            }else{
                null
            }
        }catch (e:Exception){
            Log.v("CONSOLE","e $e")
            null
        }
    }
    /**
     * val typeToken = object : TypeToken<List<Course>>() {}.type
     * GsonBuilder().create().fromJson<List<Course>>(response.body?.string(),typeToken)
     */
    fun getCourses():List<Course> {
        return try {
            val response = httpClient.newCall(
                buildGetRequest("/api/courses/")
            ).execute()
            if(response.isSuccessful){
                val courseResponse = GsonBuilder().create().fromJson<CourseResponse>(response.body?.string(),
                    CourseResponse::class.java)
                courseResponse.data?: emptyList()
            }else{
                emptyList()
            }
        }catch (e:Exception){
            emptyList()
        }
    }

    fun getReviews():List<Review> {
        return try {
            val response = httpClient.newCall(
                buildGetRequest("/api/reviews/")
            ).execute()
            if(response.isSuccessful){
                val reviewResponse = GsonBuilder().create().fromJson<ReviewResponse>(response.body?.string(),
                    ReviewResponse::class.java)
                reviewResponse.data?: emptyList()
            }else{
                emptyList()
            }
        }catch (e:Exception){
            emptyList()
        }
    }

    private fun buildGetRequest(api:String):Request{
        return Request.Builder().apply {
            get()
            url(URL.plus(api))
            }.build()
    }

    private fun buildPostRequest(api:String,requestBody:RequestBody):Request{
        return Request.Builder().apply {
            addHeader("Authorization","Bearer ".plus(TOKEN))
            post(requestBody)
            url(URL.plus(api))
        }.build()
    }
}