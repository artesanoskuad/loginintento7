package com.artesanoskuad.loginintento7.authentication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario(
    @PrimaryKey
    val usuario: String,
    val nombreCompleto: String,
    val password: String
)
