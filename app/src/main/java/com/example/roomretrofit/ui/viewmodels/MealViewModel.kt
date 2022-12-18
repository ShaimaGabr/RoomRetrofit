package com.example.roomretrofit.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomretrofit.data.meallist.Meal
import com.example.roomretrofit.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MealViewModel @Inject constructor(private val mealRepository: MealRepository):ViewModel() {


    private val _getMealInformationLiveData = MutableLiveData<Meal>()
    val getMealInformationLiveData:LiveData<Meal> = _getMealInformationLiveData

    fun getMealInformation(mealId:String){
        viewModelScope.launch{
            try {
                val response = mealRepository.getMealInformation(mealId)
                if(response.isSuccessful){
                    _getMealInformationLiveData.value=response.body()!!.meals[0]
                }
            }catch (t:Throwable){
                Log.d("testApp",t.message.toString())
            }
        }
    }
    fun upsetMeal(meal: Meal)=viewModelScope.launch {
        mealRepository.upSetMeal(meal)
    }
    fun deleteMeal(meal: Meal){
        viewModelScope.launch {
            mealRepository.deleteMeal(meal)
        }
    }
   fun getSavedMeal()=mealRepository.getMealSaved

}