package com.example.roomretrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomretrofit.adapter.CategoryAdapter
import com.example.roomretrofit.databinding.FragmentItemCategoryBinding
import com.example.roomretrofit.ui.viewmodels.ItemCategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FragmentItemCategory : Fragment() {
        lateinit var binding:FragmentItemCategoryBinding
        private lateinit var categoryName:String
        private val categoryViewModel: ItemCategoryViewModel by viewModels()
       private lateinit var categoryAdapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryAdapter=CategoryAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentItemCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryName= arguments?.getString("categoryName").toString()
//        lifecycleScope.launchWhenStarted {
//            categoryViewModel.getCategory(categoryName)
//            categoryViewModel.categoryStateFlow.collect{data->
//                for(i in data)
//                    Toast.makeText(requireContext(),i.strMeal,Toast.LENGTH_SHORT).show()
//            }
//            getCategoryInformation()
//            setupCategoryRecyclerView()
//        }

        getCategoryInformation()
        setupCategoryRecyclerView()



    }

    private fun setupCategoryRecyclerView() {
        binding.categoryRecyclerview.apply {
            layoutManager=GridLayoutManager(context,2,RecyclerView.VERTICAL,false)
           adapter=categoryAdapter
        }
    }

    private fun getCategoryInformation() {
        lifecycleScope.launchWhenStarted {
            categoryViewModel.getCategory(categoryName)
            categoryViewModel.categoryStateFlow.collect{ data->
                categoryAdapter.differ.submitList(data)
            }
        }
    }

}