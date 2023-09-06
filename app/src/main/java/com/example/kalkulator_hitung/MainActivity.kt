package com.example.kalkulator_hitung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kalkulator_hitung.databinding.ActivityMainBinding
import android.view.View
import android.widget.Button


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentValue = true
    private var previousValue = ""
    private var addNumber = ""
    private var addOpr = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    // Fungsi untuk mengatur OnClickListener pada tombol angka
    fun inputNumber(view: View) {
        with(binding) {
            if (currentValue) {
                txtMain.text = ""
            }
            val btnClick = txtMain.text.toString() + (view as Button).text.toString()
            txtMain.text = btnClick
            currentValue = false
        }
    }

    // Fungsi untuk mengatur OnClickListener pada tombol operator
    fun inputOpr(view: View) {
        with(binding) {
            currentValue = true
            previousValue = txtMain.text.toString()
            addOpr = (view as Button).text.toString()
            txtOpr.text = previousValue + addOpr
        }
    }

    // Fungsi untuk menghitung hasil
    fun calResult(view: View) {
        with(binding) {
            addNumber = txtMain.text.toString()
            var result = 0.0
            when (addOpr) {
                "/" -> result = previousValue.toDouble() / addNumber.toDouble()
                "*" -> result = previousValue.toDouble() * addNumber.toDouble()
                "+" -> result = previousValue.toDouble() + addNumber.toDouble()
                "-" -> result = previousValue.toDouble() - addNumber.toDouble()
            }
            if (result % 1.0 == 0.0) {
                var res = result.toInt()
                txtMain.text = res.toString()
            } else {
                txtMain.text = result.toString()
            }
            txtOpr.text = txtOpr.text.toString() + addNumber.toString()
        }
    }

    //Fungsi untuk clear kembali ke 0 (AC)
    fun clearCal(view: View) {
        with(binding) {
            currentValue = true
            txtMain.text = "0"
            txtOpr.text = ""
        }
    }

    // Fungsi untuk menghapus satu karakter terakhir pada layar utama
    fun deleteLastCharacter(view: View) {
        with(binding) {
            val currentText = txtMain.text.toString()
            if (currentText.isNotEmpty()) {
                txtMain.text = currentText.substring(0, currentText.length - 1)
            }
        }
    }

    // Fungsi untuk menghitung persen
    fun calculatePercentage(view: View) {
        with(binding) {
            val newValue = txtMain.text.toString().toDouble()
            val result = newValue / 100.0
            txtMain.text = result.toString()
            currentValue = true
        }
    }
}