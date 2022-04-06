package com.emit.paises.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emit.paises.databinding.ItemListBinding
import com.emit.paises.model.entities.Country

class CountryListAdapter : ListAdapter<Country, CountryListAdapter.CountryListViewHolder>(DiffCallback) {

    private companion object DiffCallback : DiffUtil.ItemCallback<Country>() {

        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean =
            oldItem.code == newItem.code

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean =
            oldItem.code == newItem.code


    }

    inner class CountryListViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) = with(binding) {
            textviewCode.text = country.code
            textviewCurrencyCode.text = country.currencyCodes[0]
            textviewName.text = country.name
            textviewWikiDataId.text = country.wikiDataId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        val binding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CountryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}