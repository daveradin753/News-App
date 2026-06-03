package com.newsapplication.mandiri.util

object StringUtils {

    fun String.capitalizeWords(): String {
        return split(" ")
            .joinToString(" ") { word ->
                word.replaceFirstChar { char ->
                    char.uppercase()
                }
            }
    }

}