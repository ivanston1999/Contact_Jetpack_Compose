package com.capstone.contact.main
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.capstone.contact.ui.theme.ContactTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Contact()
                }
            }
        }
    }
}