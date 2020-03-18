package com.revolut.ui.converter.ui.adapter

import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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

    var rates = emptyList<RecyclerRate>().toMutableList()

    fun setUpdatedRates(updatedRates: List<RecyclerRate>) {
        val notifyItemChangedCall: Boolean = rates.size == 0
        if (rates.size == 0)
            rates = updatedRates.toMutableList()
        else {
            for (rate in rates) {
                val update = updatedRates.filter {
                    it.baseCurrency == rate.baseCurrency
                }[0]
                rate.baseCurrencyRate = update.baseCurrencyRate
                rate.baseCurrency = update.baseCurrency
                rate.baseCurrencyValue = update.baseCurrencyValue
            }
        }
        if (notifyItemChangedCall)
            notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_rate_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val rate = rates[position]
        holder.itemView.currencyValue.removeTextChangedListener(watcher)

        if (rate.baseCurrencyValue != 0.0)
            holder.itemView.currencyValue.setText(rate.baseCurrencyValue.toString())

        if (position == 0) {
            this.startDragListener.onSetBaseCurrency(rate.baseCurrency)

            holder.itemView.currencyValue.addTextChangedListener(watcher)
        }

        holder.bind(rate)

        holder.itemView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                this.startDragListener.onStartDrag(holder)
            }
            return@setOnTouchListener true
        }
    }

    override fun getItemCount(): Int {
        return rates.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(recyclerRate: RecyclerRate) {
            itemView.currencySymbol.text = recyclerRate.baseCurrency
            when (recyclerRate.baseCurrency) {
                "USD" -> {
                    itemView.currencyIcon.setImageResource(R.mipmap.us_icon)
                    itemView.description.text = itemView.context.getString(R.string.usd_description)
                }

                "EUR" -> {
                    itemView.currencyIcon.setImageResource(R.mipmap.euro_icon)
                    itemView.description.text =
                        itemView.context.getString(R.string.euro_description)
                }

                "SEK" -> {
                    itemView.currencyIcon.setImageResource(R.mipmap.swedish_icon)
                    itemView.description.text = itemView.context.getString(R.string.sek_description)
                }

                "CAD" -> {
                    itemView.currencyIcon.setImageResource(R.mipmap.cad_icon)
                    itemView.description.text = itemView.context.getString(R.string.cad_description)
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
        Log.d("", "")
    }

    override fun onRowClear(itemViewHolder: ItemViewHolder) {
        Log.d("", "")
        for (i in 0 until rates.size) {
            rates[i].baseCurrencyValue = 0.0
        }
        notifyDataSetChanged()
    }

    internal val mRunnable: Runnable = Runnable {
        notifyDataSetChanged()
    }

    private var mDelayHandler = Handler()

    val watcher =  object : TextWatcher {
        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
            s: CharSequence, start: Int,
            count: Int, after: Int
        ) {
        }

        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
            if (s.isNotEmpty()) {
                for (i in 1 until rates.size) {
                    val convertedValue = s.toString().toDouble() * rates[i].baseCurrencyRate
                    rates[i].baseCurrencyValue = convertedValue
                }
                mDelayHandler.postDelayed(mRunnable, 0)
            } else {
                for (i in 0 until rates.size) {
                    rates[i].baseCurrencyValue = 0.0
                }
                mDelayHandler.postDelayed(mRunnable, 0)
            }
        }
    }
}