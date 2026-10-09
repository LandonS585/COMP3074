package com.example.messageapp

import com.example.messageapp.ui.theme.MessageAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

// one message
data class Message(val author: String, val body: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // use the theme name from your project
            MessageAppTheme {
                MessageList(getMessages())
            }
        }
    }
}

fun getMessages(): List<Message> {
    val list = mutableListOf<Message>()
    for (i in 1..3) {
        list.add(Message("Joe", "Hi"))
        list.add(Message("John", "How are you?"))
        list.add(Message("Joe", "This is a test."))
        list.add(Message("John", "I hate coding!"))
    }
    return list
}

@Composable
fun MessageList(messages: List<Message>) {
    LazyColumn(modifier = Modifier.padding(top = 40.dp)) {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

// custom composable for one message
@Composable
fun MessageCard(message: Message) {
    Row(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "profile picture",
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = message.author)
            Text(text = message.body)
        }
    }
}