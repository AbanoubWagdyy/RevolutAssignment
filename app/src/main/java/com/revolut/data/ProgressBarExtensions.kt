package com.revolut.data

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.show() {
    this.apply {
        visibility = View.VISIBLE
    }
}

fun ProgressBar.hide() {
    this.apply {
        visibility = View.GONE
    }
}