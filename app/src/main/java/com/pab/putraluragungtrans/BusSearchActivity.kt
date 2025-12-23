package com.pab.putraluragungtrans

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BusSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bus_search)

        val actionSearch = findViewById<Button>(R.id.actionSearch)
        val actionBack = findViewById<Button>(R.id.actionBack)

        actionSearch.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            intent.putExtra("TARGET_MENU_ID", R.id.nav_bus)

            startActivity(intent)
            finish()
        }

        actionBack.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

            intent.putExtra("TARGET_MENU_ID", R.id.nav_bus)

            startActivity(intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}