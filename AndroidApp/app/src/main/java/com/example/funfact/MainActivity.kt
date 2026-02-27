package com.example.funfact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.funfact.ui.composable.AgentScreen
import com.example.funfact.ui.theme.FunFactTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunFactTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AgentScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}