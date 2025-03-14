package com.example.entregadeqta

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var celsiusText: TextView
    private lateinit var fahrenheitText: TextView
    private lateinit var kelvinText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editTextText2)
        celsiusText = findViewById(R.id.textView6)
        fahrenheitText = findViewById(R.id.textView8)
        kelvinText = findViewById(R.id.textView9)

        val celsiusButton: Button = findViewById(R.id.Celsi)
        val fahrenheitButton: Button = findViewById(R.id.Faren)
        val kelvinButton: Button = findViewById(R.id.Kelv)

        celsiusButton.setOnClickListener { convertFromCelsius() }
        fahrenheitButton.setOnClickListener { convertFromFahrenheit() }
        kelvinButton.setOnClickListener { convertFromKelvin() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun convertFromCelsius() {
        val value = editText.text.toString().toDoubleOrNull()
        if (value != null) {
            celsiusText.text = String.format("%.0f °C", value)
            fahrenheitText.text = String.format("%.0f °F", celsiusToFahrenheit(value))
            kelvinText.text = String.format("%.0f K", celsiusToKelvin(value))
        } else {
            showError()
        }
    }

    private fun convertFromFahrenheit() {
        val value = editText.text.toString().toDoubleOrNull()
        if (value != null) {
            celsiusText.text = String.format("%.0f °C", fahrenheitToCelsius(value))
            fahrenheitText.text = String.format("%.0f °F", value)
            kelvinText.text = String.format("%.0f K", fahrenheitToKelvin(value))
        } else {
            showError()
        }
    }

    private fun convertFromKelvin() {
        val value = editText.text.toString().toDoubleOrNull()
        if (value != null) {
            celsiusText.text = String.format("%.0f °C", kelvinToCelsius(value))
            fahrenheitText.text = String.format("%.0f °F", kelvinToFahrenheit(value))
            kelvinText.text = String.format("%.0f K", value)
        } else {
            showError()
        }
    }

    private fun showError() {
        celsiusText.text = "Error"
        fahrenheitText.text = "Error"
        kelvinText.text = "Error"
    }

    private fun celsiusToFahrenheit(celsius: Double): Double {
        return (celsius * 9 / 5) + 32
    }

    private fun celsiusToKelvin(celsius: Double): Double {
        return celsius + 273.15
    }

    private fun fahrenheitToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9
    }

    private fun fahrenheitToKelvin(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9 + 273.15
    }

    private fun kelvinToCelsius(kelvin: Double): Double {
        return kelvin - 273.15
    }

    private fun kelvinToFahrenheit(kelvin: Double): Double {
        return (kelvin - 273.15) * 9 / 5 + 32
    }
}