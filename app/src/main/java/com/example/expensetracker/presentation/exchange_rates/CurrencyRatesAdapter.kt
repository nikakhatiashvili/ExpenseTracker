package com.example.expensetracker.presentation.exchange_rates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.databinding.CurrencyItemBinding
import com.example.expensetracker.domain.currency_rates.CommercialRates

class CurrencyRatesAdapter:RecyclerView.Adapter<CurrencyRatesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRatesAdapter.ViewHolder {
        return ViewHolder(
            CurrencyItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    var data: List<CommercialRates> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding:CurrencyItemBinding):RecyclerView.ViewHolder(binding.root){
        private lateinit var currentData:CommercialRates
        fun bind(){
            val context = itemView.context
            currentData = data[adapterPosition]
            binding.buyTxt.text = context.getString(R.string.price,currentData.buy.toString())
            binding.sellTxt.text = context.getString(R.string.price,currentData.sell.toString())
            binding.name.text = currentData.currency
        }
    }

    override fun onBindViewHolder(holder: CurrencyRatesAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount()= data.size

}
