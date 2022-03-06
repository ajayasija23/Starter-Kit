package com.app.starterkit.model

data class ApiResponse(
    val `data`: Data,
    val message: String,
    val success: Boolean
)