package com.example.homework10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework10.databinding.ItemJsonLayoutBinding

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    val countriesList = mutableListOf<Countries>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val binding =
            ItemJsonLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount() = countriesList.size

    inner class CountriesViewHolder(private val binding: ItemJsonLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            val model = countriesList[adapterPosition]
            binding.tvName.text = model.name
            binding.tvCapital.text = model.capital
            binding.tvRegion.text = model.region
            binding.tvPopulation.text = model.population.toString()
            model.borders?.forEach {
                binding.tvBorders.append(" $it")
            }
            model.currencies?.forEach {
                binding.tvCurrencies.append("${it.code} ${it.name} ${it.symbol} \n")
            }
        }
    }

    fun setDataToList(countriesList: MutableList<Countries>) {
        this.countriesList.clear()
        this.countriesList.addAll(countriesList)
        notifyDataSetChanged()
    }

    fun clearData(){
        countriesList.clear()
        notifyDataSetChanged()
    }
}