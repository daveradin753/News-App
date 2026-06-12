package com.newsapplication.mandiri.util

class ApiException(
    val code: String?,
    override val message: String?
): Exception(message?: "Something went wrong")