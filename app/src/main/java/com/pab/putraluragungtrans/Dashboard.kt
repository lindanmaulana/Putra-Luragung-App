package com.pab.putraluragungtrans

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Dashboard : AppCompatActivity(), NavigationCallbackDashboard {
    private lateinit var bottomNav: BottomNavigationView
    private val homeFragment: DashboardHomeFragment = DashboardHomeFragment()
    private val busFragment: DashboardBusFragment = DashboardBusFragment()

    private val ticketFragment: DashboardTicketFragment = DashboardTicketFragment()
    private val accountFragment: DashboardAccountFragment = DashboardAccountFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation_view)

        ViewCompat.setOnApplyWindowInsetsListener(bottomNav) { view, windowInsets ->
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                this.bottomMargin = 0
            }

            WindowInsetsCompat.CONSUMED
        }

        if (savedInstanceState == null) {
            replaceFragment(homeFragment)
        }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> replaceFragment(homeFragment)
                R.id.nav_bus -> replaceFragment(busFragment)
                R.id.nav_ticket -> replaceFragment(ticketFragment)
                R.id.nav_account -> replaceFragment(accountFragment)

                // Item placeholder tidak perlu ditangani karena disetel 'enabled="false"'
            }
            true
        }

        handleIntent(intent)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    override fun navigateTo(itemId: Int) {
        when(itemId) {
            R.id.nav_home -> {
                replaceFragment(homeFragment)
                bottomNav.selectedItemId = R.id.nav_home
            }
            R.id.nav_bus -> {
                replaceFragment(busFragment)
                bottomNav.selectedItemId = R.id.nav_bus
            }
            R.id.nav_ticket -> {
                replaceFragment(ticketFragment)
                bottomNav.selectedItemId = R.id.nav_ticket
            }
            R.id.nav_account -> {
                replaceFragment(accountFragment)
                bottomNav.selectedItemId = R.id.nav_account
            }

            else -> {
                replaceFragment(homeFragment)
                bottomNav.selectedItemId = R.id.nav_home
            }
        }
    }

    private fun handleIntent(intent: Intent?) {
        val targetMenuId = intent?.getIntExtra("TARGET_MENU_ID", -1) ?: -1

        if (targetMenuId != -1) {
            navigateTo(targetMenuId)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            // R.id.fragment_container adalah ID dari FrameLayout Anda
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }
}