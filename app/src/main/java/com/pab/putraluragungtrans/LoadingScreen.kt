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
    private val SPLASH_TIME_LOAD: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loading_screen)

        val session = SessionManager(this)

        lifecycleScope.launch {
            delay(SPLASH_TIME_LOAD)

            when {
                session.isLoggedIn() -> {
                    navigateTo(Dashboard::class.java, R.id.nav_home)
                }

                else -> {
                    navigateTo(Onboarding::class.java)
                }
            }
        }
    }
}