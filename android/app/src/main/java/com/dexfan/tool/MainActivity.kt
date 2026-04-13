package com.dexfan.tool

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val content = TextView(this).apply {
            text = "DeX Fan Tool\nUSB probe skeleton ready."
            textSize = 20f
            setPadding(48, 48, 48, 48)
        }

        setContentView(content)
    }
}
