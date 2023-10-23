package com.example.tugasmodule9.data.model

import com.google.firebase.firestore.FieldValue
import java.util.Date

data class Movie(
    val poster: String?,
    val title: String?,
    val rating: Float?,
    val popularity: Float?,
    val overview: String?,
    val createdAt: Date
)
