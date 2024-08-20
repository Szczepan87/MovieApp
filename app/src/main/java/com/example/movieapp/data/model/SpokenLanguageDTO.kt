package com.example.movieapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguageDTO(
    @SerialName("english_name")
    val englishName: String,
    @SerialName("iso_639_1")
    val isoName: String,
    @SerialName("name")
    val name: String,
)
