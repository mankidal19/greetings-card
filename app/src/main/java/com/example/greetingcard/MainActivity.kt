package com.example.greetingcard

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.greetingcard.data.Bill
import com.example.greetingcard.data.Message
import com.example.greetingcard.presentation.view.BillWebViewScreen
import com.example.greetingcard.ui.theme.GreetingCardTheme
import com.example.greetingcard.presentation.view.MessageCard
import com.example.greetingcard.presentation.viewmodel.BillPlzViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingCardTheme {
                val viewModel = remember { BillPlzViewModel() }
                val billUrl by viewModel.billUrl.collectAsState()

                var showProgressBar by remember { mutableStateOf(false) }
                var billAmount by remember { mutableIntStateOf(0) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        val currentContext = LocalContext.current
                        billUrl?.let { currentBillUrl ->
                            BillWebViewScreen(
                                billUrl = currentBillUrl,
                                onPaymentCompleted = { message ->
                                    Toast.makeText(
                                        currentContext,
                                        "$message - Pending for confirmation.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    showProgressBar = false
                                    viewModel.clearBillUrl()
                                },
                                onBackPressed = {
                                    showProgressBar = false
                                    viewModel.clearBillUrl()
                                }
                            )
                        } ?: run {
                            if (showProgressBar) {
                                // Loading indicator while waiting for the bill URL.
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    CircularProgressIndicator()
                                }
                            } else {
                                MessageCard(
                                    message = Message(
                                        "Mimi",
                                        "Meeeeowwwwwwwwww"
                                    ),
                                    onFeedButtonClick = { amount ->
                                        billAmount = amount
                                        viewModel.createBill(
                                            Bill(
                                                "meowmeow@test.com",
                                                "Aiman",
                                                billAmount // in MYR cents
                                            )
                                        )
                                        showProgressBar = true
                                    }
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}