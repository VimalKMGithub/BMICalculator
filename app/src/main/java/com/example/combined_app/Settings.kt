package com.example.combined_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val toolbar = findViewById<Toolbar>(R.id.nav)
        toolbar.title="Settings"
        setSupportActionBar(toolbar)

        val bnv = findViewById<BottomNavigationView>(R.id.bnView)
        bnv.selectedItemId=R.id.settings
        bnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home->{
                    startActivity(Intent(this,MainActivity::class.java))
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
                startActivity(Intent(this,Notifications::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}