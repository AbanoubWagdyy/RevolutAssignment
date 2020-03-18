package com.revolut.extensions

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(str: String) {
    activity?.let {
        if (!it.isFinishing)
            Snackbar.make(activity!!.findViewById(android.R.id.content), str, Snackbar.LENGTH_SHORT)
                .show()
    }
}