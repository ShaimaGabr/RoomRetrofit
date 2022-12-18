package com.example.roomretrofit.db

import androidx.room.*
import com.example.roomretrofit.data.meallist.Meal
import kotlinx.coroutines.flow.Flow


@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMeal(meal: Meal)

    @Delete
    suspend fun deleteMeal(meal: Meal)

    @Query("SELECT * FROM mealInformation")
    fun getSavedMeal() : Flow<List<Meal>>
}