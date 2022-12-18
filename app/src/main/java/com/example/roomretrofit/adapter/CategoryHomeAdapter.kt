package com.example.roomretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomretrofit.data.category.Category
import com.example.roomretrofit.databinding.CategoryRowBinding

class CategoryHomeAdapter():RecyclerView.Adapter<CategoryHomeAdapter.ViewHolder>() {
lateinit var onClickItemCategory:((Category)->Unit)
    private val diffutil=object :DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.idCategory==newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this,diffutil)
    class ViewHolder(val binding:CategoryRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(CategoryRowBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=differ.currentList[position]
        Glide.with(holder.itemView)
            .load(data.strCategoryThumb)
            .into(holder.binding.imgCategory)
        holder.binding.categoryName.text=data.strCategory
        holder.itemView.setOnClickListener {
            onClickItemCategory.invoke(data)
        }
    }

    override fun getItemCount() = differ.currentList.size
}