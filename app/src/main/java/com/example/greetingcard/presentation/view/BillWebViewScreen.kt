package com.example.greetingcard.presentation.view

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greetingcard.presentation.viewmodel.BillPlzViewModel

/**
 * Created by mankidal on 19/04/2025.
 */

@Composable
fun BillWebViewScreen(
    billUrl: String,
    onPaymentCompleted: (String) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: BillPlzViewModel = viewModel()
) {
    BackHandler(onBack = onBackPressed)

    Surface(modifier = Modifier.fillMaxSize()) {
        billUrl.let { url ->
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
                                    onPaymentCompleted.invoke("Thank you!")
                                    return true // prevent loading
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
        }
    }
}