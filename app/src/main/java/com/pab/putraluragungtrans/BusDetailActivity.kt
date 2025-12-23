package com.pab.putraluragungtrans

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BusDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bus_detail)

        val actionBookingTicket = findViewById<Button>(R.id.actionBookingTicket)
        val actionBack = findViewById<Button>(R.id.actionBack)

        val dataDetail = IntentCompat.getParcelableExtra<Bus>(intent, "EXTRA_BUS", Bus::class.java)

        val tvName = findViewById<TextView>(R.id.textBusName)
        val tvRoute = findViewById<TextView>(R.id.textBusRoute)
        val tvPrice = findViewById<TextView>(R.id.textBusPrice)
        val imgBus = findViewById<ImageView>(R.id.imageBus)

        dataDetail?.let {
            tvName.text = it.name
            tvRoute.text = it.route
            tvPrice.text = it.price
            imgBus.setImageResource(it.ImageUrl)
        }

        actionBookingTicket.setOnClickListener {
            val intent = Intent(this, BookingChooseSeatActivity::class.java)
            intent.putExtra("DATA_DETAIL_BUS", dataDetail)
            startActivity(intent)
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