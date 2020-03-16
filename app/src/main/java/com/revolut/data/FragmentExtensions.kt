package com.revolut.data

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(str: String) {
    activity?.let {
        Snackbar.make(activity!!.findViewById(android.R.id.content), str, Snackbar.LENGTH_SHORT)
            .show()
    }
}