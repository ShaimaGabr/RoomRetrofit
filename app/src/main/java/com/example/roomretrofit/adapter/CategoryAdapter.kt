package com.example.roomretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomretrofit.data.overlist.Meal
import com.example.roomretrofit.databinding.FavoriteRowBinding

class CategoryAdapter:RecyclerView.Adapter<CategoryAdapter.viewHolder> (){
    private val diffult= object:DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal==newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
           return oldItem==newItem
        }
    }
  class  viewHolder(val binding:FavoriteRowBinding):RecyclerView.ViewHolder(binding.root)
  val differ=AsyncListDiffer(this,diffult)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
       return viewHolder(FavoriteRowBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       val data=differ.currentList[position]
        Glide.with(holder.itemView)
            .load(data.strMealThumb)
            .into(holder.binding.favoriteImg)
        holder.binding.favoriteName.text=data.strMeal
    }

    override fun getItemCount()=differ.currentList.size
}