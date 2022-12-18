package com.example.roomretrofit

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.roomretrofit.data.meallist.Meal
import com.example.roomretrofit.databinding.FragmentMealDetailBinding
import com.example.roomretrofit.ui.viewmodels.MealViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MealDetailFragment : Fragment() {
    private lateinit var binding: FragmentMealDetailBinding
    private lateinit var mealId     :String
    private  lateinit var mealThumb :String
    private  lateinit var mealTitle :String
    private  lateinit var SaveMeal: Meal
   private val mealViewModel:MealViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentMealDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), arguments?.getString("mealTitle")  ,Toast.LENGTH_SHORT).show()
        getMealInformation()
        mealViewModel.getMealInformation(mealId)
        observeGetMealInformationData()

        binding.btnFavorite.setOnClickListener {
            SaveMeal.let { meal->
                if(meal !=null){
                    mealViewModel.upsetMeal(meal)
                }}
//                lifecycleScope.launchWhenStarted {
//                    mealViewModel.getSavedMeal().collect{ meals->
//                        Log.d("testApp1", meals.toString())
//                    }
//                }
            }



    }

    private fun observeGetMealInformationData() {
        mealViewModel.getMealInformationLiveData.observe(viewLifecycleOwner, Observer { data->
            SaveMeal=data
          binding.categoryName.text="Category:"+data .strCategory
            binding.location.text="Area:"+data.strArea
            binding.detailsInstruction.text=data.strInstructions
            binding.imgYoutube.setOnClickListener {
                val intent= Intent(Intent.ACTION_VIEW,Uri.parse(data.strYoutube))
                startActivity(intent)
            }
        })
    }

    private fun getMealInformation() {
        mealId= arguments?.getString("mealId")!!
        mealTitle=arguments?.getString("mealTitle")!!
        mealThumb=arguments?.getString("mealThumb")!!
       Glide.with(requireContext())
            .load(mealThumb)
            .into(binding.mealImage)
        binding.collapsing.title=mealTitle
    }
}