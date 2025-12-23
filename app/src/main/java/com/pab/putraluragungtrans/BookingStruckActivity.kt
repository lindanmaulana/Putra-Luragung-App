package com.pab.putraluragungtrans

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BookingStruckActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_struck)

        val actionNext = findViewById<Button>(R.id.actionNext)

        var struckBusName = findViewById<TextView>(R.id.struckBusName)
        var struckOriginCity = findViewById<TextView>(R.id.struckOriginCity)
        var struckDestinationCity = findViewById<TextView>(R.id.struckDestinationCity)
        var struckDepartureTime = findViewById<TextView>(R.id.struckDepartureTime)
        var struckArrivalTime = findViewById<TextView>(R.id.struckArrivalTime)
        var struckPrice = findViewById<TextView>(R.id.struckPrice)
        var struckSeatNumber = findViewById<TextView>(R.id.struckSeatNumber)

        val dataSelectedSeat = if (android.os.Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Seat>("DATA_SELECTED_SEAT", Seat::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Seat>("DATA_SELECTED_SEAT")
        }

//        val dataSelectedBus = if (android.os.Build.VERSION.SDK_INT >= 33) {
//            intent.getParcelableExtra<Bus>("DATA_SELECTED_BUS", Bus::class.java)
//        } else {
//            @Suppress("DEPRECATION")
//            intent.getParcelableExtra<Bus>("DATA_SELECTED_BUS")
//        }

        if (dataSelectedSeat != null) {
//            struckBusName.text = dataSelectedBus.name
            struckSeatNumber.text = dataSelectedSeat.seatNumber
        }

        actionNext.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

            intent.putExtra("TARGET_MENU_ID", R.id.nav_home)

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