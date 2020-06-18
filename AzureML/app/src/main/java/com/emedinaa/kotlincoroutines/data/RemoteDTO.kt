package com.emedinaa.kotlincoroutines.data

import com.emedinaa.kotlincoroutines.Result
import com.google.gson.annotations.SerializedName

data class  ResultObject(val output1:List<Result>)
data class ResultResponse(  @SerializedName("Results") val data:ResultObject)