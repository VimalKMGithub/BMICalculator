package com.example.combined_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class Notifications : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        val toolbar = findViewById<Toolbar>(R.id.nav)
        toolbar.title="Notifications"
        setSupportActionBar(toolbar)

        val bnv = findViewById<BottomNavigationView>(R.id.bnView)
        bnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                }

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
}