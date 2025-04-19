package com.example.greetingcard.di.api

import com.example.greetingcard.data.request.BillRequest
import com.example.greetingcard.data.response.BillResponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by mankidal on 19/04/2025.
 */

interface BillPlzApi {

    @POST("v3/bills")
    suspend fun createBill(@Body request: BillRequest): BillResponse
}