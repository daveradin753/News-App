package com.newsapplication.mandiri.util

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.newsapplication.mandiri.R

fun showErrorDialog(context: Context, message: String?, onPositiveClick: (() -> Unit)? = null) {
    MaterialAlertDialogBuilder(context)
        .setTitle(context.getString(R.string.error_title))
        .setMessage(message ?: context.getString(R.string.unknown_error))
        .setPositiveButton(context.getString(R.string.ok)) { dialog, _ ->
            dialog.dismiss()
            onPositiveClick?.invoke()
        }
        .show()
}