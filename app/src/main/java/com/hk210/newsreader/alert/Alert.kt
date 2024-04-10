package com.hk210.newsreader.alert

import android.view.View
import com.google.android.material.snackbar.Snackbar

object Alert {

    fun showSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}