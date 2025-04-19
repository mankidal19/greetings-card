package com.example.greetingcard.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greetingcard.BuildConfig
import com.example.greetingcard.data.request.BillRequest
import com.example.greetingcard.di.api.NetworkModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.logging.Logger

/**
 * Created by mankidal on 19/04/2025.
 */

class BillPlzViewModel: ViewModel() {

    private val CALLBACK_URL = "https://your-backend.com/callback"
    private val REDIRECT_URL = "https://your-app.com/redirect"
    private val LOGGER = Logger.getLogger(BillPlzViewModel::class.java.name)
    /*
    * Why StateFlow instead of LiveData?
    * 1. Jetpack Compose is designed to work with Flows (especially StateFlow)
    * 2. StateFlow is more Kotlin-native and predictable
    *    - LiveData was designed for XML-based UI (pre-Compose)
    *    - StateFlow is Kotlin-first, fully backed by coroutines,
    *      and behaves predictably (i.e., always has a value, no race conditions)
    * */
    private val _billUrl = MutableStateFlow<String?>(null)
    val billUrl: StateFlow<String?> = _billUrl

    fun createBill(
        email: String,
        name: String,
        amountInCents: Int,
        description: String = ""
    ) {
        val request = BillRequest(
            collectionId = BuildConfig.BILLPLZ_COLLECTION_ID,
            email = email,
            name = name,
            amount = amountInCents,
            callbackUrl = CALLBACK_URL,
            redirectUrl = REDIRECT_URL,
            description = description
        )

        viewModelScope.launch {
            try {
                val resp = NetworkModule.api.createBill(request)
                _billUrl.value = resp.url
            } catch (e: Exception) {
                LOGGER.severe("Error: ${e.message}")
                _billUrl.value = null
            }
        }
    }
}