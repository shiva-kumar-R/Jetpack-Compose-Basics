package com.example.jetpackcomposebasics.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.ui.theme.ui.theme.JetpackComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeBasicsTheme {
                // A surface container using the 'background' color from the theme
                //MyApp(modifier = Modifier.fillMaxSize())
                MyAppColumn()
            }
        }
    }
}

/**
 * Default modifier passed
 */
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary
    ) {
        Greeting2(name = "Android")
    }
}

@Composable
fun MyAppColumn(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("Compose", "World")
) {
    Column(
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxSize()
    ) {
        for (item in names) {
            Greeting2(name = item)
        }
    }
}

@Composable
fun Greeting2(name: String) {
    //use mutableStateof in compose
    //use remember so it doesn't reset on every recomposition
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 40.dp else 0.dp

    Surface(color = MaterialTheme.colorScheme.primary) {
        Row(modifier = Modifier.padding(20.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello ")
                Text(text = "$name!")
            }
            ElevatedButton(onClick = { expanded.value = !expanded.value }) {
                Text(text = if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    JetpackComposeBasicsTheme {
        //MyApp()
        MyAppColumn()
    }
}