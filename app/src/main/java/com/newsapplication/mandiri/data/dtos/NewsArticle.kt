package com.newsapplication.mandiri.data.dtos
import com.google.gson.annotations.SerializedName
import com.newsapplication.mandiri.domain.model.ArticleModel


class NewsArticle {

    data class Response(
        @SerializedName("code")
        var code: String? = null,
        @SerializedName("message")
        var message: String? = null,
        @SerializedName("status")
        var status: String? = null,
        @SerializedName("articles")
        var articles: MutableList<ArticleModel>? = null,
    )


}