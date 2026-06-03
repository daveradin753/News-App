package com.newsapplication.mandiri.util

class ApiException(
    code: String?,
    message: String?
): Exception(message?: "Something went wrong")