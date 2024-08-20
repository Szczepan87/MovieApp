package com.example.movieapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountryDTO(
    @SerialName("iso_3166_1")
    val isoName: String,
    @SerialName("name")
    val name: String,
)