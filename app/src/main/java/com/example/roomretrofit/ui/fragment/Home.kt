package com.example.roomretrofit.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomretrofit.R
import com.example.roomretrofit.adapter.CategoryHomeAdapter
import com.example.roomretrofit.adapter.OverAdapter
import com.example.roomretrofit.databinding.FragmentHomeBinding
import com.example.roomretrofit.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment() {
   private lateinit var binding: FragmentHomeBinding
  private val homMvvm:HomeViewModel by viewModels()
    private lateinit var overAdapter: OverAdapter
    private  lateinit var categoryHomeAdapter: CategoryHomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       overAdapter= OverAdapter()
        categoryHomeAdapter= CategoryHomeAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRandomMeal(view)
        getOverMeal()
        setUpOverRecView()
        getcategory()
        setupCategoriesHomeRecView()
        onOverItemClick(view)
        onCategoryItemClick(view)


    }

    private fun onCategoryItemClick(view: View) {
        categoryHomeAdapter.onClickItemCategory={data->
            val bundle = Bundle()
            bundle.putString("categoryName",data.strCategory)
            Navigation.findNavController(view).navigate(R.id.action_home_to_fragmentItemCategory,bundle)
        }
    }

    private fun onOverItemClick(view: View) {
        overAdapter.onOverItemClick={data->

            val bundle = Bundle()
            bundle.putString("mealId",data.idMeal)
            bundle.putString("mealTitle",data.strMeal)
            bundle.putString("mealThumb",data.strMealThumb)
            Navigation.findNavController(view).navigate(R.id.action_home_to_mealDetailFragment,bundle)
        }
    }

    private fun setupCategoriesHomeRecView() {
        binding.categoriesRecycler.apply {
            layoutManager=GridLayoutManager(context,3,RecyclerView.VERTICAL,false)
            adapter =categoryHomeAdapter
        }
    }

    private fun getcategory() {
        homMvvm.getCategoriesHomeFragment()
        lifecycleScope.launchWhenStarted {
            homMvvm.getCategoriesStateFlow.collect{data->
                categoryHomeAdapter.differ.submitList(data)

            }
        }
    }

    private fun getOverMeal(){
        homMvvm.getOverMeals()
        homMvvm.getOverMealLiveData.observe(viewLifecycleOwner){ data->
           overAdapter.differ.submitList(data)
        }
    }
    private fun setUpOverRecView(){
        binding.overRecycler.apply {
           adapter=overAdapter
        }
    }

    private fun getRandomMeal(view:View){
        homMvvm.getRandomMeal()
        homMvvm.getRandomMealLiveData.observe(viewLifecycleOwner){data ->
            if (data !=null){
            Glide.with(this)
                .load(data.strMealThumb)
                .into(binding.randomImage)
            try{

                binding.cartHome.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("mealId",data.idMeal)
                    bundle.putString("mealTitle",data.strMeal)
                    bundle.putString("mealThumb",data.strMealThumb)
                    Navigation.findNavController(view).navigate(R.id.action_home_to_mealDetailFragment,bundle)

                }
//                binding.cartHome.setOnClickListener {
//                    val intent= Intent(requireActivity(),MealActivity::class.java)
//                    intent.putExtra("mealId",data.idMeal)
//                    intent.putExtra("mealTitle",data.strMeal)
//                    intent.putExtra("mealThumb",data.strMealThumb)
//                    startActivity(intent)
               // }
            }catch (t:Throwable){
                Log.d("testApp",t.message.toString())
            }
    }}}

}