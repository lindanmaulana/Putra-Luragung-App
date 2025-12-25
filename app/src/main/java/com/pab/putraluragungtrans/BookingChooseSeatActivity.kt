package com.pab.putraluragungtrans

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class BookingChooseSeatActivity : AppCompatActivity() {
    private lateinit var actionChooseSeatNext: Button
    private lateinit var rvSeatMap: RecyclerView
    private lateinit var seatAdapter: SeatAdapter

    private lateinit var cardCheckout: MaterialCardView
    private lateinit var seatSelected: TextView
    private lateinit var seatTotalPrice: TextView
    private val seatList = mutableListOf<Seat>()
    private var selectedSeatData: Seat? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_choose_seat)

        cardCheckout = findViewById<MaterialCardView>(R.id.cardCheckout)
        actionChooseSeatNext = findViewById<Button>(R.id.actionConfirmBooking)
        cardCheckout.visibility = View.GONE

        seatSelected = findViewById<TextView>(R.id.seatSelected)
        seatTotalPrice = findViewById<TextView>(R.id.seatTotalPrice)
        rvSeatMap = findViewById(R.id.rvSeatMap)

        val actionBack = findViewById<View>(R.id.actionBack)

//        val dataDetailBus = if (android.os.Build.VERSION.SDK_INT >= 33) {
//            intent.getParcelableExtra<Seat>("DATA_DETAIL_BUS", Seat::class.java)
//        } else {
//            @Suppress("DEPRECATION")
//            intent.getParcelableExtra<Seat>("DATA_DETAIL_BUS")
//        }

        actionChooseSeatNext.setOnClickListener {
            try {
                if (selectedSeatData != null) {
                    val intent = Intent(this, BookingCheckoutActivity::class.java)
                    intent.putExtra("DATA_SELECTED_SEAT", selectedSeatData)
//                    intent.putExtra("DATA_SELECTED_BUS", dataDetailBus)
                    startActivity(intent)
                }
            } catch (e: Exception) {
                android.util.Log.e("CRASH_DEBUG", "Penyebab Crash: ${e.message}")
                e.printStackTrace()
            }
        }

        actionBack.setOnClickListener { finish() }

        setupSeatData()
        setupRecyclerView()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupSeatData() {
        // 50 slot karena 10 baris x 5 kolom (termasuk lorong)
        for (i in 0 until 50) {
            val column = i % 5 // Mendapatkan posisi kolom (0-4)
            val row = (i / 5) + 1 // Mendapatkan nomor baris (1-10)

            var seatName = ""
            val isAisle = column == 2 // Kolom indeks 2 adalah lorong

            if (!isAisle) {
                // Tentukan huruf berdasarkan indeks kolom
                val letter = when (column) {
                    0 -> "A"
                    1 -> "B"
                    3 -> "C"
                    4 -> "D"
                    else -> ""
                }
                seatName = "$letter$row" // Menghasilkan A1, B1, C1, dst.
            }

            // Contoh: tandai beberapa kursi sebagai booked
            val booked = (i == 5 || i == 10)

            seatList.add(Seat(i, seatName, 120000, isBooked = booked))
        }
    }

    private fun setupRecyclerView() {
        seatAdapter = SeatAdapter(seatList) { clickedSeat ->
            if (clickedSeat.isBooked) return@SeatAdapter

            if (!clickedSeat.isBooked) {
                var wasSelected = clickedSeat.isSelected
                val previousSelectedIndex = seatList.indexOfFirst { it.isSelected }

                if (previousSelectedIndex != -1) {
                    seatList[previousSelectedIndex].isSelected = false
                    seatAdapter.notifyItemChanged(previousSelectedIndex)
                }

                clickedSeat.isSelected = !wasSelected
                seatAdapter.notifyItemChanged(seatList.indexOf(clickedSeat))
                updateCheckoutPanel(clickedSeat)
            }
        }

        rvSeatMap.layoutManager = GridLayoutManager(this, 5)
        rvSeatMap.adapter = seatAdapter
    }

    private fun updateCheckoutPanel(selectedSeat: Seat) {
        if (selectedSeat.isSelected) {
            selectedSeatData = selectedSeat
            cardCheckout.visibility = View.VISIBLE
            actionChooseSeatNext.visibility = View.VISIBLE
            seatSelected.text = "Kursi ${selectedSeat.seatNumber}"
            seatTotalPrice.text = "200000"
        } else {
            selectedSeatData = null
            cardCheckout.visibility = View.GONE
        }
    }
}