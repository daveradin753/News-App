package com.newsapplication.mandiri.data.dtos

import com.newsapplication.mandiri.domain.model.SourceModel
import kotlinx.serialization.SerialName

class NewsSource {

    data class Response(
        @SerialName("status")
        val status: String? = null,
        @SerialName("sources")
        val sources: MutableList<SourceModel>? = null,
        @SerialName("code")
        val code: String? = null,
        @SerialName("message")
        val message: String? = null
    )

}