package dev.seabat.kmp.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.seabat.kmp.tutorial.page.greeting.GreetingScreen
import dev.seabat.kmp.tutorial.page.grep.GrepScreen
import dev.seabat.kmp.tutorial.page.home.HomeScreen
import dev.seabat.kmp.tutorial.page.rocketlaunch.RocketLaunchScreen
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Home
                ) {
                    composable<Home> {
                        HomeScreen(
                            goToGreeting = { grepResult ->
                                navController.navigate(Greeting(name = grepResult, id = 10))
                            },
                            goToGrep = {
                                navController.navigate(Grep)
                            },
                            goToRocketLaunch = {
                                navController.navigate(RocketLaunch)
                            }
                        )
                    }
                    composable<Greeting> {
                        val args = it.toRoute<Greeting>()
                        GreetingScreen(
                            name = args.name,
                            id = args.id,
                            goBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable<Grep> {
                        GrepScreen(
                            goBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable<RocketLaunch> {
                        RocketLaunchScreen(
                            goBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Serializable
object Home

@Serializable
data class Greeting(
    val name: String,
    val id: Int,
)

@Serializable
object Grep

@Serializable
object RocketLaunch

@Preview
@Composable
fun AppAndroidPreview() {
    HomeScreen()
}
