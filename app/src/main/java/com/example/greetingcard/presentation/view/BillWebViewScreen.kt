package com.example.greetingcard.presentation.view

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greetingcard.data.CreateBill
import com.example.greetingcard.presentation.viewmodel.BillPlzViewModel

/**
 * Created by mankidal on 19/04/2025.
 */

@Composable
fun BillWebViewScreen(
    createBill: CreateBill,
    viewModel: BillPlzViewModel = viewModel()
) {
    val billUrl = viewModel.billUrl.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.createBill(
            email = createBill.email,
            name = createBill.name,
            amountInCents = createBill.amountInCents
        )
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        val currentContext = LocalContext.current
        billUrl?.let { url ->
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        webViewClient = object : WebViewClient() {
                            override fun shouldOverrideUrlLoading(
                                view: WebView?,
                                request: WebResourceRequest?
                            ): Boolean {
                                val currentUrl = request?.url.toString()
                                if (currentUrl.contains(BillPlzViewModel.REDIRECT_URL)) {
                                    Toast
                                        .makeText(
                                            context,
                                            "Thank you for your payment!",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                                return false
                            }
                        }
                        // Using setJavaScriptEnabled can
                        // introduce XSS vulnerabilities
                        // into your application
                        settings.javaScriptEnabled = true
                        loadUrl(url)
                    }
                }, modifier = Modifier.fillMaxSize()
            )
        } ?: run {
            // Loading indicator while waiting for the bill URL.
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}