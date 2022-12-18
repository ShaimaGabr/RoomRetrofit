package com.example.roomretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.roomretrofit.R
import com.example.roomretrofit.databinding.ActivityMealBinding

class MealActivity : AppCompatActivity() {
    private lateinit var binding  :ActivityMealBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getMealInformation()

    }

    private fun getMealInformation() {
//        val intent  =intent
//        mealId=intent.getStringExtra("mealId").toString()
//        mealThumb=intent.getStringExtra("mealThumb").toString()
//        mealTitle=intent.getStringExtra("mealTitle").toString()
//
//        Glide.with(applicationContext)
//            .load(mealThumb)
//            .into(binding.mealImage)
//        binding.collapsing.title=mealTitle
    }
}