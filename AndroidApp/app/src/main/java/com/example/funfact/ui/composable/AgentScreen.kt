package com.example.funfact.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.funfact.ui.theme.FunFactTheme
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

@Composable
fun AgentScreen(modifier: Modifier = Modifier) {
    val client = remember { OkHttpClient() }
    var question by remember { mutableStateOf("") }
    var response by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = question,
            onValueChange = { question = it },
            label = { Text("Ask something...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (question.isBlank()) return@Button
                isLoading = true
                response = ""

                val url = "http://10.0.2.2:8000/agent/fact?question=${
                    java.net.URLEncoder.encode(question, "UTF-8")
                }"

                val request = Request.Builder().url(url).build()

                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        android.os.Handler(android.os.Looper.getMainLooper()).post {
                            response = "Error: ${e.message}"
                            isLoading = false
                        }
                    }

                    override fun onResponse(call: Call, res: Response) {
                        val body = res.body?.string() ?: "Empty response"
                        android.os.Handler(android.os.Looper.getMainLooper()).post {
                            response = body
                            isLoading = false
                        }
                    }
                })
            },
            enabled = !isLoading && question.isNotBlank()
        ) {
            Text("Ask the Agent")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (isLoading) CircularProgressIndicator()

        if (response.isNotEmpty()) {
            Text(
                text = response,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AgentScreenPreview() {
    FunFactTheme {
        AgentScreen()
    }
}
