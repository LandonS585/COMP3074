package com.example.lab2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var count = 0
    private var step = 1
    private lateinit var countText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countText = findViewById(R.id.countText)
        val addButton = findViewById<Button>(R.id.addButton)
        val subtractButton = findViewById<Button>(R.id.subtractButton)
        val resetButton = findViewById<Button>(R.id.resetButton)
        val stepButton = findViewById<Button>(R.id.stepButton)

        addButton.setOnClickListener {
            count += step
            updateCount()
        }

        subtractButton.setOnClickListener {
            count -= step
            updateCount()
        }

        resetButton.setOnClickListener {
            count = 0
            step = 1
            updateCount()
        }

        stepButton.setOnClickListener {
            step = 2
        }
    }

    private fun updateCount() {
        countText.text = count.toString()
    }
}