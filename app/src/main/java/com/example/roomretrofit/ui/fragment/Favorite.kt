package com.example.roomretrofit.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomretrofit.R
import com.example.roomretrofit.adapter.FavoriteAdapter
import com.example.roomretrofit.databinding.FragmentFavoriteBinding
import com.example.roomretrofit.ui.viewmodels.MealViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class Favorite : Fragment() {
    private lateinit var binding:FragmentFavoriteBinding
    private lateinit var favoriteAdapter: FavoriteAdapter
    private   val mealViewModel:MealViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteAdapter=FavoriteAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFavoriteRecycleView()
        getSavedData()
    }

    private fun getSavedData() {
        lifecycleScope.launchWhenStarted {
            mealViewModel.getSavedMeal().collect{savedData->
                favoriteAdapter.differ.submitList(savedData)

            }
        }
    }

    private fun setupFavoriteRecycleView() {
        binding.favoriteRecyclerView.apply {
            layoutManager=GridLayoutManager(context,2,RecyclerView.VERTICAL,false)
            adapter=favoriteAdapter
        }

    }
}