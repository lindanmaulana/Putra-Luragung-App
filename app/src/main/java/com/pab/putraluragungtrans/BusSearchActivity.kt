package com.pab.putraluragungtrans

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class BusSearchActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bus_search)

        val actionSearch = findViewById<MaterialButton>(R.id.actionSearch)
        val actionBack = findViewById<MaterialButton>(R.id.actionBack)

        actionSearch.setOnClickListener {
            navigateTo(Dashboard::class.java, R.id.nav_bus)
        }

        actionBack.setOnClickListener {
            navigateTo(Dashboard::class.java, R.id.nav_home)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}