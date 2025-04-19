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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.greetingcard.data.Message
import com.example.greetingcard.ui.theme.GreetingCardTheme
import com.example.greetingcard.ui.theme.MessageCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        val currentContext = LocalContext.current
                        MessageCard(
                            message = Message(
                                "Mimi",
                                "Meeeeowwwwwwwwww"
                            ),
                            onFeedButtonClick = {
                                Toast.makeText(currentContext, "Feeding in progress!", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }
    }
}