package com.neirasphere.ecosphere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.neirasphere.ecosphere.presentation.EcoSphereApp
import com.neirasphere.ecosphere.presentation.SetStatusBarColor
import com.neirasphere.ecosphere.ui.theme.EcoSphereProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoSphereProjectTheme {
                // A surface container using the 'background' color from the theme
                SetStatusBarColor(color = Color.White)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   EcoSphereApp()
                }
            }
        }
    }
}