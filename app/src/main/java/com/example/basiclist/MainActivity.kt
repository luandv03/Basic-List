package com.example.basiclist

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val listView = findViewById<ListView>(R.id.listView)
        val textViewError = findViewById<TextView>(R.id.textViewError)

        buttonShow.setOnClickListener {
            val input = editTextNumber.text.toString()
            if (input.isEmpty()) {
                textViewError.text = "Please enter a number"
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null || n <= 0) {
                textViewError.text = "Invalid number. Please enter a positive integer."
                return@setOnClickListener
            }

            textViewError.text = ""
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val numbers = when (selectedRadioButtonId) {
                R.id.radioEven -> getEvenNumbers(n)
                R.id.radioOdd -> getOddNumbers(n)
                R.id.radioSquare -> getSquareNumbers(n)
                else -> emptyList()
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listView.adapter = adapter
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        return (0..n).filter { Math.sqrt(it.toDouble()).toInt() * Math.sqrt(it.toDouble()).toInt() == it }
    }
}