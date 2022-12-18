package com.example.roomretrofit.repository

import android.util.Log
import com.example.roomretrofit.data.category.CategoryList
import com.example.roomretrofit.data.meallist.MealList
import com.example.roomretrofit.data.overlist.OverList
import com.example.roomretrofit.network.MealApi
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val mealApi: MealApi
) {
    suspend fun getRandomMeal():Response<MealList>
    {
        val response=mealApi.getRandomMeal()
        if (response.isSuccessful){
            Log.d("testApp","Success To connected to random meal")
            Log.d("testApp",response.code().toString())
        } else {
            Log.d("testApp","failed To connected to random meal")
            Log.d("testApp",response.code().toString())
        }
        return response
    }

    suspend fun getOverMeal(categoryName:String):Response<OverList>
    {
        val response=mealApi.getOverMeal(categoryName)
        if(response.isSuccessful)
        {
        Log.d("testApp","Success To connected to random meal")
        Log.d("testApp",response.code().toString())
    } else {
        Log.d("testApp","failed To connected to random meal")
        Log.d("testApp",response.code().toString())
    }
    return response
    }
    suspend fun getCategoryHome():Response<CategoryList>{
        val response=mealApi.getCategoryHomeFragment()
        if(response.isSuccessful)
        {
            Log.d("testApp","Success To connected to CategoryHomeFragment")
            Log.d("testApp",response.code().toString())
        } else {
            Log.d("testApp","failed To connected to CategoryHomeFragment")
            Log.d("testApp",response.code().toString())
        }
        return response
    }
}