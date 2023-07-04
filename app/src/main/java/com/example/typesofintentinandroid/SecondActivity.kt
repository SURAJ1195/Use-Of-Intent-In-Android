package com.example.typesofintentinandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.example.typesofintentinandroid.ui.theme.TypesOfIntentInAndroidTheme

class SecondActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TypesOfIntentInAndroidTheme {
                Text(text = "Second Activity")
            }
        }
    }
}