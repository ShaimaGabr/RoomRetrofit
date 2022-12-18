package com.example.roomretrofit.network


import com.example.roomretrofit.data.category.CategoryList
import com.example.roomretrofit.data.meallist.MealList
import com.example.roomretrofit.data.overlist.OverList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("random.php")
    suspend fun getRandomMeal():Response<MealList>

    @GET("filter.php")
    suspend fun getOverMeal(@Query("c") categoryName:String):Response<OverList>

    @GET("categories.php")
    suspend fun getCategoryHomeFragment():Response<CategoryList>
    @GET ("lookup.php")
    suspend fun getMealInformation(@Query("i") mealId:String) : Response<MealList>

    @GET("filter.php")
    suspend fun getCategory(@Query("c") categoryName:String):Response<OverList>

}