package com.newsapplication.mandiri.domain.model
import com.google.gson.annotations.SerializedName

data class SourceModel(
    @SerializedName("category")
    var category: String?,
    @SerializedName("country")
    var country: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("language")
    var language: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("url")
    var url: String?
)

