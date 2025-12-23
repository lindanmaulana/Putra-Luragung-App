package com.pab.putraluragungtrans

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingScreen : AppCompatActivity() {
    private val SPLASH_TIME_LOAD: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loading_screen)

        val session = SessionManager(this)

        lifecycleScope.launch {
            delay(SPLASH_TIME_LOAD)

            val intent = when {
                session.isLoggedIn() -> {
                    Intent(this@LoadingScreen, Dashboard::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        putExtra("TARGET_MENU_ID", R.id.nav_home)
                    }
                }

                else -> Intent(this@LoadingScreen, MainActivity::class.java)
            }

            startActivity(intent)
            finish()
        }
    }
}