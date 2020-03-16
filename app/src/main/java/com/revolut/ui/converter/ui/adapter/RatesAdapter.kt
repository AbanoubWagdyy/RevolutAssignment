package com.revolut.ui.converter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revolut.R
import com.revolut.ui.converter.data.model.Rates

class RatesAdapter(
    var rates: List<Rates>?
) : RecyclerView.Adapter<RatesAdapter.RateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RateViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        if (rates != null)
            return rates!!.size
        else
            return 0
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        val rate = rates!![position]

    }

    fun setCurrencyRates(rates: List<Rates>?) {
        this.rates = rates
        notifyDataSetChanged()
    }

    class RateViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.rate_list_item, parent, false)) {
    }
}