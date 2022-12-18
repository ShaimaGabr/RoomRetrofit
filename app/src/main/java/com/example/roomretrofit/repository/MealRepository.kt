package com.example.roomretrofit.repository

import android.util.Log
import com.example.roomretrofit.data.meallist.Meal
import com.example.roomretrofit.data.meallist.MealList
import com.example.roomretrofit.db.MealDatabase
import com.example.roomretrofit.network.MealApi
import retrofit2.Response
import javax.inject.Inject

class MealRepository @Inject constructor(
    private val maelApi:MealApi,
    private val db      :MealDatabase
    ) {
    private val database=db.mealDao()

    suspend fun getMealInformation(mealId:String):Response<MealList>
    {
        val response=maelApi.getMealInformation(mealId)
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

    suspend fun upSetMeal(meal: Meal){
        database.upsertMeal(meal)
    }
    suspend fun deleteMeal(meal: Meal){
        database.deleteMeal(meal)
    }

    val getMealSaved=database.getSavedMeal()

}