package com.revolut.ui.converter.ui.adapter

import androidx.recyclerview.widget.RecyclerView

interface OnStartDragListener {
    fun onStartDrag(
        viewHolder: RecyclerView.ViewHolder?
    )

    fun onSetBaseCurrency(base: String)
}