package com.example.lab2_s76228

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class ResultActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val textNama = findViewById<TextView>(R.id.textNama)
        val textTarikh = findViewById<TextView>(R.id.textTarikh)

        val name = intent.getStringExtra("NAMA") ?: ""
        val dobString = intent.getStringExtra("TARIKH") ?: ""

        // Part A: Name Analysis
        val nameNoSpace = name.replace(" ", "")
        val charCount = nameNoSpace.length
        val vowelsCount = name.filter { it.lowercaseChar() in "aeiou" }.count()

        val repeatedChars = nameNoSpace
            .groupBy { it.lowercaseChar() }
            .filter { it.value.size > 1 }
            .keys
            .joinToString(", ")

        textNama.text = "Nama: $name\n" +
                "Jumlah abjad: $charCount\n" +
                "Bilangan vokal: $vowelsCount\n" +
                "Abjad berulang: $repeatedChars"

        // Part B: Date Analysis
        try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val birthDate = LocalDate.parse(dobString, formatter)
            val currentDate = LocalDate.now()

            val age = Period.between(birthDate, currentDate).years
            val dayOfWeek = birthDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("ms", "MY"))

            textTarikh.text = "Umur anda: $age tahun\n" +
                    "Dilahirkan pada: $dayOfWeek"
        } catch (e: Exception) {
            textTarikh.text = "Format tarikh tidak sah. Gunakan yyyy-MM-dd."
        }
    }
}