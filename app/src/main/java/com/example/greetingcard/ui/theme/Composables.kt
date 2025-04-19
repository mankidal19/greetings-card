package com.example.greetingcard.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greetingcard.R
import com.example.greetingcard.data.Message

@Composable
fun MessageCard(
    message: Message,
    modifier: Modifier = Modifier,
    onFeedButtonClick: (() -> Unit)? = null
) {
    Row {
        Image(
            painter = painterResource(R.drawable.mimi),
            contentDescription = "Mimi",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(48.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Spacer(modifier = modifier.width(8.dp))
        Column {
            Text(
                text = message.author,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = modifier.height(4.dp))
            Text(
                text = message.body,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        Spacer(modifier = modifier.width(8.dp))
        Button(onClick = {
            onFeedButtonClick?.invoke()
        }) {
            Text(
                text = "Feed ${message.author}"
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(modifier = Modifier.padding(horizontal = 16.dp), color = Color.Magenta) {
        Text(
            text = "Hello my name is $name!",
            modifier = modifier
                .padding(24.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingCardTheme {
        Greeting("Meow")
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun MessageCardPreview() {
    GreetingCardTheme {
        Surface {
            MessageCard(Message("Aiman", "I am hangryyyyyy"))
        }
    }
}