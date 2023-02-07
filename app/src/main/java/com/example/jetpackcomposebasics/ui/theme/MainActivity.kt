package com.example.jetpackcomposebasics.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
    var showOnBoarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier = modifier) {
        if (showOnBoarding) {
            OnboardingScreen(onContinueClicked = { showOnBoarding = false })
        } else MyAppColumn()
    }
}

//@Composable
//fun MyAppColumn(
//    modifier: Modifier = Modifier,
//    names: List<String> = listOf("Compose", "World")
//) {
//    Column(
//        modifier = modifier
//            .padding(vertical = 4.dp, horizontal = 8.dp)
//            .fillMaxSize()
//    ) {
//        for (item in names) {
//            Greeting2(name = item)
//        }
//    }
//}

@Composable
fun MyAppColumn(
    modifier: Modifier = Modifier,
    names: List<String> = List(500) { "$it" }
) {
    LazyColumn(
        modifier = modifier
            .padding(vertical = 4.dp)
    ) {
        items(items = names) { name ->
            Greeting2(name = name)
        }
    }
}

@Composable
fun Greeting2(name: String) {
    //use mutableStateof in compose
    //use remember so it doesn't reset on every recomposition
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded) 50.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello ")
                Text(text = "$name!")
            }
            ElevatedButton(onClick = { expanded = !expanded }) {
                Text(text = if (expanded) "Show less" else "Show more")
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