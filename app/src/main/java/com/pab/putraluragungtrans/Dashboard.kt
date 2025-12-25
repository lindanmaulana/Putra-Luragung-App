package com.pab.putraluragungtrans

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Dashboard : BaseActivity(), NavigationCallbackDashboard {
    private lateinit var bottomNav: BottomNavigationView
    private val homeFragment by lazy { DashboardHomeFragment() }
    private val busFragment by lazy { DashboardBusFragment() }
    private val ticketFragment by lazy { DashboardTicketFragment() }
    private val accountFragment by lazy { DashboardAccountFragment() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (bottomNav.selectedItemId != R.id.nav_home) {
                    updateDashboardMenu(R.id.nav_home)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                    isEnabled = true
                }
            }
        })
        ViewCompat.setOnApplyWindowInsetsListener(bottomNav) { view, windowInsets ->
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                this.bottomMargin = 0
            }

            WindowInsetsCompat.CONSUMED
        }

        if (savedInstanceState == null) {
            replaceFragmentDashboard(R.id.fragmentContainer, homeFragment)
        }

        bottomNav.setOnItemSelectedListener { item ->
            updateDashboardMenu(item.itemId)
            true
        }

        handleIntent(intent)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun updateDashboardMenu(itemId: Int) {
        if (bottomNav.selectedItemId == itemId && supportFragmentManager.findFragmentById(R.id.fragmentContainer) != null) {
            return
        }

        val targetFragment = when(itemId) {
            R.id.nav_home -> homeFragment
            R.id.nav_bus -> busFragment
            R.id.nav_ticket -> ticketFragment
            R.id.nav_account -> accountFragment
            else -> homeFragment
        }

        replaceFragmentDashboard(R.id.fragmentContainer, targetFragment)
        if (bottomNav.selectedItemId != itemId) {
            bottomNav.setOnItemSelectedListener(null)

            bottomNav.selectedItemId = itemId

            bottomNav.setOnItemSelectedListener { item ->
                updateDashboardMenu(item.itemId)
                true
            }
        }
    }

    override fun navigateTo(itemId: Int) {
        updateDashboardMenu(itemId)
    }
    private fun handleIntent(intent: Intent?) {
        val targetMenuId = intent?.getIntExtra("TARGET_MENU_ID", -1) ?: -1
        if (targetMenuId != -1) {
            updateDashboardMenu(targetMenuId)
        }
    }
}