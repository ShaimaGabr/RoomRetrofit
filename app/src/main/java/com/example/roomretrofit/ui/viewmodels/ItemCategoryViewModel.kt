package com.example.roomretrofit.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomretrofit.data.overlist.Meal
import com.example.roomretrofit.repository.itemCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemCategoryViewModel @Inject constructor(private val categoryRepository: itemCategoryRepository):ViewModel() {
private var _categoryStateFlow= MutableStateFlow<List<Meal>>(emptyList())
    var categoryStateFlow:StateFlow<List<Meal>> = _categoryStateFlow

    fun getCategory(categoryName:String){
        viewModelScope.launch {
            try {
                 val response=categoryRepository.getCategory(categoryName)
                if(response.isSuccessful)
                    _categoryStateFlow.emit(response.body()!!.meals)

            }catch (t:Throwable){
                Log.d("testApp",t.message.toString())
            }
        }
    }


}