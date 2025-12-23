package com.pab.putraluragungtrans

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TicketDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ticket_detail)

        val actionBack = findViewById<Button>(R.id.actionBack)
        val dataDetail = IntentCompat.getParcelableExtra(intent, "EXTRA_TICKET", Ticket::class.java)

        val tvBusName: TextView = findViewById(R.id.tvBusName)
        val tvDetailAction: TextView = findViewById(R.id.tvDetailAction)
        val tvDepartureDate: TextView = findViewById(R.id.tvDepartureDate)

        val tvOriginCity: TextView = findViewById(R.id.tvOriginCity)
        val tvOriginTerminal: TextView = findViewById(R.id.tvOriginTerminal)
        val tvDepartureTime: TextView = findViewById(R.id.tvDepartureTime)

        val tvDestinationCity: TextView = findViewById(R.id.tvDestinationCity)
        val tvDestinationTerminal: TextView = findViewById(R.id.tvDestinationTerminal)
        val tvArrivalTime: TextView = findViewById(R.id.tvArrivalTime)

        dataDetail?.let {
            tvBusName.text = it.busName
            tvDetailAction.text = it.detailActionText
            tvDepartureDate.text = it.departureDate
            tvOriginCity.text = it.originCity
            tvOriginTerminal.text = it.originTerminal
            tvDepartureTime.text = it.departureTime
            tvDestinationCity.text = it.destinationCity
            tvDestinationTerminal.text = it.destinationTerminal
            tvArrivalTime.text = it.arrivalTime
        }

        actionBack.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

            intent.putExtra("TARGET_MENU_ID", R.id.nav_ticket)

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