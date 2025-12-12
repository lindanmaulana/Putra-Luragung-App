package com.pab.putraluragungtrans

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
    private val searchFragment: DashboardSearchFragment = DashboardSearchFragment()

    private val invoiceFragment: DashboardInvoiceFragment = DashboardInvoiceFragment()
    private val profileFragment: DashboardProfileFragment = DashboardProfileFragment()


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
                R.id.nav_search -> replaceFragment(searchFragment)
                R.id.nav_invoice -> replaceFragment(invoiceFragment)
                R.id.nav_profile -> replaceFragment(profileFragment)

                // Item placeholder tidak perlu ditangani karena disetel 'enabled="false"'
            }
            true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun navigateTo(itemId: Int) {
        when(itemId) {
            R.id.nav_home -> replaceFragment(homeFragment)
            R.id.nav_search -> {
                // Saat tombol diklik, ganti Fragment
                replaceFragment(searchFragment)

                // Opsional: Setel item Bottom Nav Bar yang sesuai menjadi terpilih
                bottomNav.selectedItemId = R.id.nav_search
            }
//            R.id.nav_invoice -> replaceFragment(invoiceFragment)
//            R.id.nav_profile -> replaceFragment(profileFragment)
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