package com.example.yumgrove

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yumgrove.databinding.PopularRvItemBinding

class PopularAdapter(var context: Context):RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    private var datalist: List<Recipe> = listOf()
    inner class ViewHolder(var binding: PopularRvItemBinding,):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=PopularRvItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    fun changeData(newList: List<Recipe>) {
        datalist = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(datalist.get(position).img).into(holder.binding.popularImg)


        holder.binding.popularTxt.text= datalist.get(position).tittle
        var time = datalist.get(position).ing.split("/n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        holder.binding.popularTime.text=time.get(0)
    }
}