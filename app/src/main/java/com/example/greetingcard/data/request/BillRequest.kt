package com.example.greetingcard.data.request

import com.squareup.moshi.Json

data class BillRequest(
    @Json(name = "collection_id")
    val collectionId: String,
    val email: String,
    val name: String,
    val amount: Int,
    @Json(name = "callback_url")
    val callbackUrl: String,
    @Json(name = "redirect_url")
    val redirectUrl: String,
    val description: String
)
