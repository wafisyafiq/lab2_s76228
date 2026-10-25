package com.example.lab2_s76228

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editNama = findViewById<EditText>(R.id.editNama)
        val editTarikh = findViewById<EditText>(R.id.editTarikh)
        val btnAnalisis = findViewById<Button>(R.id.btnAnalisis)

        btnAnalisis.setOnClickListener {
            val name = editNama.text.toString().trim()
            val dob = editTarikh.text.toString().trim()

            if (name.isNotEmpty() && dob.isNotEmpty()) {
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra("NAMA", name)
                    putExtra("TARIKH", dob)
                }
                startActivity(intent)
            } else {
                editNama.error = "Required"
            }
        }
    }
}