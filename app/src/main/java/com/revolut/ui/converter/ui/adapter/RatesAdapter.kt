package com.revolut.ui.converter.ui.adapter

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revolut.R
import com.revolut.ui.converter.data.model.RecyclerRate
import kotlinx.android.synthetic.main.layout_rate_item.view.*
import java.util.*

class RatesAdapter(private val startDragListener: OnStartDragListener) :
    RecyclerView.Adapter<RatesAdapter.ItemViewHolder>(),
    ItemMoveCallbackListener.Listener {

    private var rates = emptyList<RecyclerRate>().toMutableList()

    fun setRates(updatedRates: List<RecyclerRate>) {
        val notifyItemChangedCall: Boolean = rates.size == 0
        rates = updatedRates.toMutableList()
        if (notifyItemChangedCall)
            notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return rates.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val rate = rates[position]

        if (position == 0)
            this.startDragListener.onSetBaseCurrency(rate.baseCurrency)

        holder.bind(rate)

        holder.itemView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                this.startDragListener.onStartDrag(holder)
            }
            return@setOnTouchListener true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_rate_item, parent, false)
        return ItemViewHolder(itemView)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(recyclerRate: RecyclerRate) {
            itemView.currencySymbol.text = recyclerRate.baseCurrency
            when (recyclerRate.baseCurrency) {
                "USD" -> {
                    itemView.currencyIcon.setImageResource(R.mipmap.us_icon)
                    itemView.currencySymbol.text = "US Dollar"
                }

                "EUR" -> {
                    itemView.currencyIcon.setImageResource(R.mipmap.euro_icon)
                    itemView.currencySymbol.text = "Euro"
                }

                "SEK" -> {
                    itemView.currencyIcon.setImageResource(R.mipmap.swedish_icon)
                    itemView.currencySymbol.text = "Swedish Krona"
                }

                "CAD" -> {
                    itemView.currencyIcon.setImageResource(R.mipmap.cad_icon)
                    itemView.currencySymbol.text = "Canadian Dollar"
                }
            }

            itemView.currencyValue.isEnabled = adapterPosition == 0
        }
    }

    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(rates, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(rates, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onRowSelected(itemViewHolder: ItemViewHolder) {
    }

    override fun onRowClear(itemViewHolder: ItemViewHolder) {
    }
}