package com.emedinaa.kotlincoroutines.data

import com.emedinaa.kotlincoroutines.Course
import com.emedinaa.kotlincoroutines.Result
import com.emedinaa.kotlincoroutines.Review
import com.google.gson.annotations.SerializedName

data class CourseResponse(val status:Int?,val msg:String?,val data:List<Course>?){
    fun isSuccess():Boolean= (status==200)
}

data class ReviewResponse(val status:Int?,val msg:String?,val data:List<Review>?){
    fun isSuccess():Boolean= (status==200)
}

data class  ResultObject(val output1:List<Result>)
data class ResultResponse(  @SerializedName("Results") val data:ResultObject)