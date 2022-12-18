package com.example.roomretrofit.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomretrofit.data.category.Category
import com.example.roomretrofit.data.meallist.Meal
import com.example.roomretrofit.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) :ViewModel() {



    private val _getRandomMealLiveData=MutableLiveData<Meal>()
    val getRandomMealLiveData: LiveData<Meal> =_getRandomMealLiveData
    private var saveStatRandomMeal:Meal?=null

    fun getRandomMeal(){
        saveStatRandomMeal?.let { randomMeal->
            _getRandomMealLiveData.postValue(randomMeal)
            return
        }
        viewModelScope.launch {
            val response=homeRepository.getRandomMeal()
            response.body()!!.meals.let {
                _getRandomMealLiveData.postValue(it[0])
            }
            saveStatRandomMeal= response.body()!!.meals[0]
        }
    }


    private val _getOverMealLiveData=MutableLiveData<List<com.example.roomretrofit.data.overlist.Meal>>()
    val getOverMealLiveData:LiveData<List<com.example.roomretrofit.data.overlist.Meal>> = _getOverMealLiveData
    fun getOverMeals(){
        viewModelScope.launch {
            val response=homeRepository.getOverMeal("Seafood")
            if (response.isSuccessful){
                response.body()?.meals.let {
                    _getOverMealLiveData.postValue(it)
                }
            }
        } }

       private var _getCategoriesStateFlow=MutableStateFlow<List<Category>>(emptyList())
       var getCategoriesStateFlow:StateFlow<List<Category>> = _getCategoriesStateFlow
        fun getCategoriesHomeFragment(){
            viewModelScope.launch {
                val response=homeRepository.getCategoryHome()
                if (response.isSuccessful){
                    response.body()?.categories.let { data->
                        if (data != null) {
                            _getCategoriesStateFlow.emit(data)
                        }


                    }
                }
            }
        }


}