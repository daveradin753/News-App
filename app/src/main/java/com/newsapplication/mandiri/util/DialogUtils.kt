package com.newsapplication.mandiri.util

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.newsapplication.mandiri.R

fun Context.showErrorDialog(message: String?, onPositiveClick: (() -> Unit)? = null) {
    MaterialAlertDialogBuilder(this)
        .setTitle(getString(R.string.error_title))
        .setMessage(message ?: getString(R.string.unknown_error))
        .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
            dialog.dismiss()
            onPositiveClick?.invoke()
        }
        .show()
}