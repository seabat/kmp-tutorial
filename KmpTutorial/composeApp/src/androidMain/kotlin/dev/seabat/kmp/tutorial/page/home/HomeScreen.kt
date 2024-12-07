package dev.seabat.kmp.tutorial.page.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.seabat.kmp.tutorial.R

@Composable
@Preview
fun HomeScreen(
    goToGreeting: (String) -> Unit = {},
    goToGrep: () -> Unit = {},
    goToRocketLaunch: () -> Unit = {}
) {
    var showContent by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { showContent = !showContent }) {
            Text("Click me!")
        }
        AnimatedVisibility(showContent) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painterResource(R.drawable.compose_multiplatform), null)
                Button(onClick = { goToGreeting("HOMEから遷移") }) {
                    Text("Go to Greeting")
                }
                Button(onClick = { goToGrep() }) {
                    Text("Go to Grep")
                }
                Button(onClick = { goToRocketLaunch() }) {
                    Text("Go to Rocket Launch")
                }
            }
        }
    }
}
