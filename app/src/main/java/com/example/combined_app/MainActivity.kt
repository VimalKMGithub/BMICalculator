package com.example.combined_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var etHeight: EditText
    private lateinit var etWeight: EditText
    private lateinit var calculateBtn: Button
    private lateinit var reCalculateBtn: Button
    private lateinit var bmiTextView: TextView
    private lateinit var bmiResultTextView: TextView
    private lateinit var statusTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.nav)
        toolbar.title="Home"
        setSupportActionBar(toolbar)
        
        etHeight = findViewById(R.id.etHeight)
        etWeight = findViewById(R.id.etWeight)
        calculateBtn = findViewById(R.id.calculate_btn)
        reCalculateBtn = findViewById(R.id.ReCalculate)
        bmiTextView = findViewById(R.id.bmi_tv)
        bmiResultTextView = findViewById(R.id.bmi)
        statusTextView = findViewById(R.id.status)

        calculateBtn.setOnClickListener {
            if (etHeight.text.isNotEmpty() && etWeight.text.isNotEmpty()) {
                hideKeyboard()
                val height = etHeight.text.toString().toInt()
                val weight = etWeight.text.toString().toInt()
                val bmi = calculateBMI(height, weight)

                bmiResultTextView.text = bmi.toString()
                bmiResultTextView.visibility = View.VISIBLE

                if (bmi < 18.5) {
                    statusTextView.text = "Under Weight"
                } else if (bmi >= 18.5 && bmi < 24.9) {
                    statusTextView.text = "Healthy"
                } else if (bmi >= 24.9 && bmi < 30) {
                    statusTextView.text = "Overweight"
                } else if (bmi >= 30) {
                    statusTextView.text = "Suffering from Obesity"
                }

                bmiTextView.visibility = View.VISIBLE
                statusTextView.visibility = View.VISIBLE

                reCalculateBtn.visibility = View.VISIBLE
                calculateBtn.visibility = View.GONE

            } else {
                Toast.makeText(this, "Please enter valid height and weight", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        reCalculateBtn.setOnClickListener {
            resetEverything()
        }

        val bnv = findViewById<BottomNavigationView>(R.id.bnView)
        bnv.selectedItemId = R.id.home
        bnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.settings -> {
                    startActivity(Intent(this, Settings::class.java))
                }

                R.id.exit -> {
                    finishAffinity()
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.head_view, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.notifications -> {
                startActivity(Intent(this, Notifications::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun resetEverything() {
        calculateBtn.visibility = View.VISIBLE
        reCalculateBtn.visibility = View.GONE

        etHeight.text.clear()
        etWeight.text.clear()
        statusTextView.text = ""
        bmiResultTextView.text = ""
        bmiTextView.visibility = View.GONE
    }

    private fun calculateBMI(height: Int, weight: Int): Float {
        val heightInMeter = height.toFloat() / 100
        return weight.toFloat() / (heightInMeter * heightInMeter)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view: View? = currentFocus
        view?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}