package com.example.movieapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BelongsToCollectionDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("name")
    val name: String,
    @SerialName("poster_path")
    val posterResourceName: String?,
)
