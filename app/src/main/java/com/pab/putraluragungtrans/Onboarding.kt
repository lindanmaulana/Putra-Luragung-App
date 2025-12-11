package com.pab.putraluragungtrans

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class OnboardingPageAdapter(
    fragmentActivity: FragmentActivity,
    private val pages: List<Fragment> // Daftar Fragment (Halaman Onboarding) Anda
) : FragmentStateAdapter(fragmentActivity) {

    // 1. Metode Wajib: Mengembalikan jumlah total halaman.
    override fun getItemCount(): Int {
        return pages.size
    }

    // 2. Metode Wajib: Membuat dan mengembalikan Fragment untuk posisi tertentu.
    override fun createFragment(position: Int): Fragment {
        return pages[position]
    }
}
class Onboarding : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding)

        val viewPager = findViewById<ViewPager2>(R.id.onBoarding)
        val actionNext = findViewById<Button>(R.id.actionNext)
        val actionBack = findViewById<Button>(R.id.actionBack)
        val actionSkip = findViewById<Button>(R.id.actionSkip)

        val pages = listOf(
            OnboardingPageFragment.newInstance("Selamat Datang di", "Luragung Trans!", "Akses layanan bus dengan mudah langsung dari ponsel Anda cek jadwal, rute, hingga posisi bus.", R.drawable.onboarding_first, R.drawable.onboarding_first_session),
            OnboardingPageFragment.newInstance("Cepat dan Dapat Diandalkan", "Pelacakan Bus","Temukan lokasi bus, cek estimasi kedatangan, dan rencanakan perjalanan tanpa khawatir.",  R.drawable.onboarding_second, R.drawable.onboarding_second_session),
            OnboardingPageFragment.newInstance("Pembayaran yang", "Mudah", "Akses layanan bus dengan mudah langsung dari ponsel Anda—cek jadwal, rute, hingga posisi bus.",  R.drawable.onboarding_end, R.drawable.onboarding_end_session)
        )

        val adapter = OnboardingPageAdapter(this, pages)
        viewPager.adapter = adapter

        actionNext.setOnClickListener {
            val currentItem = viewPager.currentItem

            if (currentItem < adapter.itemCount - 1) {
                viewPager.setCurrentItem(currentItem + 1, true)
            } else {
                val intent = Intent(this, SigninAuth::class.java)
                startActivity(intent)
            }
        }

        actionBack.setOnClickListener {
            val currentItem = viewPager.currentItem

            if (currentItem > 0) {
                viewPager.setCurrentItem(currentItem - 1, true)
            } else {
                onBackPressedDispatcher.onBackPressed()
            }
        }

        actionSkip.setOnClickListener {
            val intent = Intent(this, SigninAuth::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

