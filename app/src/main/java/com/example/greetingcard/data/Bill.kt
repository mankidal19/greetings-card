package com.example.greetingcard.data

/**
 * Created by mankidal on 19/04/2025.
 */

data class Bill(
    val email: String,
    val name: String,
    val amountInCents: Int,
    val description: String = "Fed by $name"
)
