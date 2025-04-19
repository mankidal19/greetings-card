package com.example.greetingcard

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.greetingcard.data.Bill
import com.example.greetingcard.data.Message
import com.example.greetingcard.presentation.view.BillWebViewScreen
import com.example.greetingcard.ui.theme.GreetingCardTheme
import com.example.greetingcard.presentation.view.MessageCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingCardTheme {
                var showBillWebView by remember { mutableStateOf(false) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        val currentContext = LocalContext.current
                        if (showBillWebView) {
                            BillWebViewScreen(
                                bill = Bill(
                                    "man.kidal19@gmail.com",
                                    "Aiman",
                                    1001 // in MYR cents
                                ),
                                onPaymentCompleted = { message ->
                                    Toast.makeText(
                                        currentContext,
                                        message, Toast.LENGTH_SHORT
                                    ).show()
                                    showBillWebView = false
                                }
                            )
                        } else {
                            MessageCard(
                                message = Message(
                                    "Mimi",
                                    "Meeeeowwwwwwwwww"
                                ),
                                onFeedButtonClick = {
                                    showBillWebView = true
                                    // Toast.makeText(currentContext, "Feeding in progress!", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }

                    }
                }
            }
        }
    }
}