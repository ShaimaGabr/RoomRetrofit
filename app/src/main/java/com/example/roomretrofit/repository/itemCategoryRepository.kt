package com.example.roomretrofit.repository

import android.util.Log
import com.example.roomretrofit.data.overlist.OverList
import com.example.roomretrofit.network.MealApi
import retrofit2.Response
import javax.inject.Inject

class itemCategoryRepository @Inject constructor(private val mealApi: MealApi) {
suspend fun getCategory(categoryName:String):Response<OverList>{
    val response=mealApi.getCategory(categoryName)
    if(response.isSuccessful)
    {
        Log.d("testApp","Success to connected to MealInformation")
        Log.d("testApp",response.code().toString())
    }
    else
    {
        Log.d("testApp","Failed to connected to MealInformation")
        Log.d("testApp",response.code().toString())
    }
    return response
}

}