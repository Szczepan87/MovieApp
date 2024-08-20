package com.example.movieapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompanyDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("logo_path")
    val logoResourceName: String?,
    @SerialName("name")
    val name: String,
    @SerialName("origin_country")
    val originCountry: String,
)
