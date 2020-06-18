package com.emedinaa.kotlincoroutines

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Course(val id:Int, val nickname:String,@SerializedName("name") val title:String, @SerializedName("mode") val modality:String,
                  @SerializedName("startdate") val date:String, val desc:String, @SerializedName("image") val photo:String):Parcelable

@Parcelize
data class Review(val id:Int, val author:String, val comment:String, val rate:Int):Parcelable

data class Result(val age:Int,val anaemia:Int,val diabetes:Int,val high_blood_pressure:Int,
                  val sex:Int,val smoking:Int,@SerializedName("DEATH_EVENT") val death:Int?,
                  @SerializedName("Scored Labels") val label:Int,
                  @SerializedName("Scored Probabilities") val probability:Double
            )
