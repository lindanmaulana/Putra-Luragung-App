package com.pab.putraluragungtrans

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class BookingPaymentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_payment)

        val actionCheckStatus = findViewById<MaterialButton>(R.id.actionCheckStatus)
        val actionBack = findViewById<MaterialButton>(R.id.actionBack)

        actionCheckStatus.setOnClickListener {
            navigateTo(TicketStruckActivity::class.java)
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