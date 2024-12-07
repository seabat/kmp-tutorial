package dev.seabat.kmp.tutorial

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.seabat.kmp.tutorial.shared.viewmodel.GreetingViewModel

@Composable
@Preview
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val rocketLaunchPhrase by homeViewModel.rocketLaunchPhrase.collectAsStateWithLifecycle()
    var showContent by remember { mutableStateOf(false) }
    val grepResult by homeViewModel.grepResult.collectAsStateWithLifecycle()

    val viewModel = remember { GreetingViewModel() }
    val phrases by viewModel.phrases.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadPhrases()
    }

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
                phrases.forEach { phrase ->
                    Text(phrase)
                    HorizontalDivider()
                }
                Text(rocketLaunchPhrase)
                Text(grepResult.toString())
            }
        }
    }
}