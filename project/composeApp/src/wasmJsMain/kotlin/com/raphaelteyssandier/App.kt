package com.raphaelteyssandier

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.*
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import kotlinx.browser.window
import org.jetbrains.compose.resources.painterResource

import raphaelteyssandier.composeapp.generated.resources.Res
import raphaelteyssandier.composeapp.generated.resources.compose_multiplatform

@Composable
fun App() {
    MaterialTheme {
        Content()
    }
}

@Composable
fun Content() {
    val windowInfo = currentWindowAdaptiveInfo()
    val layoutType = with(windowInfo) {
        if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
            NavigationSuiteType.NavigationDrawer
        } else {
            calculateFromAdaptiveInfo(windowInfo)
        }
    }

    NavigationSuiteScaffoldLayout(
        navigationSuite = {
            NavigationSuite(
                layoutType = layoutType,
                modifier = Modifier
                    .shadow(8.dp)
                    .fillMaxHeight()
            ) {
                item(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Abc,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Raphael ?")
                    }
                )
            }
        },
        layoutType = layoutType
    ) {
        Test()
    }
}

@Composable
private fun Test() {
    var showContent by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { showContent = !showContent }) {
            Text("Click me!")
        }
        AnimatedVisibility(showContent) {
            val greeting = remember { Greeting().greet() }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: $greeting")
            }
        }
    }
}