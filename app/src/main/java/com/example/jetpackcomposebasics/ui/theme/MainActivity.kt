package com.example.jetpackcomposebasics.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
                MyApp2(modifier = Modifier.fillMaxSize())
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
fun MyApp2(modifier: Modifier = Modifier) {
    var showOnBoarding by remember { mutableStateOf(true) }

    Surface(modifier = modifier) {
        if (showOnBoarding) {
            OnboardingScreen(onContinueClicked = { showOnBoarding = false })
        } else MyAppColumn()
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

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to Jetpack Basics")
        Button(
            modifier = Modifier.padding(20.dp),
            onClick = onContinueClicked
        ) {
            Text(text = "Continue")
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

@Preview(showBackground = true)
@Composable
fun DefaultPreviewOnboardingScreen() {
    JetpackComposeBasicsTheme {
        //MyApp()
        OnboardingScreen(onContinueClicked = {})
    }
}